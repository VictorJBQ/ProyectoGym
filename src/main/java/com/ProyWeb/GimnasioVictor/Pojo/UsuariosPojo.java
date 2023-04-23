package com.ProyWeb.GimnasioVictor.Pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuariosPojo {
	@XmlElement(name = "usuario")
	  private List<UsuarioPojo> usuario=null;

	public UsuariosPojo(List<UsuarioPojo> usuario) {
		super();
		this.usuario = usuario;
	}

	public UsuariosPojo() {
		super();
	}

	public List<UsuarioPojo> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<UsuarioPojo> usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuariosPojo [usuario=" + usuario + "]";
	}
	
	
}
