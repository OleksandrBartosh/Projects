package ua.model.request;

import ua.entity.Cargo;

public class ChooseCargoRequest {

	private Cargo cargo;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}