package logging;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.lang.String;
import javax.swing.*;

import playerManipulation.Player;

import FrontendDatabase.playerFrontend;
import java.awt.Toolkit;
import java.awt.Color;
public class LoggingGUI {
	JFrame masterPage;
	CardLayout pages;
	JPanel pagePanels;
	private JPanel login, signUpPage, recoverPage, resetPasswordPage, changePasswordPage;
	private JTextField usernameInput, newUsernameInput;
	private JPasswordField passwordInput,reenterPassword, newPasswordInput, recoverNewPassword, reenterRecoverNewPassword; 
	private JTextField securityQuestionInput, securityAnswerInput,recoverUsername, resetSecurityAnswerInput;
	private JLabel usernameLabel, passwordLabel, newUsernameLabel,newPasswordLabel, reenterPasswordLabel,securityQuestion, securityAnswer,
	recoverUsernameLabel, resetSecurityQuestion, resetSecurityAnswer, recoverNewPasswordLabel, reenterRecoverNewPasswordLabel;
	private JButton loginButton, signUpToLogin,registerButton,signUp,recoverButton, recoverMyPass, recoverToLogin, resetMyPass, resetToLogin, newPassword, cancelRecover;
	Player currentPlayer;
	String securityQuestionDisplayed;

	public static void main(String[] args) {
		//Use the event dispatch thread for Swing components changeeeeeee
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
		masterPage.setTitle("Pac-Man");
		masterPage.setSize(520,620 );
		//Building the card layout to switch between pages
		pages = new CardLayout();
		pagePanels = new JPanel();
		pagePanels.setLayout(pages);
		loginPage();
		pages.show(pagePanels, "login");
		//adds login page to panel
		pagePanels.add(login, "login");
		signUpPage();
		pagePanels.add(signUpPage, "signUpPage");
		recoverPage();
		login.add(signUp);
		pagePanels.add(recoverPage, "recover");
		//add gif
		File path = new File("").getAbsoluteFile();		
		ImageIcon background = new ImageIcon(path+ "\\resources\\background.gif");
		JLabel loginBackground = new JLabel();
		JLabel signUpBackground = new JLabel();
		JLabel recoverBackground = new JLabel();
		JLabel resetPasswordBackground = new JLabel();

		loginBackground.setIcon(background);
		signUpBackground.setIcon(background);
		recoverBackground.setIcon(background);
		resetPasswordBackground.setIcon(background);
		login.add(loginBackground);
		signUpPage.add(signUpBackground);
		recoverPage.add(recoverBackground);
		masterPage.getContentPane().add(pagePanels,BorderLayout.CENTER);
		masterPage.setVisible(true);
	}
	public void loginPage(){
		//Create Login Panel
		login = new JPanel();
		login.setBackground(Color.BLACK);
		//initialize all components to be displayed
		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.WHITE);
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
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

		signUp = new JButton("Sign Up");

