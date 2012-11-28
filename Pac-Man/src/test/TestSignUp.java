package test;

import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logging.*;


import org.junit.Test;

import frontendDatabase.*;
/**
 * This class tests the methods required in order to Sign Up and create a profile
 * 
 * @author Arjun
 * 
 */
public class TestSignUp {
	

	JPasswordField passwordsTextField[] =new JPasswordField[]{
			new JPasswordField ("A myUsernameii"),
			new JPasswordField ("B myUsernameii"),
			new JPasswordField ("C myUsernameii"),
			new JPasswordField ("D myUsernameii")
	};
	
	JPasswordField passwordsTextField2[] =new JPasswordField[]{
			new JPasswordField ("A myUsernameii"),
			new JPasswordField ("B myUsernameii"),
			new JPasswordField ("C myUsernameii"),
			new JPasswordField ("D myUsernameii")
			
			
	};
	
	JPasswordField passwordsTextFieldFail[] =new JPasswordField[]{
			new JPasswordField ("235"),
			new JPasswordField ("B myUsernameiiB myUsernameiiB myUsernameiiB myUsernameiiB B myUsernameii"),
			new JPasswordField (""),
			new JPasswordField (null)
			
			
	};
	
	JTextField usernameTextField[] =new JTextField[]{
			new JTextField ("A myUsernameii"),
			new JTextField ("B myUsernameii"),
			new JTextField ("C myUsernameii"),
			new JTextField ("D myUsernameii")
			
			
	};
	
	JTextField usernameTextFieldFail[] =new JTextField[]{
			new JTextField ("234"),
			new JTextField ("B myUsernameiiB myUsernameiiB myUsernameiiB myUsernameiiB B myUsernameii"),
			new JTextField (""),
			new JTextField (null)
			
			
	};
	
	JTextField usernameTextField2[] =new JTextField[]{
			new JTextField ("A myUsernameii"),
			new JTextField ("BBBBBBBBB22"),
			new JTextField ("C-myUsernameii."),
			new JTextField ("D_myUsernameii")
			
			
	};
	
	
	JTextField usernameTextFieldFail2[] =new JTextField[]{
			new JTextField (";;;"),
			new JTextField ("!!!!"),
			new JTextField ("*****"),
			new JTextField (":::@")
			
			
	};
	
	PlayerFrontend database = new PlayerFrontend(); 
	
	/**
	 * Tests whether the both passwords entered by the player match
	 * 
	 */
	@Test
	public void testPasswordMatch() {
		for (int i =0; i<passwordsTextField.length; i++){
		assertEquals(SignUp.passwordMatch(passwordsTextField[i], passwordsTextField2[i]), true);
		assertEquals(SignUp.passwordMatch(passwordsTextField[i], passwordsTextField2[3-i]), false);
		}
	}

	/**
	 * Tests whether the password entered by the player are of the correct length
	 * 
	 */
	@Test
	public void testPasswordLength() {
	
	for (int i =0; i<passwordsTextField.length; i++){
		assertEquals(SignUp.passwordLength(passwordsTextField[i]), true);
		assertEquals(SignUp.passwordLength(passwordsTextFieldFail[i]), false);
		
	}
	}
	/**
	 * Tests whether the username entered by the player are of the correct length
	 * 
	 */
	@Test
	public void testUsernameLength() {
		for (int i =0; i<passwordsTextField.length; i++){
			assertEquals(SignUp.usernameLength(usernameTextField[i]), true);
			assertEquals(SignUp.usernameLength(usernameTextFieldFail[i]), false);
			
		}
	}
	/**
	 * Tests whether the username entered by the player contains any invalid characters 
	 * 
	 */
	@Test
	public void testInvalidCharacters() {
		for (int i =0; i<passwordsTextField.length; i++){
			assertEquals(SignUp.invalidCharacters(usernameTextField2[i]), false);
			assertEquals(SignUp.invalidCharacters(usernameTextFieldFail2[i]), true);
			
		}
	}

//	@Test
//	public void testSignupErrors() {
//		
//		database.addNewPlayer(usernameTextFields[0], passwordsTextField[0], securityQuestionTextField[0], securityAnswerTextField[0]);
//		
//		
//		for (int i =0; i<4; i++){
//			assertSame(SignUp.signupErrors(usernameTextFields[0], passwordsTextField[0], securityQuestionTextField[0], securityAnswerTextField[0], passwordsTextField[0]), SignUp.giveErrorMessage());
//		
//			
//		}
//	}
	

//	@Test
//	public void testGiveErrorMessage() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRegister() {
//		fail("Not yet implemented");
//	}

}
