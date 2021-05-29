package otplogin;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class login {
	private int count=0;
	private JFrame frmLogin;
	private JTextField txtusername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		connectsql();
		initialize();
		
	}
	
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	private JPasswordField txtpassword;
	
	
	public void connectsql(){
		try {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","");
				System.out.println("connection is successful");
			} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getloginotp() {
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setForeground(Color.WHITE);
		frmLogin.setFont(new Font("Dialog", Font.PLAIN, 16));
		frmLogin.setTitle("Login verification");
		frmLogin.setBounds(100, 100, 575, 365);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(173, 11, 190, 45);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(57, 97, 140, 28);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(57, 158, 140, 28);
		frmLogin.getContentPane().add(lblNewLabel_1_1);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(173, 96, 253, 29);
		frmLogin.getContentPane().add(txtusername);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String username = txtusername.getText();
				@SuppressWarnings("deprecation")
				String password = txtpassword.getText();
				
				try {
					stmt = con.prepareStatement("select username, password from logininfo where username = ?");
					stmt.setString(1, username);
					rs = stmt.executeQuery();
					String entry = "Invalid password !";
					if(rs.next()) {
						
						if(username.equals(rs.getString(1)) && password.equals(rs.getString(2)) && count<3) {
							count=0;
							
							otpgenerator otp = new otpgenerator();
							otp.OTPgenerator();
						}
						else {
							
							if(!password.equals(rs.getString(2))) {
								count=count+1;// checking number of times entered
								
							}
							
							if(count>=3) {
								entry ="User locked out password incorrect, more than 3 times!";
							}
							otpgenerator invalid = new otpgenerator();
							invalid.invalidentry(entry);
						}
												
				}else {
						otpgenerator invalid = new otpgenerator();
						invalid.invalidentry("Invalid Username!");
					}
					
					
				} catch (SQLException e2) {
					
				}
				
				txtpassword.setText("");
				txtusername.setText("");
				txtpassword.requestFocus();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(218, 235, 114, 29);
		frmLogin.getContentPane().add(btnNewButton);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(173, 158, 253, 28);
		frmLogin.getContentPane().add(txtpassword);
	}
}
