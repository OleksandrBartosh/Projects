package ua.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transporter", indexes= {@Index(columnList = "name"),
									@Index(columnList = "surname"),
									@Index(columnList = "age"),
									@Index(columnList = "rate"),
									@Index(columnList = "maxWeight"),
									@Index(columnList = "_count"),
									@Index(columnList = "phone",unique=true),
									@Index(columnList = "status")})
public class Transporter extends AbstractEntityName{

	private String surname;
	
	private int age;

	private BigDecimal rate;
	
	private int maxWeight;
	
	private String photoUrl;
	
	private int version;
	
	@Column(name="_count")
	private int count;
	
	@Column(length=13)
	private String phone;
	
	@ManyToOne(fetch=FetchType.LAZY)
    private Model model;
	
	private int carAge;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private City cityArrive;

	@Column(name="date_arrive")
	@Temporal(TemporalType.DATE)
	private Date dateArrive;
	
	@Column(name="registration_date")
	private LocalDateTime registrationDate;
	
	@Enumerated
	private Status status;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy="transporter")
	private List<Comments> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "owner")
	private List<Cargo> cargos = new ArrayList<>();
	
	@OneToMany(mappedBy = "transporter")
	private List<Offer> offers = new ArrayList<>();
		
	public Transporter() {
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public int getCount() {
		return count;
	}

	public String getPhone() {
		return phone;
	}

	public Model getModel() {
		return model;
	}

	public int getCarAge() {
		return carAge;
	}

	public City getCityArrive() {
		return cityArrive;
	}

	public Date getDateArrive() {
		return dateArrive;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public Status getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setCarAge(int carAge) {
		this.carAge = carAge;
	}

	public void setCityArrive(City cityArrive) {
		this.cityArrive = cityArrive;
	}

	public void setDateArrive(Date dateArrive) {
		this.dateArrive = dateArrive;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
}