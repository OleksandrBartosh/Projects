package ua.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CargoView {

	private Integer id;

	private String goods;

	private int weight;

	private int height;

	private int width;

	private int length;

	private String cityFrom;

	private String cityTo;

	private String price;
	
	private String creationDate;
	
	public CargoView() {
	}

	public CargoView(Integer id, String goods, int weight, int height, int width, int length, String cityFrom,
			String cityTo, BigDecimal price, LocalDateTime creationDate) {
		this.id = id;
		this.goods = goods;
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.length = length;
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
		this.price = String.valueOf(price);
		this.creationDate = creationDate.format(DateTimeFormatter.ofPattern("dd:MM:yy HH:mm"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}

	public String getCityTo() {
		return cityTo;
	}

	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "goods: " + goods + ", weight: " + weight + ", height: " + height + ", width: "
				+ width + ", length: " + length + ", cityFrom: " + cityFrom + ", cityTo: " + cityTo + ", price: " + price
				+ ", creationDate: " + creationDate;
	}
}