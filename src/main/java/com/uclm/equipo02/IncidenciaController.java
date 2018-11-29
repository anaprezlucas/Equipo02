package com.uclm.equipo02;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Incidencia;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.DAOFichaje;
import com.uclm.equipo02.persistencia.DAOIncidencia;


@Controller
public class IncidenciaController {
	
	private final String usuario_conect = "usuarioConectado";
	DAOIncidencia incidenciaDao = new DAOIncidencia();
	
	@RequestMapping(value = "/crearIncidencia", method = RequestMethod.POST)
	public String crearIncidencia(HttpServletRequest request, Model model) throws Exception {
		
		Usuario usuario;
	    usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
	   
		String nombreUsuario = usuario.getNombre();
		String dniUsuario = usuario.getDni();
		String categoria = request.getParameter("listaTiposIncidencia");
		String fechaCreacion =(java.time.LocalDate.now()).toString();
		String descripcion = request.getParameter("textoIncidencia");
		String estado = "Pendiente";
		String comentarioGestor = "";
		
		Incidencia incidencia = new Incidencia(nombreUsuario, dniUsuario, categoria, descripcion, estado, 
				fechaCreacion, comentarioGestor);
		
		try {
			incidenciaDao.insert(incidencia);
		} catch (Exception e) {

		}
		
		return "interfazCrearIncidencia";
	}
	
	
	
	@RequestMapping(value = "listarIncidenciasGestor", method = RequestMethod.GET)
	public String listarIncidenciasGestor(HttpServletRequest request, Model model) {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);

		String dni = usuario.getDni();
		String fecha1= request.getParameter("fecha1");
		String fecha2= request.getParameter("fecha2");

		if(!incidenciaDao.existeIncidenciasEspera()) {
			model.addAttribute("nullIncidencia","No existe ning&uacutena incidencia en estado de espera");
			return "resolverIncidencia";
		}else {
			List<Document> listaIncidenciasGestor =incidenciaDao.getIncidenciasGestor();
			model.addAttribute("listaIncidencias", listaIncidenciasGestor);
			return "resolverIncidencia";
		}
	}

	
	
	@RequestMapping(value = "/RECrearIncidencia", method = RequestMethod.GET)
	public ModelAndView irIncidencias() {
		return new ModelAndView("interfazCrearIncidencia");
	}
	
	@RequestMapping(value = "/REResolverIncidencia", method = RequestMethod.GET)
	public ModelAndView REResolverIncidencia() {
		return new ModelAndView("resolverIncidencia");
	}
}
