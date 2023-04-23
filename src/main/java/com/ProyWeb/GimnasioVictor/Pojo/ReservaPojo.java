package com.ProyWeb.GimnasioVictor.Pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "reserva")
@XmlType(propOrder = { "fechaRealizada", "fechaReserva","usuario_email","entrenador_dni"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservaPojo {
	@XmlElement(name = "fechaRealizada")
	private String fechaRealizada;
	@XmlElement(name = "fechaReserva")
	private String fechaReserva;
	@XmlElement(name = "usuario_email")
	private String usuario_email;
	@XmlElement(name = "entrenador_dni")
	private String entrenador_dni;
	
	
	
	
	public ReservaPojo() {
		super();
	}
	public ReservaPojo(String fechaRealizada, String fechaReserva, String usuario_email, String entrenador_dni) {
		super();
		this.fechaRealizada = fechaRealizada;
		this.fechaReserva = fechaReserva;
		this.usuario_email = usuario_email;
		this.entrenador_dni = entrenador_dni;
	}
	public String getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	public String getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getUsuario_email() {
		return usuario_email;
	}
	public void setUsuario_email(String usuario_email) {
		this.usuario_email = usuario_email;
	}
	public String getEntrenador_dni() {
		return entrenador_dni;
	}
	public void setEntrenador_dni(String entrenador_dni) {
		this.entrenador_dni = entrenador_dni;
	}
	@Override
	public String toString() {
		return "ReservaPojo [fechaRealizada=" + fechaRealizada + ", fechaReserva=" + fechaReserva + ", usuario_email="
				+ usuario_email + ", entrenador_dni=" + entrenador_dni + "]";
	}
	
	
}
