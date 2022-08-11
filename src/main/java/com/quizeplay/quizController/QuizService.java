package com.quizeplay.quizController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizeplay.repositories.MainQuizProjection;
import com.quizeplay.repositories.MainQuizRepository;

@Service
public class QuizService {
	@Autowired MainQuizRepository mainquizrepos;
	
	 public Map<String, String> getallQuestionAnswers() {
		List<MainQuizProjection> allQuizes = mainquizrepos.findBy();
		Map<String, String> quesAns = allQuizes.stream()
			.collect(Collectors.toMap(MainQuizProjection::getQuestion, MainQuizProjection::getCorrectanswers));
		return quesAns;
	 }
	 
	 
	 public long countQuestions() {
		 return mainquizrepos.count();
	 }
	
	public String generateHtml(String elem, int opt) {
		return "<div id='block-"+opt+"' style='padding: 10px;'>\r\n" + 
		  		"    <label for='option-"+opt+"' style=' padding: 5px; font-size: 2.5rem;'>\r\n" + 
		  		"      <input type='radio' style='transform: scale(1.6); margin-right: 10px; vertical-align: middle; margin-top: -2px;' onclick=myFunction("+elem+") />\r\n" + 
		  		"      "+elem+"</label>\r\n" + 
		  		"    <span id='result-"+opt+"'></span>\r\n" + 
		  		"  </div>\r\n" + 
		  		"  <hr />\r\n" + 
		  		"\r\n";
	}
	
	public String generateFooter() {
		
		return "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>\r\n" + 
				"	<script>\r\n" + 
				"	function myFunction(ans){\r\n" + 
				"	var userid = document.getElementById(\"userid\").value;\r\n" + 
//				"	var ans = document.getElementById(\"anser\").value;\r\n" + 
				" var question = document.querySelector(\"h1\").innerText;"+
				"var data = { \"user\": userid, \"question\": question, \"answer\": ans}; \r\n"+
				"console.log(userid);\r\n"+
				"	 $.ajax({\r\n" + 
				"			type: \"POST\",\r\n" + 
				"			url: \"/quiz/sendAns\",\r\n" + 
				"			data:  data ,\r\n" + 
				"			dataType: 'json',\r\n" + 
				"			success: function(result) {\r\n" + 
				"				window.console.log('Successful');\r\n" + 
				"			}\r\n" + 
				"		});\r\n" + 
				"	}\r\n" + 
				"</script>";
	}
	
}
