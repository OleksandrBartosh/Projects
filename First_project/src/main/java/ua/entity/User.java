package ua.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="_user", indexes=@Index(columnList = "email"))
public class User extends AbstractEntity{

	private String email;
	
	private String password;
	
	private Role role;
	
	@OneToOne(mappedBy="user", orphanRemoval=true, cascade=CascadeType.PERSIST)
	private Owner owner;
	
	@OneToOne(mappedBy="user", orphanRemoval=true, cascade=CascadeType.PERSIST)
	private Transporter transporter;
	
	@OneToOne(mappedBy="user", orphanRemoval=true, cascade=CascadeType.PERSIST)
	private Admin admin;
	
	public User() {
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Transporter getTransporter() {
		return transporter;
	}
	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}