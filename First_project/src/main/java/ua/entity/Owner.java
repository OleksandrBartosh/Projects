package ua.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "owner", indexes = { @Index(columnList = "name"),
								   @Index(columnList = "surname"),
								   @Index(columnList = "_count"),
								   @Index(columnList = "phone", unique=true)})
public class Owner extends AbstractEntityName {

	private String surname;

	@Column(length=13)
	private String phone;

	private BigDecimal rate;
	
	@Column(name = "_count")
	private int count;
	
	private String photoUrl;
	
	private int version;

	private String address;
	
	@Column(name="registration_date")
	private LocalDateTime registrationDate;

	@OneToOne
	private User user;

	@OneToMany(mappedBy = "owner")
	private List<Cargo> cargos = new ArrayList<>();
	
	@OneToMany(mappedBy="owner")
	private List<Comments> comments = new ArrayList<>();
	
	public Owner() {
	}

	public String getSurname() {
		return surname;
	}

	public String getPhone() {
		return phone;
	}

	public int getCount() {
		return count;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public String getAddress() {
		return address;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public User getUser() {
		return user;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}