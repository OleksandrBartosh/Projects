package ua.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ua.entity.Status;

public class TransporterView {
	
	private Integer id;
	
	private BigDecimal rate;
	
	private int count;
	
	private String photoUrl;
	
	private int version;
	
	private String name;
	
	private String surname;
	
	private int age;
	
	private String phone;
	
	private String brand;
	
	private String model;
	
	private int maxWeight;
	
	private int carAge;
	
	private String cityArrive;
	
	private String dateArrive;
	
	private String registrationDate;
	
	private String status;
	
	public TransporterView() {
	}

	public TransporterView(Integer id, BigDecimal rate, int count, String photoUrl, int version, String name,
			String surname, int age, String phone, String brand, String model, int maxWeight, int carAge,
			String cityArrive, Date dateArrive, LocalDateTime registrationDate, Status status) {
		this.id = id;
		this.rate = rate;
		this.count = count;
		this.photoUrl = photoUrl;
		this.version = version;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.phone = phone;
		this.brand = brand;
		this.model = model;
		this.maxWeight = maxWeight;
		this.carAge = carAge;
		this.cityArrive = cityArrive;
		this.dateArrive = dateArrive.toString();
		this.registrationDate = registrationDate.format(DateTimeFormatter.ofPattern("dd:MM:yy HH:mm"));
		this.status = status.name();
	}

	public Integer getId() {
		return id;
	}

	public BigDecimal getRate() {
		return rate;
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

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public int getCarAge() {
		return carAge;
	}

	public String getCityArrive() {
		return cityArrive;
	}

	public String getDateArrive() {
		return dateArrive;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public void setCarAge(int carAge) {
		this.carAge = carAge;
	}

	public void setCityArrive(String cityArrive) {
		this.cityArrive = cityArrive;
	}

	public void setDateArrive(String dateArrive) {
		this.dateArrive = dateArrive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "TransporterView [id=" + id + ", rate=" + rate + ", count=" + count + ", photoUrl=" + photoUrl
				+ ", version=" + version + ", name=" + name + ", surname=" + surname + ", age=" + age + ", phone="
				+ phone + ", brand=" + brand + ", model=" + model + ", maxWeight=" + maxWeight + ", carAge=" + carAge
				+ ", cityArrive=" + cityArrive + ", dateArrive=" + dateArrive + ", registrationDate=" + registrationDate
				+ ", status=" + status + "]";
	}
}