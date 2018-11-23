package com.uclm.equipo02.persistencia;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.uclm.equipo02.modelo.Usuario;

public class DAOAdmin {
	
	
	
	public MongoCollection<Document> getUsuarios() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios = broker.getCollection("Usuarios");
		return usuarios;
	}

	
	
	public Usuario buscarUsuarioEmail(String email) {
		Usuario user = new Usuario();
		
		Document documento = new Document();
		MongoCursor<Document> elementos = getUsuarios().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
				if(documento.get("email").toString().equalsIgnoreCase(email)) {
					user.setEmail(email);
					user.setNombre(documento.get("nombre").toString());
					user.setPassword(documento.get("pwd").toString());
					user.setRol(documento.get("rol").toString());
					
				}
		}
		
		return user;
	}
	
	
	public boolean existeUser(String email) {
		boolean bool=false;
		Document documento = new Document();
		MongoCursor<Document> elementos = getUsuarios().find().iterator();
		while(elementos.hasNext()) {
			documento = elementos.next();
				if(documento.get("email").toString().equalsIgnoreCase(email)) {
					bool=true;
					System.out.println("CHECK BOOL IF"+bool);
				}
				
		}
		System.out.println("CHECK BOOL RETURN"+bool);
		return bool;
		
	}
	
	
	
}
