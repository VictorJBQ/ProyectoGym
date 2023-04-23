package com.ProyWeb.GimnasioVictor.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ProyWeb.GimnasioVictor.model.Entrenador;

public interface EntrenadorRepositorio extends CrudRepository<Entrenador,Integer>{
	Entrenador findBydni(String dni);
}
