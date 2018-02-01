package ua.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OwnerView {

	private Integer id;
	
	private BigDecimal rate;

	private String photoUrl;
	
	private int version;
	
	private String name;
	
	private String surname;

	private String phone;

	private int count;

	private String address;
	
	private String registrationDate;

	public OwnerView() {
	}

	public OwnerView(Integer id,  BigDecimal rate, String photoUrl, int version, String name, String surname, String phone, int count,
			String address, LocalDateTime registrationDate ) {
		this.id = id;
		this.rate = rate;
		this.photoUrl = photoUrl;
		this.version = version;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.count = count;
		this.address = address;
		this.registrationDate = registrationDate.format(DateTimeFormatter.ofPattern("dd:MM:yy HH:mm"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getPhotoUrl() {
		return photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegistrationDate() {
		return registrationDate;
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
		return "OwnerView [id=" + id + ", rate=" + rate + ", photoUrl=" + photoUrl + ", version=" + version + ", name="
				+ name + ", surname=" + surname + ", phone=" + phone + ", count=" + count + ", address=" + address
				+ ", registrationDate=" + registrationDate + "]";
	}
}