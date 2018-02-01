package ua.model.request;

import ua.entity.Brand;
import ua.entity.Model;

public class TransporterCarRequest {

	private Brand brand;
	
    private Model model;
	
	private int carAge;
	
	private int maxWeight;

	public TransporterCarRequest() {
	}

	public Brand getBrand() {
		return brand;
	}

	public Model getModel() {
		return model;
	}

	public int getCarAge() {
		return carAge;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setCarAge(int carAge) {
		this.carAge = carAge;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}
}
