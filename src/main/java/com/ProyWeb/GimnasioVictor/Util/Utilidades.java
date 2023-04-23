package com.ProyWeb.GimnasioVictor.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ProyWeb.GimnasioVictor.Pojo.EntrenadorPojo;
import com.ProyWeb.GimnasioVictor.Pojo.EntrenadoresPojo;
import com.ProyWeb.GimnasioVictor.Pojo.ReservaPojo;
import com.ProyWeb.GimnasioVictor.Pojo.ReservasPojo;
import com.ProyWeb.GimnasioVictor.Pojo.UsuarioPojo;
import com.ProyWeb.GimnasioVictor.Pojo.UsuariosPojo;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class Utilidades {
	private static String ruta_fich="src/main/resources/static/xmlResources/";
	
	
	public static List<UsuarioPojo> cargarUsuarios(MultipartFile file)throws JAXBException{
		if(!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(ruta_fich+"usuarios.xml");
				Files.write(path,bytes);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JAXBContext contexto = JAXBContext.newInstance(UsuariosPojo.class);
        Unmarshaller um = contexto.createUnmarshaller();
        UsuariosPojo au = (UsuariosPojo) um.unmarshal(new File("src/main/resources/static/xmlResources/usuarios.xml"));
        List<UsuarioPojo> aux=(List<UsuarioPojo>) au.getUsuario();
         return aux;
	}
	
	public static List<EntrenadorPojo> cargarEntrenadores(MultipartFile file)throws JAXBException{
		if(!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(ruta_fich+"entrenadores.xml");
				Files.write(path,bytes);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JAXBContext contexto = JAXBContext.newInstance(EntrenadoresPojo.class);
        Unmarshaller um = contexto.createUnmarshaller();
        EntrenadoresPojo au = (EntrenadoresPojo) um.unmarshal(new File("src/main/resources/static/xmlResources/entrenadores.xml"));
        List<EntrenadorPojo> aux=(List<EntrenadorPojo>) au.getEntrenador();
         return aux;
	}
	public static List<ReservaPojo> cargarReservas(MultipartFile file)throws JAXBException{
		if(!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(ruta_fich+"reservas.xml");
				Files.write(path,bytes);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JAXBContext contexto = JAXBContext.newInstance(ReservasPojo.class);
        Unmarshaller um = contexto.createUnmarshaller();
        ReservasPojo au = (ReservasPojo) um.unmarshal(new File("src/main/resources/static/xmlResources/reservas.xml"));
        List<ReservaPojo> aux=(List<ReservaPojo>) au.getReserva();
         return aux;
	}
	public void CrearJsonObjeto(Object objeto, String ruta,HttpServletResponse response) throws IOException {
		 Path path = Paths.get("src/main/resources/static/jsonResources/"+ruta+".json");
	        try {Files.write(path, (new GsonBuilder().setPrettyPrinting().create().toJson(objeto)).getBytes());
	           
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	}
	
	public String LeerJson(String valor) {
		String a,json="";
		try {
			BufferedReader bufer = new BufferedReader(new FileReader("src/main/resources/static/jsonResources/" +valor+".json"));
			a = bufer.readLine();
			while (a != null) {
				json += a;
				a = bufer.readLine();
			}
			bufer.close();
		}catch (IOException ex) {
            
        }
		return json;
	}
	

}
