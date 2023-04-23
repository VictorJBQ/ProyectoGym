package com.ProyWeb.GimnasioVictor.model;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Roles {
	
	@Id
	private String rol;
	
	@OneToMany(mappedBy="roles")
	private List<Usuario> usuario;

	

	public Roles(String rol) {
		super();
		this.rol = rol;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}



	public Roles() {
		super();
	}



	@Override
	public String toString() {
		return "Roles [rol=" + rol + ", usuario=" + usuario + "]";
	}



	
	
	
}
