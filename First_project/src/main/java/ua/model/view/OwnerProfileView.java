package ua.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OwnerProfileView {

	private Integer id;
	
	private BigDecimal rate;

	private String photoUrl;
	
	private int version;
	
	private String email;
	
	private String name;
	
	private String surname;

	private String phone;

	private int count;

	private String address;
	
	private String registrationDate;

	public OwnerProfileView() {
	}

	public OwnerProfileView(Integer id, BigDecimal rate, String photoUrl, int version, String email, String name,
			String surname, String phone, int count, String address, LocalDateTime registrationDate) {
		this.id = id;
		this.rate = rate;
		this.photoUrl = photoUrl;
		this.version = version;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.count = count;
		this.address = address;
		this.registrationDate = registrationDate.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
	}

	public Integer getId() {
		return id;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
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

	public String getAddress() {
		return address;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "OwnerProfileView [id=" + id + ", rate=" + rate + ", photoUrl=" + photoUrl + ", version=" + version
				+ ", email=" + email + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", count="
				+ count + ", address=" + address + ", registrationDate=" + registrationDate + "]";
	}
}