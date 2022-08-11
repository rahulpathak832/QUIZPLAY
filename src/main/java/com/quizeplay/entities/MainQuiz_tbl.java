package com.quizeplay.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="MAIN_QUIZ_TBL")
public class MainQuiz_tbl {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="QUESTION", unique = true)
	private String question;
	
	@Column(name="ANSWERS_LIST")
	private String anslist;
	
	@Column(name="CORRECT_ANS")
	private String correctanswers;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTERED_DATE")
	private Date enteredDate;
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnslist() {
		return anslist;
	}

	public void setAnslist(String anslist) {
		this.anslist = anslist;
	}

	public String getCorrectanswers() {
		return correctanswers;
	}

	public void setCorrectanswers(String correctanswers) {
		this.correctanswers = correctanswers;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	@Override
	public String toString() {
		return "MainQuiz_tbl [id=" + id + ", question=" + question + ", anslist=" + anslist + ", correctanswers="
				+ correctanswers + ", enteredDate=" + enteredDate + "]";
	}



}
