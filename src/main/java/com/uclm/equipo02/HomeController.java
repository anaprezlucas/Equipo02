package com.uclm.equipo02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.mail.MailSender;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;



@Controller



public class HomeController {



	private final String usuarioServ = "usuario";
	private final String usuario_login = "login";
	private final String usuario_conect = "usuarioConectado";
	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";
	private final String welcome = "welcome";
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
			model.addAttribute(alert, "Por favor rellene los campos");
			return usuario_login;
		}
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPassword(password);

		if(nombre.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			request.getSession().setAttribute(usuario_conect, usuario);
			return "admin";
		}else if (userDao.login(usuario) && request.getSession().getAttribute(usuario_conect) == null) {
			request.getSession().setAttribute(usuario_conect, usuario);
			return "fichajes";
		}else {

			model.addAttribute(alert, "Usuario y/o clave incorrectos");
			return usuario_login;
		}
	}
	public ModelAndView cambiarVista(String nombreVista) {
		ModelAndView vista = new ModelAndView(nombreVista);
		return vista;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpServletRequest request) throws Exception {
		HttpSession sesion = request.getSession();

		System.out.println("Sesion antes de invalidar: " + sesion);
		sesion.invalidate();
		System.out.println("Invalidamos la sesion: " + sesion);

		return cambiarVista(usuario_login);
	}
	@RequestMapping(value = "/crearUsuario", method = RequestMethod.POST)
	public String crearUsuario(HttpServletRequest request, Model model) throws Exception {

		String mail = request.getParameter("txtUsuarioEmail");
		String nombre = request.getParameter("txtUsuarioNombre");
		String rol = request.getParameter("listaRoles");
		String pass = passRandom();
		if (mail.equals("") || nombre.equals("") || rol.equals("")) {
			//model.addAttribute(alert, "Por favor rellene los campos");

		}
		//UsuarioDaoImplement userDao = new UsuarioDaoImplement();
		Usuario user = new Usuario();
		user.setNombre(nombre);
		user.setPassword(pass);
		user.setEmail(mail);
		user.setRol(rol);

		try {
			userDao.insert(user);
		} catch (Exception e) {

		}
		
		String destinatario =  "alguien@servidor.com"; //A quien le quieres escribir.
	    String asunto = "Contraseña por defecto";
	    String cuerpo = "Hola " + nombre + "! \nLa contraseña por defecto es la siguiente:\n" + pass
	    		+"\nUn Saludo+\nInTime Corporation";

	    MailSender mailSender = new MailSender();
	    mailSender.enviarConGMail(mail, asunto, cuerpo);

		return usuario_login;
	}

	public String passRandom() {
		char[] elementos={'0','1','2','3','4','5','6','7','8','9' ,
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		char[] conjunto = new char[10];
		String pass;

		for(int i=0;i<10;i++){
			int el = (int)(Math.random()*62);
			conjunto[i] = (char)elementos[el];
		}
		return pass = new String(conjunto);
	}
}
