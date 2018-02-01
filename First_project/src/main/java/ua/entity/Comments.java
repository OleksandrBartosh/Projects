package ua.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Comments extends AbstractEntity{

	private int rate;
	
	private String comment;
	
	private LocalDateTime date;
	
	@Enumerated
	private Role leftBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Transporter transporter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Owner owner;
 
	public Comments() {
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Role getLeftBy() {
		return leftBy;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setLeftBy(Role leftBy) {
		this.leftBy = leftBy;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}