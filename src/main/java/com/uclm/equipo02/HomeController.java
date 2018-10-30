package com.uclm.equipo02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.MongoBroker;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;


@Controller



public class HomeController {
	
	

	private final String usuarioServ = "usuario/";
	private final String usuario_login = "usuario/login";
	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase db;
	
	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";
	
	
	
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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void iniciarSesion(HttpServletRequest request, Model model) throws Exception {
		String cadenaUrl = usuarioServ;
		System.out.println("He pasado por el método del iniciarSesion");
		UsuarioDaoImplement userDao=  new UsuarioDaoImplement();
		Usuario user = new Usuario();
		user.setNombre("Rodrigo");
		user.setPassword("1234");
		user.setEmail("rodrigo@gmail.com");
		user.setRol("empleado");
		
		try {
			userDao.insert(user);
		} catch (Exception e) {
			System.out.println("estoy dentro de la excepción");
			System.out.println(e.toString());
		}
		//return cadenaUrl += "login";
	}
}
