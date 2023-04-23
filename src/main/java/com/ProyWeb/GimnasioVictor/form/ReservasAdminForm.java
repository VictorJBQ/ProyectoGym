package com.ProyWeb.GimnasioVictor.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class ReservasAdminForm {
	@NotNull
	private LocalDate fechaReserva;
	@NotNull
	private String email;
	@NotNull
	private String dni;
	public LocalDate getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
}
