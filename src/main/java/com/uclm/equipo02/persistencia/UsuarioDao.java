package com.uclm.equipo02.persistencia;
import java.util.List;

import com.uclm.equipo02.modelo.*;

public interface UsuarioDao{
	public void insert (Usuario usuario) throws Exception;
	public List<Usuario> list() ;
	public void delete (Usuario usuario);
	public void update(String nombre, String pwdAntigua, String pwdNueva);
}