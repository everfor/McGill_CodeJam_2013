import static org.junit.Assert.assertEquals;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logging.SignUp;

import org.junit.Test;

import frontendDatabase.PlayerFrontend;

/**
 * This class tests the methods required in order to Sign Up and create a
 * profile
 * 
 */
public class TestSignUp {

	// variables needed for testing
	JPasswordField passwordsTextField[] = new JPasswordField[] {
			new JPasswordField("12345678"), new JPasswordField("myTeam333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("TeamArjun3") };

	JPasswordField passwordsTextField2[] = new JPasswordField[] {
			new JPasswordField("12345678"), new JPasswordField("myTeam333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("TeamArjun3")

	};

	JPasswordField passwordsTextFieldFail[] = new JPasswordField[] {
			new JPasswordField("235"),
			new JPasswordField(
					"B myUsernameiiB myUsernameiiB myUsernameiiB myUsernameiiB B myUsernameii"),
			new JPasswordField(""), new JPasswordField(null)

	};

	JTextField usernameTextField[] = new JTextField[] {
			new JTextField("teamBlABlA"), new JTextField("Team33333"),
			new JTextField("My Team"), new JTextField("One More3") };

	JTextField usernameTextFieldFail[] = new JTextField[] {
			new JTextField("234"),
			new JTextField(
					"B myUsernameiiB myUsernameiiB myUsernameiiB myUsernameiiB B myUsernameii"),
			new JTextField(""), new JTextField(null)

	};

	JTextField usernameTextField2[] = new JTextField[] {
			new JTextField("A myUsernameii"), new JTextField("BBBBBBBBB22"),
			new JTextField("C-myUsernameii."), new JTextField("D_myUsernameii")

	};

	JTextField usernameTextFieldFail2[] = new JTextField[] {
			new JTextField(";;;"), new JTextField("!!!!"),
			new JTextField("*****"), new JTextField(":::@")

	};

	PlayerFrontend database = new PlayerFrontend();

	/**
	 * Tests whether the both passwords entered by the player match
	 * 
	 */
	@Test
	public void testPasswordMatch() {
		for (int i = 0; i < passwordsTextField.length; i++) {
			assertEquals(SignUp.passwordMatch(passwordsTextField[i],
					passwordsTextField2[i]), true);
			assertEquals(SignUp.passwordMatch(passwordsTextField[i],
					passwordsTextField2[3 - i]), false);
		}
	}

	/**
	 * Tests whether the password entered by the player are of the correct
	 * length
	 * 
	 */
	@Test
	public void testPasswordLength() {

		for (int i = 0; i < passwordsTextField.length; i++) {
			assertEquals(SignUp.passwordLength(passwordsTextField[i]), true);
			assertEquals(SignUp.passwordLength(passwordsTextFieldFail[i]),
					false);

		}
	}

	/**
	 * Tests whether the username entered by the player are of the correct
	 * length
	 * 
	 */
	@Test
	public void testUsernameLength() {
		for (int i = 0; i < passwordsTextField.length; i++) {
			assertEquals(SignUp.usernameLength(usernameTextField[i]), true);
			assertEquals(SignUp.usernameLength(usernameTextFieldFail[i]), false);

		}
	}

	/**
	 * Tests whether the username entered by the player contains any invalid
	 * characters
	 * 
	 */
	@Test
	public void testInvalidCharacters() {
		for (int i = 0; i < passwordsTextField.length; i++) {
			assertEquals(SignUp.invalidCharacters(usernameTextField2[i]), false);
			assertEquals(SignUp.invalidCharacters(usernameTextFieldFail2[i]),
					true);

		}
	}

}