package com.ProyWeb.GimnasioVictor.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Entrenador {
	@Id
	private String dni;
	private String nombre;
	private String apellido;
	
	@OneToMany(mappedBy="entrenador",cascade = CascadeType.ALL)
	private List<Reservas> listaReserva;
	

	
	public Entrenador() {
		
	}




	public Entrenador(String dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String id) {
		this.dni = id;
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

	
	
	
}
