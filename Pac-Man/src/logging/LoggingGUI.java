package logging;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.String;
import javax.swing.*;
public class LoggingGUI {
	JFrame masterPage;
	CardLayout pages;
	JPanel pagePanels;
	private JPanel login, signUpPage, recoverPage ;
	private JTextField usernameInput, newUsernameInput;
	private JPasswordField passwordInput,reenterPassword, newPasswordInput; 
	private JTextField securityQuestionInput, securityAnswerInput;
	private JLabel usernameLabel, passwordLabel, newUsernameLabel,newPasswordLabel, reenterPasswordLabel,securityQuestion, securityAnswer;
	private JButton loginButton, backToLogin,registerButton,signUp,recoverButton;
	public static void main(String[] args) {
		//Use the event dispatch thread for Swing components changeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){        
				new LoggingGUI();         
			}
		});
	}

	public LoggingGUI(){ 
		//main page
		masterPage = new JFrame();
		masterPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		masterPage.setTitle("Login");
		masterPage.setSize(500,500 );

		//Building the card layout to switch between pages
		pages = new CardLayout();
		pagePanels = new JPanel();
		pagePanels.setLayout(pages);
		pages.show(pagePanels, "login");

		//Create Login Panel
		login = new JPanel();
		//initialize all components to be displayed
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		usernameInput = new JTextField();
		passwordInput = new JPasswordField();
		loginButton = new JButton("Login");
		recoverButton = new JButton("Forgot Password?");
		//displays all components in login screen
		login.add(usernameLabel);
		login.add(usernameInput);
		login.add(passwordLabel);
		login.add(passwordInput);
		login.add(loginButton);
		login.add(recoverButton);
		//sets display size of input textfields
		usernameInput.setColumns(10);
		passwordInput.setColumns(10);
		//sets what happens when the login button is pressed
		recoverButton.setActionCommand("recover");
		recoverButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.next(recoverPage);//TODO
			}
		}
				);
		loginButton.setActionCommand("login");
		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				LoginLogout.login(usernameInput, passwordInput);
			}
		}
				);
		//CardLayout for the sign Up Page
		signUpPage = new JPanel();
		//initialize all components to be displayed
		backToLogin = new JButton("Back");
		registerButton = new JButton("Register");
		newUsernameLabel = new JLabel("New Username");
		newUsernameInput = new JTextField();
		newPasswordLabel = new JLabel("New Password");
		newPasswordInput = new JPasswordField();
		reenterPasswordLabel = new JLabel("Re-enter Password");
		reenterPassword = new JPasswordField();
		securityQuestion = new JLabel("Security Question");
		securityQuestionInput = new JTextField();
		securityAnswer = new JLabel("Security Answer");
		securityAnswerInput = new JTextField();
		signUp = new JButton("Sign Up");
		//displays all components in login screen
		signUpPage.add(backToLogin);
		signUpPage.add(registerButton);
		signUpPage.add(newUsernameLabel);
		signUpPage.add(newUsernameInput);
		signUpPage.add(newPasswordLabel);
		signUpPage.add(newPasswordInput);
		signUpPage.add(reenterPasswordLabel);
		signUpPage.add(reenterPassword);
		signUpPage.add(securityQuestion);
		signUpPage.add(securityQuestionInput);
		signUpPage.add(securityAnswer);
		signUpPage.add(securityAnswerInput);
		//sets display size of input textfields
		newUsernameInput.setColumns(10);
		newPasswordInput.setColumns(10);
		reenterPassword.setColumns(10);
		securityQuestionInput.setColumns(10);
		securityAnswerInput.setColumns(10);
		//sets what happens when the back button is pressed
		backToLogin.setActionCommand("Back");
		backToLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.previous(pagePanels);
			}
		}
				);
		//sets what happens when the register button is pressed
		registerButton.setActionCommand("Register");
		registerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				SignUp.register(newUsernameInput, newPasswordInput, securityQuestionInput, securityAnswerInput,  reenterPassword);
			}
		}
				);
		//adds login page to panel
		pagePanels.add(login, "login");

		login.add(signUp);
		//sets what happens when the sign up button is pressed
		signUp.setActionCommand("Sign Up");
		signUp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.next(pagePanels);
			}
		});

		pagePanels.add(signUpPage, "back");
		signUp.setActionCommand("login");

		
		
		
		
		
		
		//Create Login Panel
//		recoverPage = new JPanel();
		//adds login page to panel
//		pagePanels.add(recoverPage, "recoverPage");
//		login.add(recoverPage);
		//sets what happens when the sign up button is pressed
		
		//initialize all components to be displayed
//		usernameLabel = new JLabel("Username");
//		passwordLabel = new JLabel("Password");
//		usernameInput = new JTextField();
//		passwordInput = new JPasswordField();
//		loginButton = new JButton("Login");
//		recoverButton = new JButton("Forgot Password?");
//		//displays all components in login screen
//		recoverPage.add(usernameLabel);
//		recoverPage.add(usernameInput);
//		recoverPage.add(passwordLabel);
//		recoverPage.add(passwordInput);
//		recoverPage.add(loginButton);
//		recoverPage.add(recoverButton);
//		//sets display size of input textfields
//		usernameInput.setColumns(10);
//		passwordInput.setColumns(10);
//		//sets what happens when the login button is pressed
//		loginButton.setActionCommand("recoverPage");
//		loginButton.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent event){
//				LoginLogout.login(usernameInput, passwordInput);
//			}
//		}
//				);




		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		masterPage.getContentPane().add(pagePanels,BorderLayout.CENTER);
		masterPage.setVisible(true);
	}
}