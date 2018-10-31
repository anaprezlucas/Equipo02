package com.uclm.equipo02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;



@Controller



public class HomeController {
	
	

	private final String usuarioServ = "usuario/";
	private final String usuario_login = "usuario/login";
	private final String usuario_conect = "usuarioConectado";
	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";
	private final String welcome = "bienvenido";
	private final String alert = "alerta";
	
	UsuarioDaoImplement userDao = new UsuarioDaoImplement();
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/***
	 * 
	 * @method ejecucion cuando pulsamos el boton login
	 *
	 */
	
	
	//@RequestMapping(value = "/login", method = RequestMethod.POST)
	//public void iniciarSesion(HttpServletRequest request, Model model) throws Exception {
	//	String cadenaUrl = usuarioServ;
	//	UsuarioDaoImplement userDao = new UsuarioDaoImplement();
	//	Usuario user = new Usuario();
	//	user.setNombre("Rodrigo");
	//	user.setPassword("1234");
	//	user.setEmail("rodrigo@gmail.com");
	//	user.setRol("empleado");
		
	//	try {
	//		userDao.insert(user);
	//	} catch (Exception e) {
			
	//	}
		//return cadenaUrl += "login";
	//}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String iniciarSesion(HttpServletRequest request, Model model) throws Exception {
		//String cadenaUrl = usuarioServ;
		String nombre = request.getParameter("txtUsuarioNombre");
		String password = request.getParameter("txtUsuarioPassword");
		if (nombre.equals("") || password.equals("")) {
			model.addAttribute("alerta", "Por favor rellene los campos");
			return "login";
		}
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPassword(password);
		
		if (userDao.login(usuario) && request.getSession().getAttribute(usuario_conect) == null) {
			request.getSession().setAttribute(usuario_conect, usuario);
			return "welcome";
		}else {

		model.addAttribute("alerta", "Usuario y/o clave incorrectos");
		return "login";
		}
	}
	public ModelAndView cambiarVista(String nombreVista) {
		ModelAndView vista = new ModelAndView(nombreVista);
		return vista;
	}
	
	
}
