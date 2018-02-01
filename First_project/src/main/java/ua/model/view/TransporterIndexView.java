package ua.model.view;

import java.math.BigDecimal;

public class TransporterIndexView {

	private Integer id;
	
	private BigDecimal rate;
	
	private int count;
	
	private String photoUrl;
	
	private int version;
	
	private String name;
	
	private String brand;
	
	private String model;
	
	private int maxWeight;
	
	public TransporterIndexView() {
	}

	public TransporterIndexView(Integer id, BigDecimal rate, int count, String photoUrl, int version, String name,
			String brand, String model, int maxWeight) {
		this.id = id;
		this.rate = rate;
		this.count = count;
		this.photoUrl = photoUrl;
		this.version = version;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.maxWeight = maxWeight;
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

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getMaxWeight() {
		return maxWeight;
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

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Override
	public String toString() {
		return "TransporterIndexView [id=" + id + ", rate=" + rate + ", count=" + count + ", photoUrl=" + photoUrl
				+ ", version=" + version + ", name=" + name + ", brand=" + brand + ", model=" + model + ", maxWeight="
				+ maxWeight + "]";
	}
}