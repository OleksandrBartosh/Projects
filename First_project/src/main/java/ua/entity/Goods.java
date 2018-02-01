package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="goods", indexes=@Index(columnList = "name", unique=true))
public class Goods extends AbstractEntityName{

	@OneToMany(mappedBy="goods")
	private List<Cargo> cargos = new ArrayList<>();

	public Goods() {
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
}