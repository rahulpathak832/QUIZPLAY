package com.quizeplay.quizController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizeplay.entities.MainQuiz_tbl;
import com.quizeplay.entities.QuizTest;
import com.quizeplay.model.QuestionAnswers;
import com.quizeplay.repositories.MainQuizProjection;
import com.quizeplay.repositories.MainQuizRepository;
import com.quizeplay.repositories.QuizTestRepository;




@RestController
@RequestMapping(value = "/quiz")
public class MainQuiz_controller {
	
	@Autowired MainQuizRepository mainquizrepos;

	@Autowired QuizService quizservice;
	
	@Autowired QuizTestRepository quiztestrepos;
	
	@PostMapping("/insertquestion")
	public ResponseEntity<MainQuiz_tbl> saveQuestion(@RequestBody QuestionAnswers quesAns) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		MainQuiz_tbl quiz = new MainQuiz_tbl();
		quiz.setQuestion(quesAns.getQuestions());
		quiz.setCorrectanswers(quesAns.getCorrectans());
		quiz.setAnslist(map.writeValueAsString(quesAns.getAnswerlist()));
		quiz.setEnteredDate(new Date());
		MainQuiz_tbl save = mainquizrepos.save(quiz);
		return new ResponseEntity<MainQuiz_tbl>(save, HttpStatus.OK);
	}
	
	@GetMapping("/getAllquestions")
	public ResponseEntity<List<String>> getAllQuestions() {
		List<MainQuizProjection> allQuizes = mainquizrepos.findBy();
		List<String> quizes = allQuizes.stream()
				.map(MainQuizProjection::getQuestion)
				.collect(Collectors.toList());
		return new ResponseEntity<List<String>>(quizes, HttpStatus.OK);
	}
	
	@GetMapping("/getAllQuestionAnswers")
	public ResponseEntity<Map<String, String>> getAllQuestionAnswers() {
		return new ResponseEntity<Map<String, String>>
		(quizservice.getallQuestionAnswers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/sendAns", method = RequestMethod.POST)
	public void sendAndProcessAns(@RequestParam String user,@RequestParam String question,
		@RequestParam String answer, HttpServletRequest request) {

		if(!quiztestrepos.existsByAskedQuestionAndUserid(question,user)) {
			System.out.println("not exist");
			QuizTest quiz = new QuizTest();
			quiz.setAskedQuestion(question);
			quiz.setGivenans(answer);
			quiz.setUserid(user);
			Map<String, String> getallQuestionAnswers = quizservice.getallQuestionAnswers();
			quiz.setCorrects((getallQuestionAnswers.get(question).equals(answer)) ? "1" : "0");

			QuizTest save = quiztestrepos.save(quiz);
			System.out.println("Object saved => "+ save);
		}
	}
	
	@GetMapping("/getresult/{userid}")
	public String getQuizResult(@PathVariable String userid) {
		System.out.println("get quiz records => "+ userid);
		long count = quiztestrepos.countByUserid(userid);
		long correct = quiztestrepos.countBycorrectAnswers(userid);
		return "<html>\n" + "<header><title>QUIZ result</title></header>\n" +
        "<body>\n<h2 style='text-align:center; font-size: 2.5rem;' >You got "+correct+" out of "+ count+"</h2>"
        + " <a style='font-size: 1.5rem;' href='http://localhost:8080/startQuizSession'>Play another quiz</a></body>\n" + "</html>";
	}
	

	
	
	
	
	
	
	
	
	
	
}
