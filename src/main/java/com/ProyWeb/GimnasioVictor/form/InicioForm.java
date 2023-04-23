package com.ProyWeb.GimnasioVictor.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class InicioForm {
	@NotNull
	@Email
	private String email;
	@NotNull
	private String contra;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	
	
}
