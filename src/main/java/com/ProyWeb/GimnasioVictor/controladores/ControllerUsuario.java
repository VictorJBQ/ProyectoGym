package com.ProyWeb.GimnasioVictor.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ProyWeb.GimnasioVictor.form.RegistroForm;
import com.ProyWeb.GimnasioVictor.form.ReservasAdminForm;
import com.ProyWeb.GimnasioVictor.form.SessionForm;
import com.ProyWeb.GimnasioVictor.model.Entrenador;
import com.ProyWeb.GimnasioVictor.model.Reservas;
import com.ProyWeb.GimnasioVictor.repositorios.EntrenadorRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.ReservasRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControllerUsuario {

	@Autowired
	private EntrenadorRepositorio entrenadorRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private ReservasRepositorio reservasRepositorio;
	
	@GetMapping(path="/usuario/perfilUsuario")
	public String perfilUsuario(Model model, HttpServletRequest request) {
		HttpSession session= request.getSession();
		
		
		if(session.getAttribute("login")!=null) {
			String email = (String) session.getAttribute("email");
			Iterable<Reservas> itres = reservasRepositorio.buscarReservaEmail(email);
			List<Reservas> lista = new ArrayList<>();
			itres.forEach(lista::add);
			model.addAttribute("listaReserva",lista);
			model.addAttribute("usuario",session.getAttribute("login"));
			model.addAttribute("email",session.getAttribute("email"));
			
			return "/usuario/perfilUsuario";
		}else {
			return "/index";
		}
		
	}
	@PostMapping(path="/usuario/perfilUsuario")
	public String cerrarSesion(RegistroForm form,SessionForm des,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(des.getDesconecta()!=null) {
			session.removeAttribute("login");
			session.removeAttribute("email");
			return"redirect:/index";
		}
		else {
			return "/usuario/perfilUsuario";
		}
	}
	@GetMapping(path="/usuario/CrearReservaUsu")
	public String crearReserva(Model modelo,ReservasAdminForm form, HttpServletRequest request) {
		HttpSession session= request.getSession();		
		if(session.getAttribute("login")!=null) {
			Iterable<Entrenador> itEntrenadores = entrenadorRepositorio.findAll();
			List<Entrenador> listaEntrenadores = new ArrayList<>();
			itEntrenadores.forEach(listaEntrenadores::add);
			modelo.addAttribute("listaEntrenadores", listaEntrenadores);
			modelo.addAttribute("usuario",session.getAttribute("login"));
			return"/usuario/CrearReservaUsu";
		}else {
			return "/index";
		}
	}
	@PostMapping(path = "/usuario/CrearReservaUsu")
	public String CrearReservaAd( ReservasAdminForm form, Model modelo,HttpServletRequest request) {
		
		Reservas res = new Reservas(form.getFechaReserva(), usuarioRepositorio.findByemail(form.getEmail()),
				entrenadorRepositorio.findBydni(form.getDni()));
		reservasRepositorio.save(res);
		modelo.addAttribute("nuevo", "Reserva con id: " + res.getId() + " guardada con éxito");
		return "redirect:/usuario/perfilUsuario";
	}
}
