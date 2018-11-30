package com.uclm.equipo02;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		String estado = "En espera";
		String comentarioGestor = "";
		
		Incidencia incidencia = new Incidencia(nombreUsuario, dniUsuario, categoria, descripcion, estado, 
				fechaCreacion, comentarioGestor);
		
		try {
			incidenciaDao.insert(incidencia);
		} catch (Exception e) {

		}
		
		return "interfazCrearIncidencia";
	}
	
	@RequestMapping(value = "seleccionarIncidencia", method = RequestMethod.GET)
	public String seleccionarIncidencia(HttpServletRequest request, Model model) {
		
		
		String idIncidencia=request.getParameter("idI");
		ObjectId id=new ObjectId(idIncidencia);
		
	
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		
		
		Incidencia inci = incidenciaDao.buscarIncidenciaID(id);
		model.addAttribute("seleccionadaInci", inci); 
		//Creacion de lista de incidencias de nuevo
		List<Document> listaIncidenciasGestor =incidenciaDao.getIncidenciasGestor();
		model.addAttribute("listaIncidencias", listaIncidenciasGestor);
		
		
		
		
		return "resolverIncidencia";

	
	}
	
	
	@RequestMapping(value = "resolverIncidencia", method = RequestMethod.GET)
	public String resolverIncidencia(HttpServletRequest request, Model model) {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String texto=request.getParameter("textoGestor");
		
		String idIncidencia=request.getParameter("idSeleccionada");
		ObjectId id=new ObjectId(idIncidencia);
		
		Incidencia resuelta=incidenciaDao.resolverIncidencia(id,texto);
		
		incidenciaDao.updateIncidencia(resuelta);
		
		//Creacion de lista de incidencias de nuevo
		List<Document> listaIncidenciasGestor =incidenciaDao.getIncidenciasGestor();
		model.addAttribute("listaIncidencias", listaIncidenciasGestor);
	
		return "resolverIncidencia";

	
	}
	
	@RequestMapping(value = "denegarIncidencia", method = RequestMethod.GET)
	public String denegarIncidencia(HttpServletRequest request, Model model) {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String texto=request.getParameter("textoGestor");
		String idIncidencia=request.getParameter("idSeleccionada");
		ObjectId id=new ObjectId(idIncidencia);
		
		Incidencia denegada=incidenciaDao.denegarIncidencia(id,texto);
		incidenciaDao.updateIncidencia(denegada);

		//Creacion de lista de incidencias de nuevo
		List<Document> listaIncidenciasGestor =incidenciaDao.getIncidenciasGestor();
		model.addAttribute("listaIncidencias", listaIncidenciasGestor);

		return "resolverIncidencia";

	
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
