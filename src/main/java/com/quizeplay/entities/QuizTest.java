package com.quizeplay.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QuizTest")
public class QuizTest {

	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@Column(name="Userid")
	private String userid;
	
	@Column(name="GivenAns")
	private String givenans;
	
	@Column(name="AskedQuestion")
	private String askedQuestion;
	
	@Column(name="correctOrwrong")
	private String corrects;
		
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getAskedQuestion() {
		return askedQuestion;
	}

	public void setAskedQuestion(String askedQuestion) {
		this.askedQuestion = askedQuestion;
	}

	public String getGivenans() {
		return givenans;
	}

	public void setGivenans(String givenans) {
		this.givenans = givenans;
	}

	public String getCorrects() {
		return corrects;
	}

	public void setCorrects(String corrects) {
		this.corrects = corrects;
	}

	@Override
	public String toString() {
		return "QuizTest [id=" + id + ", userid=" + userid + ", givenans=" + givenans + ", askedQuestion="
				+ askedQuestion + ", corrects=" + corrects + "]";
	}


	
}
