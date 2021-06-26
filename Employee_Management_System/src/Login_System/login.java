package Login_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

public class login {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	  */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(33, 32, 543, 332);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton login = new JButton("Login");
		login.setBounds(83, 198, 356, 36);
		panel.add(login);
		login.setBackground(new Color(211, 211, 211));
		login.setForeground(new Color(0, 0, 0));
		login.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		pass = new JPasswordField();
		pass.setBounds(83, 130, 356, 36);
		panel.add(pass);
		pass.setEchoChar('*');
		pass.setForeground(Color.BLACK);
		pass.setToolTipText("************");
		pass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		
		user = new JTextField();
		user.setBounds(83, 82, 356, 36);
		panel.add(user);
		user.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		user.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Desktop\\project\\privacy.png"));
		lblNewLabel_1.setBounds(38, 130, 35, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ELCOT\\Desktop\\project\\unauthorized-person (1).png"));
		lblNewLabel.setBounds(38, 82, 35, 36);
		panel.add(lblNewLabel);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String username = user.getText();
				String password = pass.getText();
				
				if(user.getText().trim().isEmpty() && pass.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Username and password is required");
				}
				else if(user.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Username is required");
				}
				else if(pass.getText().trim().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Password is required");
				}
				else
				{
					if(password.contains("vishnu") && username.contains("vasanth"))
					{
						JOptionPane.showMessageDialog(null, "Welcome to Employee Management System!!!");
						
						employee emp = new employee();
						
						employee.main(null);
						
						//System.exit(0);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username and Password is Wrong","Login Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
