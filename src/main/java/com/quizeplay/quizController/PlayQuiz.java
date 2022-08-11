package com.quizeplay.quizController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizeplay.entities.MainQuiz_tbl;
import com.quizeplay.repositories.MainQuizProjection;
import com.quizeplay.repositories.MainQuizRepository;


import net.bytebuddy.utility.RandomString;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

@RestController
@RequestMapping(value = "/")
public class PlayQuiz {
	
	@Autowired MainQuizRepository mainquizrepos;
	
	@Autowired QuizService quizservice;
	
	@Autowired ObjectMapper mapper;
	
	@Autowired
	private Environment env;
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/startQuizSession")
	public String startQuizSession(HttpServletRequest request) {
		String quizLink = env.getProperty("quiz.baseurl")+"/play/1/"+RandomString.make(4);
		System.out.println(quizLink);
		return	"<html>\n" + "<header><title>Welcome</title></header>\n" +
        "<body>\n <a href=" + quizLink + ">Click the link to play quiz</a></body>\n" + "</html>";
	}
	
	@GetMapping("/play/{index}/{userid}")
	public String playQuiz(@PathVariable int index,@PathVariable String userid,
			HttpServletRequest req) throws JsonProcessingException {
		String BASEURI = env.getProperty("quiz.baseurl");
		List<MainQuizProjection> allQuizes = mainquizrepos.findBy();
		
		Map<String, String> quizObj = allQuizes.stream()
				.skip(index).limit(1)
				.collect(Collectors.toMap(MainQuizProjection::getQuestion, MainQuizProjection::getAnslist));
		Map.Entry<String,String> entry = quizObj.entrySet().iterator().next();
		
		List<String> anslist = Arrays.asList(entry.getValue().replaceAll("[\\[\\]\"]", "").split(","));

		StringBuilder finalhtml = new StringBuilder();
		int opt = 1;
		for (String s : anslist) {
			finalhtml.append(quizservice.generateHtml(s,opt));
		    opt++;
		}
		finalhtml.append(quizservice.generateFooter());
		String nextAnchor = "";
		if(allQuizes.size() > index+1) {
			String url = BASEURI+req.getRequestURI().replaceFirst("\\d",String.valueOf(index+1));
			nextAnchor = "<a href=" + url + ">goto next question</a>";
		}else {
			nextAnchor = "<a href='"+BASEURI+"/quiz/getresult/"+userid+"'>Get your result</a>";
		}
		
	 return "<div style='transform: scale(0.65); position: relative;'>\r\n" + 
  		"  <h1 id style='font-size: 3rem;'>"+entry.getKey()+"</h1>\r\n" + 
  		"  <p style='font-size: 1.8rem;'>Choose 1 answer</p>\r\n" + 
  		"  <hr />\r\n" + 
  		"\r\n" + 
  		finalhtml+
  		"<p style=' padding: 5px; font-size: 2.5rem;'>"+nextAnchor+"</p></body>\n"+
  		" <input type=\"hidden\" id=\"userid\" name=\"userid\" value="+ userid +">"+
  		" <input type=\"hidden\" id=\"question\" name=\"question\" value="+ entry.getKey() +">"+
  		"</div>";
	}
	 

	 
	 
	
}
