package ua.model.request;

import ua.entity.City;
import ua.entity.Status;

public class TransporterStatusRequest {

	private Integer id;
	
	private City cityArrive;
	
	private Status status;
	
	private String dateArrive;

	public TransporterStatusRequest() {
	}

	public Integer getId() {
		return id;
	}

	public City getCityArrive() {
		return cityArrive;
	}

	public Status getStatus() {
		return status;
	}

	public String getDateArrive() {
		return dateArrive;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCityArrive(City cityArrive) {
		this.cityArrive = cityArrive;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDateArrive(String dateArrive) {
		this.dateArrive = dateArrive;
	}
}
