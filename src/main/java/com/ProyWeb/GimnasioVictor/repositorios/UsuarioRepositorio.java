package com.ProyWeb.GimnasioVictor.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ProyWeb.GimnasioVictor.model.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario,Integer>{
	Usuario findByemail(String Email);
	
	@Query("FROM Usuario WHERE roles.rol != :rol")
	List<Usuario> buscardiferenteAdmin(String rol);
	
}
