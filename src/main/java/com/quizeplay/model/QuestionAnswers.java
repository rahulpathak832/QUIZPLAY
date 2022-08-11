package com.quizeplay.model;

import java.util.List;

public class QuestionAnswers {

	private String questions;
	private String correctans;
	private List<String> answerlist;
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getCorrectans() {
		return correctans;
	}
	public void setCorrectans(String correctans) {
		this.correctans = correctans;
	}
	public List<String> getAnswerlist() {
		return answerlist;
	}
	public void setAnswerlist(List<String> answerlist) {
		this.answerlist = answerlist;
	}
	
}
