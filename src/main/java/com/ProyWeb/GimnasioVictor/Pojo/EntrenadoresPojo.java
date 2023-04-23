package com.ProyWeb.GimnasioVictor.Pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entrenadores")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntrenadoresPojo {
	@XmlElement(name="entrenador")
	private List<EntrenadorPojo> entrenador = null;

	public EntrenadoresPojo(List<EntrenadorPojo> entrenador) {
		super();
		this.entrenador = entrenador;
	}

	public EntrenadoresPojo() {
		super();
	}

	public List<EntrenadorPojo> getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(List<EntrenadorPojo> entrenador) {
		this.entrenador = entrenador;
	}

	@Override
	public String toString() {
		return "EntrenadoresPojo [entrenador=" + entrenador + "]";
	}
	
	
}
