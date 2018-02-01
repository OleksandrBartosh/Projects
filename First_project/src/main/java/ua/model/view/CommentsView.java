package ua.model.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentsView {

	private Integer id;
	
	private String comment;
	
	private String date;
	
	private String name;
	
	private String surname;
	
	private int rate;
	
	public CommentsView(Integer id, String comment, LocalDateTime date, String name, String surname, int rate) {
		this.id = id;
		this.comment = comment;
		this.date = date.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
		this.name = name;
		this.surname = surname;
		this.rate = rate;
	}

	public Integer getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public String getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
