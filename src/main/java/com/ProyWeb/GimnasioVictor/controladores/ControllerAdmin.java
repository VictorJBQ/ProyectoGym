package com.ProyWeb.GimnasioVictor.controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ProyWeb.GimnasioVictor.Pojo.EntrenadorPojo;
import com.ProyWeb.GimnasioVictor.Pojo.ReservaPojo;
import com.ProyWeb.GimnasioVictor.Pojo.UsuarioPojo;
import com.ProyWeb.GimnasioVictor.Util.Utilidades;
import com.ProyWeb.GimnasioVictor.form.RegistroEntrenadorForm;
import com.ProyWeb.GimnasioVictor.form.RegistroForm;
import com.ProyWeb.GimnasioVictor.form.ReservasAdminForm;
import com.ProyWeb.GimnasioVictor.form.SessionForm;
import com.ProyWeb.GimnasioVictor.model.Entrenador;
import com.ProyWeb.GimnasioVictor.model.Reservas;
import com.ProyWeb.GimnasioVictor.model.Roles;
import com.ProyWeb.GimnasioVictor.model.Usuario;
import com.ProyWeb.GimnasioVictor.repositorios.EntrenadorRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.ReservasRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.RolesRepositorio;
import com.ProyWeb.GimnasioVictor.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControllerAdmin {
	@Autowired
	private EntrenadorRepositorio entrenadorRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private ReservasRepositorio reservasRepositorio;
	@Autowired
	private RolesRepositorio rolesRepositorio;
	@Autowired
	private Utilidades util;

	String Email;
	
	@GetMapping(path = "/admin/RegistroUsuarioAdmin")
	public String regitroAdminGet(RegistroForm form, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			if(Email==null) {
				Iterable<Roles> itRoles = rolesRepositorio.findAll();
				List<Roles> listaRoles = new ArrayList<>();
				itRoles.forEach(listaRoles::add);
				session.setAttribute("listaRoles", listaRoles);
				modelo.addAttribute("listaRoles", session.getAttribute("listaRoles"));
			return "/admin/RegistroUsuarioAdmin";}
			else {
				
				modelo.addAttribute("email",Email);
				return"/admin/RegistroUsuarioAdmin";
			}
		} else {
			return "/index";
		}
	}

	@PostMapping(path = "/admin/RegistroUsuarioAdmin")
	public String registroAdminPost(RegistroForm form, Model modelo, HttpServletRequest request) {
		if(Email==null) {
		if (usuarioRepositorio.findByemail(form.getEmail()) == null) {
			usuarioRepositorio
					.save(new Usuario(form.getNombre(), form.getApellido(), form.getEmail(), form.getpassword(),rolesRepositorio.findByRol(form.getRol())));
			modelo.addAttribute("nuevo", "Usuario con email: " + form.getEmail() + " creado correctamente");
			return "redirect:/admin/listadoUsuarios";
		} else {
			modelo.addAttribute("fallo", "Email ya existente");
			return "/admin/RegistroUsuarioAdmin";
		}}
		else {
			Usuario a =usuarioRepositorio.findByemail(Email);
			a.setNombre(form.getNombre());
			a.setApellido(form.getApellido());
			a.setPassword(form.getpassword());
			a.setRoles(rolesRepositorio.findByRol(form.getRol()));
			usuarioRepositorio.save(a);
			return "redirect:/admin/listadoUsuarios";
		}
	}

	@GetMapping(path = "/admin/RegistroEntrenadores")
	public String regitroEntrenadorGet(RegistroEntrenadorForm form, HttpServletRequest request, Model modelo) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			return "/admin/RegistroEntrenadores";
		} else {
			return "redirect:/index";
		}
	}

	@PostMapping(path = "/admin/RegistroEntrenadores")
	public String registroEntrenadorPost(@Valid RegistroEntrenadorForm form, Model modelo, HttpServletRequest request) {
		if (entrenadorRepositorio.findBydni(form.getDni()) == null) {
			entrenadorRepositorio.save(new Entrenador(form.getDni(), form.getNombre(), form.getApellido()));
			modelo.addAttribute("nuevo", "Entrenador con Dni: " + form.getDni() + " registrado correctamente");
			return "redirect:/admin/listadoEntrenadores";
		} else {
			modelo.addAttribute("fallo", "Dni ya utilizado");
			return "/admin/RegistroEntrenadores";
		}
	}

	@GetMapping(path = "/admin/listadoEntrenadores")
	public String ListaEntrenadores(HttpServletRequest request, Model modelo) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			Iterable<Entrenador> itEntrenadores = entrenadorRepositorio.findAll();
			List<Entrenador> listaEntrenadores = new ArrayList<>();
			itEntrenadores.forEach(listaEntrenadores::add);
			session.setAttribute("listaEntrenadores", listaEntrenadores);
			modelo.addAttribute("listaEntrenadores", session.getAttribute("listaEntrenadores"));
			return "/admin/listadoEntrenadores";
		} else {
			return "redirect:/index";
		}
	}

	@GetMapping(path = "/admin/listadoUsuarios")
	public String ListaUsuarios(RegistroForm form, Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			Iterable<Usuario> itUsuario = usuarioRepositorio.findAll();
			List<Usuario> listausuario = new ArrayList<>();
			itUsuario.forEach(listausuario::add);
			if (form.getOpcion() == null) {
				listausuario = usuarioRepositorio.buscardiferenteAdmin("Administrador");
				Email=null;
				session.setAttribute("listaUsuario", listausuario);
				modelo.addAttribute("listaUsuario", session.getAttribute("listaUsuario"));

				return "/admin/listadoUsuarios";
			} else if(form.getOpcion().equals("delete")){
				Usuario a = usuarioRepositorio.findByemail(form.getEmail());
				usuarioRepositorio.delete(a);
				Iterable<Usuario> itUsuario2 = usuarioRepositorio.findAll();
				List<Usuario> listausuario2 = new ArrayList<>();
				itUsuario2.forEach(listausuario2::add);
				Usuario a2 = usuarioRepositorio.findByemail("admin@gmail.com");
				listausuario2.remove(a2);
				modelo.addAttribute("listaUsuario", listausuario2);
				return "/admin/listadoUsuarios";
			}else  {
				Iterable<Roles> itRoles = rolesRepositorio.findAll();
				List<Roles> listaRoles = new ArrayList<>();
				itRoles.forEach(listaRoles::add);
				session.setAttribute("listaRoles", listaRoles);
				modelo.addAttribute("listaRoles", session.getAttribute("listaRoles"));
				Usuario a = usuarioRepositorio.findByemail(form.getEmail());
				Email= form.getEmail();
				
				return"/admin/RegistroUsuarioAdmin";
			}
		} else {
			return "redirect:/index";
		}
	}
	@PostMapping(path="/admin/listadoUsuarios")
	public String cerrarSesion(RegistroForm form,SessionForm des,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(des.getDesconecta()!=null) {
			session.removeAttribute("admin");
			return"redirect:/index";
		}
		else {
			return "/admin/listadoUsuarios";
		}
	}

	@GetMapping(path = "/admin/listadoReservas")
	public String ListaReservas(Model modelo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			Iterable<Reservas> itReservas = reservasRepositorio.findAll();
			List<Reservas> listaReserva = new ArrayList<>();
			itReservas.forEach(listaReserva::add);
			modelo.addAttribute("listaReserva", listaReserva);
			return "/admin/listadoReservas";
		} else {
			return "redirect:/index";
		}
	}

	@GetMapping(path = "/admin/CrearReservaAdmin")
	public String CrearReservaAdmin(ReservasAdminForm form, HttpServletRequest request, Model modelo) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
		Iterable<Usuario> itUsuario = usuarioRepositorio.findAll();
		List<Usuario> listausuario = new ArrayList<>();
		itUsuario.forEach(listausuario::add);
		Iterable<Entrenador> itEntrenadores = entrenadorRepositorio.findAll();
		List<Entrenador> listaEntrenadores = new ArrayList<>();
		itEntrenadores.forEach(listaEntrenadores::add);
		modelo.addAttribute("listaEntrenadores", listaEntrenadores);
		modelo.addAttribute("listaUsuario", listausuario);
		return "/admin/CrearReservaAdmin";
		} else {
			return "redirect:/index";
		}
	}

	@PostMapping(path = "/admin/CrearReservaAdmin")
	public String CrearReservaAd(@Valid ReservasAdminForm form, Model modelo) {
		Reservas res = new Reservas(form.getFechaReserva(), usuarioRepositorio.findByemail(form.getEmail()),
				entrenadorRepositorio.findBydni(form.getDni()));
		reservasRepositorio.save(res);
		modelo.addAttribute("nuevo", "Reserva con id: " + res.getId() + " guardada con éxito");
		return "redirect:/admin/listadoReservas";
	}
	@GetMapping("/admin/CargarDatos")
	public String cargaDescarga(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			model.addAttribute("mensaje", model.getAttribute("mensaje"));

			return "/admin/CargarDatos";
		}
		return "redirect:/index";
	}
	@PostMapping(path = "/admin/CargarDatos")
	public String cargaDescargaPost(Model modelo, HttpServletRequest request,
			@RequestParam("file") MultipartFile file, @RequestParam("eleccion") String tabla) throws JAXBException {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			switch(tabla) {
			case "Entrenador":
				List<EntrenadorPojo> entrenadores = Utilidades.cargarEntrenadores(file);
				for(EntrenadorPojo aux : entrenadores) {
					Entrenador ent = new Entrenador(aux.getDni(),aux.getNombre(),aux.getApellido());
					entrenadorRepositorio.save(ent);
				}
				modelo.addAttribute("mensaje","Datos de :"+tabla+" insertado corretamente");
			
					break;
			case "Reservas":
				List<ReservaPojo> reservas = Utilidades.cargarReservas(file);
				for(ReservaPojo aux : reservas) {
					Reservas ent = new Reservas(LocalDate.parse(aux.getFechaRealizada()),LocalDate.parse(aux.getFechaReserva()),usuarioRepositorio.findByemail(aux.getUsuario_email()),entrenadorRepositorio.findBydni(aux.getEntrenador_dni()));
					reservasRepositorio.save(ent);
				}
				modelo.addAttribute("mensaje","Datos de :"+tabla+" insertado corretamente");
			
				break;
			case "Usuario":
				List<UsuarioPojo> usuarios = Utilidades.cargarUsuarios(file);
				for(UsuarioPojo aux : usuarios) {
					Usuario usu = new Usuario(aux.getNombre(),aux.getApellido(),aux.getEmail(),aux.getPassword(),rolesRepositorio.findByRol("Usuario"));
					usuarioRepositorio.save(usu);
				}
				modelo.addAttribute("mensaje","Datos de :"+tabla+" insertado corretamente");
			
				break;
			default:
				break;
			}
			return "redirect:/admin/CargarDatos";
		}
		return "redirect:/index";
		
	
	}
	@GetMapping(path = "/admin/DescargarDatosJson")
	public String descarga(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {

			return "/admin/DescargarDatosJson";
		}
		return "redirect:/index";
	}
	@PostMapping(path = "/admin/DescargarDatosJson")
	public ResponseEntity<byte[]>  descargaPost(Model model, HttpServletRequest request, @RequestParam("eleccion") String opcion ,HttpServletResponse response) throws IOException {
	
		switch(opcion) {
		case "Entrenador":
			Iterable <Entrenador> entre = entrenadorRepositorio.findAll();
			List<Entrenador> listaEntrenador = new ArrayList<>();
			entre.forEach(listaEntrenador::add);
			List<EntrenadorPojo> pojoEntrenador = new ArrayList<>();
			for(Entrenador aux : listaEntrenador) {
				EntrenadorPojo nuevo = new EntrenadorPojo(aux.getDni(),aux.getNombre(),aux.getApellido());
				pojoEntrenador.add(nuevo);
			}
			util.CrearJsonObjeto(pojoEntrenador, opcion,response);
			
			break;
		case "Reservas":
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Iterable <Reservas> reser = reservasRepositorio.findAll();
			List<Reservas> listaReservas = new ArrayList<>();
			reser.forEach(listaReservas::add);
			List<ReservaPojo> pojoReserva = new ArrayList<>();
			for(Reservas aux : listaReservas) {
				ReservaPojo nuevo = new ReservaPojo(aux.getFechaRealizada().format(formato),aux.getFechaReserva().format(formato),aux.getUsuario().getEmail(),aux.getEntrenador().getDni());
				pojoReserva.add(nuevo);
			}
			util.CrearJsonObjeto(pojoReserva, opcion,response);
			break;
			
		case "Usuario":
			Iterable <Usuario> usu = usuarioRepositorio.findAll();
			List<Usuario> listaUsuario = new ArrayList<>();
			usu.forEach(listaUsuario::add);
			List<UsuarioPojo> pojoUsuario = new ArrayList<>();
			for(Usuario aux : listaUsuario) {
				UsuarioPojo nuevo = new UsuarioPojo(aux.getNombre(),aux.getApellido(),aux.getEmail(),aux.getPassword());
				pojoUsuario.add(nuevo);
			}
			util.CrearJsonObjeto(pojoUsuario, opcion,response);
			break;
		default:
			break;
		}
		String json = util.LeerJson(opcion);
		byte[] datos = json.getBytes();
		HttpHeaders head = new HttpHeaders();
		head.setContentType(MediaType.APPLICATION_JSON);
		head.setContentDispositionFormData("attachment", opcion+".json");
		head.setContentLength(datos.length);
		return ResponseEntity.ok().headers(head).body(datos);
	}

}
