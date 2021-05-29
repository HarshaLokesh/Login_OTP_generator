package otplogin;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

public class otpgenerator {

	private JFrame frmOtp;

	/**
	 * Create the application.
	 */
	public void OTPgenerator() {
		initialize1();
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void invalidentry(String name) {
		initialize2(name);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize1() {
		int randomPin   =(int) (Math.random()*9000)+1000;
		String otp = String.valueOf(randomPin);
		String name = " OTP :"+ otp;
		frmOtp = new JFrame();
		frmOtp.setTitle("OTP");
		frmOtp.setBounds(100, 100, 450, 300);
		frmOtp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOtp.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(78, 81, 287, 80);
		frmOtp.getContentPane().add(lblNewLabel);
		
		frmOtp.setVisible(true);
	}
	
	private void initialize2(String name) {
		
		frmOtp = new JFrame();
		frmOtp.setTitle("Invalid");
		frmOtp.setBounds(100, 100, 450, 300);
		frmOtp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOtp.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 81, 384, 80);
		frmOtp.getContentPane().add(lblNewLabel);
		
		frmOtp.setVisible(true);
	}

}
