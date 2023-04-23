package com.ProyWeb.GimnasioVictor.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistroEntrenadorForm {
	@NotNull
	@Size(min=9, max=9, message="Campo contraseña debe tener 9 caracteres")
	private String dni;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	
	
	@Override
	public String toString() {
		return "RegistroEntrenadorForm [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
