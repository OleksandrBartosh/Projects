package ua.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="offer")
public class Offer extends AbstractEntity{

	@ManyToOne(fetch=FetchType.LAZY)
	private Cargo cargo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Transporter transporter;
	
	@Enumerated
	private OfferStatus offerStatus;
	
	@Column(name="transporter_confirm")
	private int transporterConfirm;
	
	@Column(name="owner_confirm")
	private int ownerConfirm;
	
	@Column(name="left_by")//1-owner 2-transporter
	private int leftBy;

	public Offer() {
	}

	public Cargo getCargo() {
		return cargo;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public int getTransporterConfirm() {
		return transporterConfirm;
	}

	public int getOwnerConfirm() {
		return ownerConfirm;
	}

	public int getLeftBy() {
		return leftBy;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public void setTransporterConfirm(int transporterConfirm) {
		this.transporterConfirm = transporterConfirm;
	}

	public void setOwnerConfirm(int ownerConfirm) {
		this.ownerConfirm = ownerConfirm;
	}

	public void setLeftBy(int leftBy) {
		this.leftBy = leftBy;
	}
}
