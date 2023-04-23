package com.ProyWeb.GimnasioVictor.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ProyWeb.GimnasioVictor.model.Reservas;

public interface ReservasRepositorio extends CrudRepository<Reservas,Integer>{
	
	@Query("FROM Reservas Where usuario.email=:emailusu")
	List<Reservas> buscarReservaEmail(String emailusu);
}
