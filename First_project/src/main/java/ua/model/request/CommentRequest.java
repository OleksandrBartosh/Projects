package ua.model.request;

import org.hibernate.validator.constraints.NotBlank;

public class CommentRequest {

	private String comment;
	
	@NotBlank(message="This field can not be empty")
	private String rate;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
}
