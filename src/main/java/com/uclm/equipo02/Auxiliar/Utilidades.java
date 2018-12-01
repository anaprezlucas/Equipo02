package com.uclm.equipo02.Auxiliar;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.bson.BsonString;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.MongoBroker;




public class Utilidades {


	private static String key = "InTimeQQ12345678";
	private static String initVector = "RandomInitVector";



	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return null;
	}


	public static String decrypt(String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}
/*
	public static String encrypt_md5(String password) {
		String pass_md5;
		byte[] thedigest = null;

		try {
			byte[] bytesOfMessage = password.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("SHA-384");
			thedigest = md.digest(bytesOfMessage);
			pass_md5 = DatatypeConverter.printHexBinary(thedigest).toLowerCase();
		}catch(Exception e)
		{
			return "error";
		}
		return pass_md5;
	}
*/

	/******Cambiar a return string y poner ese string  en una alerta en el updatePWD del controller y en el jsp de updatePWD******/
	public static boolean seguridadPassword(String pwd){
		
		boolean bool=true;
		if (pwd.length() < 8)
			return false;
		boolean caracter = false;
		boolean numero = false;
		char c;
		int size = pwd.length();
		for (int i = 0; i < size; i++) {
			c = pwd.charAt(i);
			if (Character.isDigit(c)) {
				numero = true;
			}
			if (Character.isLetter(c)) {
				caracter = true;
			}
		}
		if (!caracter || !numero) {
			bool=false;
		}
			return bool;
	
	}
	
	public static boolean comprobarPwd(String dni, String pwd, String nuevaPwd) {
		MongoCollection<Document> pwds = getContrasenas();
		Document criterio = new Document();
		criterio.append("pwd", new BsonString(encrypt(nuevaPwd)));
		criterio.append("dni", dni);
		FindIterable<Document> resultado=pwds.find(criterio);
		Document usuarioBson = resultado.first();
		//Document bso = new Document();
		if (usuarioBson==null) {
			return true;
		}else {
			return false;
		}
	
	}
	/*
	 * public List<Usuario> list() {
		MongoCollection<Document> usuarios = obtenerUsuarios();
		FindIterable<Document> resultado=usuarios.find();
		String nombre;
		Document usuario;
		Iterator<Document> lista=resultado.iterator();
		List<Usuario> retorno=new ArrayList<Usuario>();
		while(lista.hasNext()) {
			usuario=lista.next();
			nombre=usuario.getString(name);
			//if(administradorDao.selectNombre(nombre)==null)retorno.add(new Usuario(nombre));
		}
		return retorno;
	}
	 */

	public static MongoCollection<Document> getContrasenas() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> incidencias = broker.getCollection("Contrasenas");
		return incidencias;
	}






}
