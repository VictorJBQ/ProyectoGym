package com.ProyWeb.GimnasioVictor.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {
	private String nombre;
	private String apellido;
	@Id
	private String email;
	private String password;
	
	
	@ManyToOne
	@JoinColumn(name="roles_rol")
	private Roles roles;
	
	@OneToMany(mappedBy="usuario",cascade = CascadeType.ALL)
	private List<Reservas> listaReserva;
	
	
	
	public Usuario() {
		
	}
	public Usuario( String nombre, String apellido, String email, String password, Roles roles) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario(String nombre, String apellido, String email, String password, List<Reservas> listaReserva,
			 Roles roles) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.listaReserva = listaReserva;
		this.roles = roles;
	}
	
	public Usuario(String nombre, String apellido, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", password=" + password
				+ ", listaReserva=" + listaReserva + "]";
	}
	
	
	
	
}
