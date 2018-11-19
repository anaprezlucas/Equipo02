package com.uclm.equipo02.Auxiliar;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;




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






}
