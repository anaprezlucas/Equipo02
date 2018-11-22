package com.uclm.equipo02;


import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.DAOFichaje;


import org.bson.Document;

@Controller
public class FichajeController {

	DAOFichaje fichajedao = new DAOFichaje();


	private final String usuario_conect = "usuarioConectado";
	private final String errorMessageAbrir = "errorMessageAbrir";
	private final String errorMessageCerrar = "errorMessageCerrar";
	private final String fichajes = "fichajes";
	private final String interfazAdministrador="interfazAdministrador";
	private final String alertaFichaje="alertaFichaje";

	@RequestMapping(value = "abrirFichaje", method = RequestMethod.POST)
	public String abrirFichaje(HttpServletRequest request, Model model) throws Exception {
		String hora;
		String fecha;

		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);


		hora=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();


		Fichaje fichaje = new Fichaje(usuario.getEmail(), fecha, hora,null,true);

		if(!fichajedao.validezAbierto(fichaje)) {///FUNCIONA PERO NO SALE EL MENSAJE
			model.addAttribute(errorMessageAbrir, "No puedes abrir otro fichaje, necesitas cerrar tu fichaje actual");

		}else {
			fichajedao.abrirFichaje(fichaje);
			model.addAttribute(alertaFichaje,"El usuario "+fichaje.getEmailFichaje()+" ha abierto un fichaje");
		}
		return fichajes;
	} 



	@RequestMapping(value = "cerrarFichaje", method = RequestMethod.POST)
	public String cerrarFichaje(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String fecha;
		fecha=(java.time.LocalDate.now()).toString();

		String horaentrada;
		horaentrada=fichajedao.getHoraEntrada(usuario.getEmail(),fecha);


		String horaactual;
		horaactual=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();

		Fichaje fichaje = new Fichaje(usuario.getEmail(), fecha,horaentrada,horaactual,false);;

		if(fichajedao.validezCerrado(fichaje)) {
			fichajedao.cerrarFichaje(usuario, fichaje);
			model.addAttribute(alertaFichaje,"El usuario "+fichaje.getEmailFichaje()+" ha cerrado un fichaje");


		}else {

			model.addAttribute(errorMessageCerrar, "No puedes cerrar ning&uacuten fichaje, necesitas fichar para cerrar un fichaje");
		}
		return fichajes;

	} 



	@RequestMapping(value = "listarFichajesEmpleado", method = RequestMethod.GET) 
	public String listarFichajesEmpleado(HttpServletRequest request, Model model) throws Exception {		
		Usuario usuario;

		usuario = (Usuario) request.getSession().getAttribute(usuario_conect); 
		String emailEmpleado = usuario.getEmail();


		List<Document> listaFich = new ArrayList<Document>();

		listaFich = usuario.getFichajesEmpleado(emailEmpleado);


		model.addAttribute("listafichajes", listaFich);

		return "fichajes";
		} 
	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * ****ADMIN FICHAJES**
	 * 
	 * 
	 * */
	
	
	@RequestMapping(value = "abrirFichajeAdmin", method = RequestMethod.POST)
	public String abrirFichajeAdmin(HttpServletRequest request, Model model) throws Exception {
		String hora;
		String fecha;

		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);


		hora=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();


		Fichaje fichaje = new Fichaje(usuario.getEmail(), fecha, hora,null,true);

		if(!fichajedao.validezAbierto(fichaje)) {///FUNCIONA PERO NO SALE EL MENSAJE
			model.addAttribute(errorMessageAbrir, "No puedes abrir otro fichaje, necesitas cerrar tu fichaje actual");

		}else {
			fichajedao.abrirFichaje(fichaje);
			model.addAttribute(alertaFichaje,"El usuario "+fichaje.getEmailFichaje()+" ha abierto un fichaje");
		}
		return interfazAdministrador;
	} 
	
	
	@RequestMapping(value = "cerrarFichajeAdmin", method = RequestMethod.POST)
	public String cerrarFichajeAdmin(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect);
		String fecha;
		fecha=(java.time.LocalDate.now()).toString();
		String horaentrada;
		horaentrada=fichajedao.getHoraEntrada(usuario.getEmail(),fecha);


		String horaactual;
		horaactual=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();

		Fichaje fichaje = new Fichaje(usuario.getEmail(), fecha,horaentrada,horaactual,false);;

		if(fichajedao.validezCerrado(fichaje)) {
			fichajedao.cerrarFichaje(usuario, fichaje);
			model.addAttribute(alertaFichaje,"El usuario "+fichaje.getEmailFichaje()+" ha cerrado un fichaje");


		}else {

			model.addAttribute(errorMessageCerrar, "No tienes ning&uacuten fichaje abierto, necesitas abrir un fichaje para poder cerrarlo");
		}
		return interfazAdministrador;

	} 
	
	
	
	
	@RequestMapping(value = "listarFichajesAdmin", method = RequestMethod.GET) 
	public String listarFichajesAdmin(HttpServletRequest request, Model model) throws Exception {		
		Usuario usuario;

		usuario = (Usuario) request.getSession().getAttribute(usuario_conect); 
		String emailEmpleado = usuario.getEmail();

		
		List<Document> listaFichAd = new ArrayList<Document>();

		listaFichAd = usuario.getFichajesEmpleado(emailEmpleado);
		
		model.addAttribute("listafichajesAd", listaFichAd);

		return interfazAdministrador;
		} 
	 
	
	/***Redireccion a gestionPwd***/
	
	
	@RequestMapping(value = "/gestionPwd", method = RequestMethod.GET)
	public ModelAndView gestionPwd(HttpServletRequest request) {
		Usuario usuario;
		usuario = (Usuario) request.getSession().getAttribute(usuario_conect); 
		request.setAttribute("nombreUser", usuario.getNombre());
		request.setAttribute("mailUser", usuario.getEmail());
		return new ModelAndView("gestionPwd");
	}



}
