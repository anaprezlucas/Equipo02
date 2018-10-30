package com.uclm.equipo02.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.BsonString;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.uclm.equipo02.modelo.Usuario;

public class UsuarioDaoImplement implements UsuarioDao{

	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";

	public UsuarioDaoImplement() {
		super();
	}
	//Inserta un nuevo usuario en la BBDD
	public void insert(Usuario usuario) throws Exception {
		if(!selectNombre(usuario)) {
			BsonDocument bso = new BsonDocument();
			bso.append(name, new BsonString(usuario.getNombre()));
			bso.append(password, new BsonString(usuario.getPassword()));
			bso.append(email, new BsonString(usuario.getEmail()));
			bso.append(rol, new BsonString(usuario.getRol()));
			MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
			usuarios.insertOne(bso);
		}else
			throw new Exception("Cuenta existente");
	}
	//Devuelve un true si existe y false si no existe
	private boolean selectNombre(Usuario usuario) {
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson == null) {
			return false;
		}
		return true;
	}

	//Obtener todos los usuarios
	private MongoCollection<BsonDocument> obtenerUsuarios() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios = broker.getCollection("Usuarios");
		return usuarios;
	}

	@Override
	//Devuelve los usuarios que no son administradores
	public List<Usuario> list() {
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find();
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name).getValue();
			//if(administradorDao.selectNombre(nombre)==null)retorno.add(new Usuario(nombre));
		}
		return retorno;
	}

	//Borrar usuario
	public void delete (Usuario usuario){
		List<Usuario> todos=selectAll();
		BsonDocument bso = new BsonDocument();
		bso.append(name, new BsonString(usuario.getNombre()));
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		usuarios.deleteOne(bso);
	}
	//Devuelve una lista de todos los usuarios
	public List<Usuario> selectAll() {
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		FindIterable<BsonDocument> resultado=usuarios.find();
		String nombre;
		BsonDocument usuario;
		Iterator<BsonDocument> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name).getValue();
			retorno.add(new Usuario(nombre));
		}
		return retorno;
	}
	//Actualizar nombre de usuario
	public void updateNombre(String antiguo, String nuevo) {
		List <Usuario> todos=selectAll();
		BsonString nombreAnt=new BsonString(antiguo);
		BsonString nombreNue=new BsonString(nuevo);
		Iterator <Usuario> it=todos.iterator();
		Usuario aux;
		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		FindIterable<BsonDocument> resultado;
		BsonDocument usuario;
		BsonDocument actualizacion = null;

		while(it.hasNext()) {
			aux=it.next();
			criterio.append(name, new BsonString(aux.getNombre()));
			resultado=usuarios.find(criterio);
			usuario = resultado.first();
			usuarios.findOneAndUpdate(usuario, actualizacion);
		}

		usuarios = obtenerUsuarios();
		criterio = new BsonDocument();
		criterio.append(name, nombreAnt);
		resultado=usuarios.find(criterio);
		usuario = resultado.first();
		actualizacion= new BsonDocument("$set", new BsonDocument(name, nombreNue));
		usuarios.findOneAndUpdate(usuario, actualizacion);
	}
	@Override
	public void update(String nombre, String pwdAntigua, String pwdNueva) {
		// TODO Auto-generated method stub

	}
	public boolean login(Usuario usuario) {

		MongoCollection<BsonDocument> usuarios = obtenerUsuarios();
		BsonDocument criterio = new BsonDocument();
		criterio.append(name, new BsonString(usuario.getNombre()));
		criterio.append(password, new BsonString(usuario.getPassword()));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuarioBson = resultado.first();
		if (usuarioBson==null) {
			return false;
		}
		return true;
	}


}