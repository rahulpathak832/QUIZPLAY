package com.quizeplay.model;

public class QuizInstance {
	
	private String user;
	
	private String question;
	
	private String answer;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "QuizInstance [user=" + user + ", question=" + question + ", answer=" + answer + "]";
	}
	
}
