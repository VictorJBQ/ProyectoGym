package com.ProyWeb.GimnasioVictor.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ProyWeb.GimnasioVictor.form.InicioForm;
import com.ProyWeb.GimnasioVictor.form.RegistroForm;
import com.ProyWeb.GimnasioVictor.model.Entrenador;
import com.ProyWeb.GimnasioVictor.model.Roles;
import com.ProyWeb.GimnasioVictor.model.Usuario;
import com.ProyWeb.GimnasioVictor.repositorios.EntrenadorRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.RolesRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControllerPrincipal {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private RolesRepositorio rolesRepositorio;

	
	@Bean //Cargamos independientemente 
	public CommandLineRunner demo(UsuarioRepositorio repositorio,RolesRepositorio roles,EntrenadorRepositorio entrenador) {
		return (args)->{
			roles.save(new Roles("Administrador"));
			roles.save(new Roles("Usuario"));
			//Añadimos los datos a repositorio que esta vacio
			//Los creamos por defecto al inicar la aplicacion
			repositorio.save(new Usuario("Admin","Admin","admin@gmail.com","admin",roles.findByRol("Administrador")));
			entrenador.save(new Entrenador("78457544Y", "David","Hordoñez"));
			entrenador.save(new Entrenador("87874485H","Killian","Zamudio"));
		
			

		};
	}
	
	@GetMapping(path="/index")
	public String inicio(Model modelo) {
		return "/index";
	}
	@GetMapping(path="/Login")
	public String Login(InicioForm form, Model modelo,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(session.getAttribute("login")!=null) {
			modelo.addAttribute("usuario", session.getAttribute("login"));
			modelo.addAttribute("email",session.getAttribute("email"));
			return"/usuario/perfilUsuario";
		}
		else if( session.getAttribute("adminLogin")!=null) {
			return "/admin/listadoUsuarios";
			
		}
		return"/Login";
	}
	@PostMapping(path="/Login")
	public String LoginForm(@Valid InicioForm form,HttpServletRequest request, BindingResult bindingResult,Model modelo){
		HttpSession session= request.getSession();
		
		Usuario u1 = usuarioRepositorio.findByemail(form.getEmail());
		if(u1==null) {
			modelo.addAttribute("Error","Email no existe");
			return"/Login";
		}
		if(!u1.getPassword().equals(form.getContra())){
			modelo.addAttribute("Error","Contraseña Incorrecta");
			return"/Login";
		}
		if(u1.getRoles().getRol().equals("Administrador")){
			session.setAttribute("admin", "administrador");
			return "redirect:/admin/listadoUsuarios";
		}else {
			
			modelo.addAttribute("usuario", u1);
			session.setAttribute("login", u1);
			session.setAttribute("email", u1.getEmail());
		return "redirect:/usuario/perfilUsuario";}
	}
	@GetMapping(path="/Registro")
	public String regitroGet(RegistroForm form, Model modelo,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(session.getAttribute("login")!=null) {
			modelo.addAttribute("usuario", session.getAttribute("login"));
			modelo.addAttribute("email",session.getAttribute("email"));
			return"/usuario/perfilUsuario";
		}
		else if( session.getAttribute("adminLogin")!=null) {
			return "/admin/listadoUsuarios";
			
		}else {
			
		return "/Registro";}
	}
	@PostMapping(path="/Registro")
	public String registroPost(RegistroForm form, Model modelo,HttpServletRequest request) {
		HttpSession session= request.getSession();
		if(usuarioRepositorio.findByemail(form.getEmail())==null) {
		Usuario nuevo=	new Usuario(form.getNombre(),form.getApellido(),form.getEmail(),form.getpassword(),rolesRepositorio.findByRol(form.getRol()));
		usuarioRepositorio.save(nuevo);
		modelo.addAttribute("usuario",nuevo);
		session.setAttribute("email", form.getEmail());
		session.setAttribute("login", nuevo);
		return "/usuario/perfilUsuario";
		}
		else {
			modelo.addAttribute("fallo","Email ya existente");
			return "/Registro";
		}
	}
	
	
	
	
	

}
