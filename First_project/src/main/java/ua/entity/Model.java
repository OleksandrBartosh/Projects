package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="model", indexes=@Index(columnList = "name", unique=true))
public class Model extends AbstractEntityName{
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Brand brand;

	@OneToMany(mappedBy="model")
	private List<Transporter> transporters = new ArrayList<>();

	public Model(String name) {
		super(name);
	}

	public Model() {
	}

	public Brand getBrand() {
		return brand;
	}

	public List<Transporter> getTransporters() {
		return transporters;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setTransporters(List<Transporter> transporters) {
		this.transporters = transporters;
	}
	
}