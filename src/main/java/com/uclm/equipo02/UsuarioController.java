package com.uclm.equipo02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;

@Controller
public class UsuarioController {
	private final String alert = "alerta";
	private final String gestionPwd = "gestionPwd";
	private final String usuario_conect = "usuarioConectado";

	UsuarioDaoImplement userDao = new UsuarioDaoImplement();

	@RequestMapping(value = "/modificarPwd", method = RequestMethod.POST)
	public String modificarPwd(HttpServletRequest request, Model model) throws Exception {
		Usuario usuarioLigero = (Usuario) request.getSession().getAttribute(usuario_conect);
		
		String pwdNueva = request.getParameter("contrasenaNueva");
		String pwdNueva2 = request.getParameter("contrasenaNueva2");
		String nombre = userDao.devolverUser(usuarioLigero);
		
		Usuario usuario = userDao.selectNombre(nombre);
		if (usuario == null || !(pwdNueva.equals(pwdNueva2))) {
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			model.addAttribute(alert, "Datos incorrectos");
			return gestionPwd;
		}
		try {
	
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			return gestionPwd;
		}
		
		
		System.out.println(usuario.toString());
		usuario.setPassword(pwdNueva);
		userDao.updatePwd(usuario);
		HttpSession session = request.getSession();
		request.setAttribute("usuarioNombre", usuario.getNombre());
		request.setAttribute("usuarioEmail", usuario.getEmail());
		session.setAttribute("alertaModificarPerfilUsuario", "Mandando alerta modificar perfil usuario");
		return "fichajes";
	}
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView irFichajes() {
		return new ModelAndView("fichajes");
	}
	@RequestMapping(value = "/incidencias", method = RequestMethod.GET)
	public ModelAndView irIncidencias() {
		return new ModelAndView("fichajes");
	}
	

}