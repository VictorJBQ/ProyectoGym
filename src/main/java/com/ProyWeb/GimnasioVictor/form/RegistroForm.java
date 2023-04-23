package com.ProyWeb.GimnasioVictor.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistroForm {
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	@NotNull
	private String email;
	@NotNull
	@Size(min=3, max=30, message="Campo contraseña debe tener entre 3 y 30 caracteres")
	private String password;
	private String rol;
	private String opcion;
	
	
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	@Override
	public String toString() {
		return "RegistroForm [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", contra=" + password
				+ "]";
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
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	
}
