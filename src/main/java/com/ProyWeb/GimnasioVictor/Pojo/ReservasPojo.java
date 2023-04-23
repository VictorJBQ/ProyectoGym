package com.ProyWeb.GimnasioVictor.Pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "reservas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservasPojo {
	@XmlElement(name = "reserva")
	  private List<ReservaPojo> reserva=null;

	public ReservasPojo(List<ReservaPojo> reserva) {
		super();
		this.reserva = reserva;
	}

	public ReservasPojo() {
		super();
	}

	public List<ReservaPojo> getReserva() {
		return reserva;
	}

	public void setReserva(List<ReservaPojo> reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "ReservasPojo [reserva=" + reserva + "]";
	}
	
	
	
}
