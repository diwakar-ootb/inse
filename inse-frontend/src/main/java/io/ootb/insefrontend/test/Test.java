package io.ootb.insefrontend.test;

import org.mindrot.jbcrypt.BCrypt;

public class Test {

	public static void main(String[] args) {
		String password = "password";
		System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
	}
}
