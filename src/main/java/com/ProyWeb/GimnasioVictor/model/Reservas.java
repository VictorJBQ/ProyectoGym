package com.ProyWeb.GimnasioVictor.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate fechaRealizada;
	private LocalDate fechaReserva;
	
	@ManyToOne
	@JoinColumn(name="usuario_email")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="entrenador_dni")
	private Entrenador entrenador;

	public Reservas() {
		super();
	}


	public Reservas(LocalDate fechaRealizada, LocalDate fechaReserva, Usuario usuario, Entrenador entrenador) {
		super();
		this.fechaRealizada = fechaRealizada;
		this.fechaReserva = fechaReserva;
		this.usuario = usuario;
		this.entrenador = entrenador;
	}


	public Reservas(LocalDate fechaReserva, Usuario usuario, Entrenador entrenador) {
		super();
		this.fechaRealizada = LocalDate.now();
		this.fechaReserva = fechaReserva;
		this.usuario = usuario;
		this.entrenador = entrenador;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(LocalDate fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	
	
	
}