		//sets what happens when the sign up button is pressed
		signUp.setActionCommand("Sign Up");
		signUp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.next(pagePanels);
			}
		});

		//sets what happens when the login button is pressed
		recoverButton.setActionCommand("recover");
		recoverButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.show(pagePanels, "recover");
				usernameInput.setText("");
				passwordInput.setText("");
			}
		}
				);
		loginButton.setActionCommand("login");
		loginButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				LoginLogout.login(usernameInput, passwordInput);
				usernameInput.setText("");
				passwordInput.setText("");
			}
		}
				);

	}
	public void signUpPage(){

		//CardLayout for the sign Up Page
		signUpPage = new JPanel();
		signUpPage.setBackground(Color.BLACK);
		//initialize all components to be displayed
		signUpToLogin = new JButton("Back");
		registerButton = new JButton("Register");
		newUsernameLabel = new JLabel("New Username");
		newUsernameLabel.setForeground(Color.WHITE);
		newUsernameInput = new JTextField();
		newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setForeground(Color.WHITE);
		newPasswordInput = new JPasswordField();
		reenterPasswordLabel = new JLabel("Re-enter Password");
		reenterPasswordLabel.setForeground(Color.WHITE);
		reenterPassword = new JPasswordField();
		securityQuestion = new JLabel("Security Question");
		securityQuestion.setForeground(Color.WHITE);
		securityQuestionInput = new JTextField();
		securityAnswer = new JLabel("Security Answer");
		securityAnswer.setForeground(Color.WHITE);
		securityAnswerInput = new JTextField();
		//displays all components in login screen
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
		signUpPage.add(signUpToLogin);
		signUpPage.add(registerButton);
		//sets display size of input textfields
		newUsernameInput.setColumns(10);
		newPasswordInput.setColumns(10);
		reenterPassword.setColumns(10);
		securityQuestionInput.setColumns(10);
		securityAnswerInput.setColumns(10);
		//sets what happens when the back button is pressed
		signUpToLogin.setActionCommand("Back");
		signUpToLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.show(pagePanels, "login");
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
	}
	public void recoverPage(){
		//Create Login Panel
		recoverPage = new JPanel();
		recoverPage.setBackground(Color.BLACK);
		//initialize all components to be displayed
		recoverUsernameLabel = new JLabel("Username");
		recoverUsernameLabel.setForeground(Color.WHITE);
		recoverUsername= new JTextField();
		recoverMyPass = new JButton("Submit");
		recoverToLogin = new JButton("Back");
		//displays all components in login screen
		recoverPage.add(recoverUsernameLabel);
		recoverPage.add(recoverUsername);
		recoverPage.add(recoverMyPass);
		recoverPage.add(recoverToLogin);
		//sets display size of input textfields
		recoverUsername.setColumns(10);
		//sets what happens when the login button is pressed

		recoverToLogin.setActionCommand("Back");
		recoverToLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.show(pagePanels, "login");
				recoverUsername.setText("");
			}
		});
		recoverMyPass.setActionCommand("Submit");
		recoverMyPass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(playerFrontend.playerExists(recoverUsername)){
					currentPlayer = new Player(recoverUsername.getText());
					securityQuestionDisplayed = currentPlayer.getSecurityQuestion();
					resetPasswordPage();
					pagePanels.add(resetPasswordPage, "resetPasswordPage");
					pages.show(pagePanels, "resetPasswordPage");
					recoverUsername.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "Username does not exist, please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
				);
	}
	public void resetPasswordPage(){
		//Create Login Panel
		resetPasswordPage = new JPanel();
		resetPasswordPage.setBackground(Color.BLACK);
		//initialize all components to be displayed
		resetMyPass = new JButton("Submit");
		resetToLogin = new JButton("Back");
		resetSecurityQuestion = new JLabel("Security Question: "+securityQuestionDisplayed+" ");
		resetSecurityQuestion.setForeground(Color.WHITE);
		resetSecurityAnswer = new JLabel("Security Answer");
		resetSecurityAnswer.setForeground(Color.WHITE);
		resetSecurityAnswerInput = new JTextField();
		//displays all components in login screen
		
		resetPasswordPage.add(resetSecurityQuestion);
		resetPasswordPage.add(resetSecurityAnswer);
		resetPasswordPage.add(resetSecurityAnswerInput);
		resetSecurityAnswerInput.setColumns(10);
		resetPasswordPage.add(resetMyPass);
		resetPasswordPage.add(resetToLogin);
		//sets display size of input textfields
		//sets what happens when the login button is pressed

		resetToLogin.setActionCommand("Back");
		resetToLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.show(pagePanels, "login");
				resetSecurityAnswerInput.setText("");
			}
		});
		resetMyPass.setActionCommand("recoverMyPass");
		resetMyPass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(playerManipulation.RecoverPassword.checkSecurityQuestion(currentPlayer.getUsername(), resetSecurityAnswerInput)){
					changePasswordPage();
					pagePanels.add(changePasswordPage, "changePasswordPage");
					pages.show(pagePanels, "changePasswordPage");
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Answer, please try again","Error",JOptionPane.ERROR_MESSAGE);
				}

			}
		}
				);
	}
	public void changePasswordPage(){
		//Create Login Panel
		changePasswordPage = new JPanel();
		changePasswordPage.setBackground(Color.BLACK);
		//initialize all components to be displayed
		newPassword = new JButton("Submit");
		cancelRecover = new JButton("Cancel");
		recoverNewPasswordLabel = new JLabel("Enter New Password");
		recoverNewPasswordLabel.setForeground(Color.WHITE);
		recoverNewPassword = new JPasswordField();
		reenterRecoverNewPasswordLabel = new JLabel("Re-enter New Password");
		reenterRecoverNewPasswordLabel.setForeground(Color.WHITE);
		reenterRecoverNewPassword = new JPasswordField();
		//displays all components in login screen
		changePasswordPage.add(recoverNewPasswordLabel);
		changePasswordPage.add(recoverNewPassword);
		changePasswordPage.add(reenterRecoverNewPasswordLabel);
		changePasswordPage.add(reenterRecoverNewPassword);
		changePasswordPage.add(newPassword);
		changePasswordPage.add(cancelRecover);
		recoverNewPassword.setColumns(10);

		reenterRecoverNewPassword.setColumns(10);

		newPassword.setActionCommand("newPassword");
		newPassword.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(playerManipulation.ChangeProfileDetails.changePassword(currentPlayer.getUsername(),recoverNewPassword,reenterRecoverNewPassword)){
					JOptionPane.showMessageDialog(null, "Your password have been successfully changed","Password Changed",JOptionPane.INFORMATION_MESSAGE);
					recoverNewPassword.setText("");
					reenterRecoverNewPassword.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "An error occured while recovering your password, please try again","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
				);
		cancelRecover.setActionCommand("cancelRecover");
		cancelRecover.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				pages.show(pagePanels, "login");
				recoverNewPassword.setText("");
				reenterRecoverNewPassword.setText("");
			}
		}
				);
	}
}