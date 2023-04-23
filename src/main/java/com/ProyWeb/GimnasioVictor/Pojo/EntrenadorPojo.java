package com.ProyWeb.GimnasioVictor.Pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "entrenador")
@XmlType(propOrder = { "dni", "nombre", "apellido" })
@XmlAccessorType(XmlAccessType.FIELD)
public class EntrenadorPojo {
	@XmlElement(name = "dni")
	private String dni;
	@XmlElement(name = "nombre")
	private String nombre;
	@XmlElement(name = "apellido")
	private String apellido;
	
	
	
	public EntrenadorPojo() {
		super();
	}
	public EntrenadorPojo(String dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
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
	@Override
	public String toString() {
		return "EntrenadorPojo [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	

}
