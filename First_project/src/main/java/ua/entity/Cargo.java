
package ua.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cargo",
	   indexes= {@Index(columnList = "weight"),
				 @Index(columnList = "height"),
				 @Index(columnList = "width"),
				 @Index(columnList = "length"),
				 @Index(columnList = "price")})
public class Cargo extends AbstractEntity{

	@ManyToOne(fetch=FetchType.LAZY)
	private Goods goods;
	
	private int weight;
	
	private int height;
	
	private int width;
	
	private int length;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private City cityFrom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private City cityTo;
	
	private BigDecimal price;
	
	private LocalDateTime creationDate;
	
	@Enumerated
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Owner owner;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Transporter transporter;
	
	@OneToMany(mappedBy="cargo", orphanRemoval=true, cascade=CascadeType.PERSIST)
	private List<Offer> offers = new ArrayList<>();
	
	public Cargo() {
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
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

	public City getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(City cityFrom) {
		this.cityFrom = cityFrom;
	}

	public City getCityTo() {
		return cityTo;
	}

	public void setCityTo(City cityTo) {
		this.cityTo = cityTo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}
}