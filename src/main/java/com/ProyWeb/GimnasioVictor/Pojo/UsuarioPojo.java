package com.ProyWeb.GimnasioVictor.Pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "usuario")
@XmlType(propOrder = { "nombre", "apellido","email","password"})
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioPojo {
	@XmlElement(name = "nombre")
	private String nombre;
	@XmlElement(name = "apellido")
	private String apellido;
	@XmlElement(name = "email")
	private String email;
	@XmlElement(name = "password")
	private String password;
	
	
	
	public UsuarioPojo() {
		super();
	}
	public UsuarioPojo(String nombre, String apellido, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
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
		return "UsuarioPojo [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", password="
				+ password + "]";
	}
	

}
