package com.ProyWeb.GimnasioVictor.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.ProyWeb.GimnasioVictor.model.Roles;

public interface RolesRepositorio  extends CrudRepository<Roles,Integer>{
		Roles findByRol(String ro);
}
