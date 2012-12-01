package logging;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import playerManipulation.Player;
/**
 * 
 * The class deals with the entire graphical user interface related to the first
 * page that appears in the process of logging
 * 
 */
public class LoggingGUI {
	public static JFrame masterPage;
	public static CardLayout pages;
	public static JPanel pagePanels;
	private JPanel login;
	private JTextField usernameInput;
	private JPasswordField passwordInput; 
	private JLabel usernameLabel, passwordLabel;
	private JButton loginButton, signUp,recoverButton;
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
	/**
	 * A constructor for the gui of logging
	 */
	public LoggingGUI(){ 
		//main page
		masterPage = new JFrame();
		//masterPage.setDefaultCloseOperation(JFrame.);
		masterPage.setTitle("Pac-Man");
		masterPage.setSize(510,622 );
		masterPage.setLocationRelativeTo(null);
		//Building the card layout to switch between pages
		pages = new CardLayout();
		pagePanels = new JPanel();
		pagePanels.setLayout(pages);
		loginPage();
		pages.show(pagePanels, "login");
		//adds login page to panel
		pagePanels.add(login, "login");

		pagePanels.add(SignUpGUI.signUpGUI(), "signUpPage");
		login.add(signUp);
		pagePanels.add(RecoverGUI.recoverPasswordGUI(), "recover");
		//add gif
		File path = new File("").getAbsoluteFile();		
		ImageIcon background = new ImageIcon(path+ "\\res\\background.gif");
		// This image and all other occurences of it were taken from:
		//http://i889.photobucket.com/albums/ac94/xHeyJuicex/th_biz4.gif
		JLabel loginBackground = new JLabel();
		loginBackground.setBounds(47, 11, 400, 150);
		
		JLabel recoverBackground = new JLabel();
		JLabel resetPasswordBackground = new JLabel();

		loginBackground.setIcon(background);
		
		recoverBackground.setIcon(background);
		resetPasswordBackground.setIcon(background);
		login.add(loginBackground);
		

		masterPage.getContentPane().add(pagePanels,BorderLayout.CENTER);
		masterPage.setVisible(true);
	}
	/**
	 * The method to display the logging page
	 */
	public void loginPage(){

		//Create Login Panel
		login = new JPanel();
		login.setBackground(Color.BLACK);
		//initialize all components to be displayed
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(47, 207, 62, 14);
		usernameLabel.setForeground(Color.WHITE);
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(205, 207, 61, 14);
		passwordLabel.setForeground(Color.WHITE);
		usernameInput = new JTextField();
		usernameInput.setBounds(114, 204, 86, 20);
		passwordInput = new JPasswordField();
		passwordInput.setBounds(271, 204, 86, 20);
		loginButton = new JButton("Login");
		loginButton.setBounds(367, 203, 86, 23);
		recoverButton = new JButton("Forgot Password?");
		recoverButton.setBounds(114, 252, 145, 23);
		login.setLayout(null);

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
		signUp.setBounds(271, 252, 79, 23);

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


}