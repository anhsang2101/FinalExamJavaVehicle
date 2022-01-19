package GUI;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.ConnectDB;
import Model.Vehicle;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class LoginGUI {
	ConnectDB c = new ConnectDB();
	JFrame frame = new JFrame("Login");
	private JTextField tfUsername;
	private JPasswordField passwordField;
	
	
	public LoginGUI(){
		frame.getContentPane().setLayout(new GridLayout(4,1));
		
		JPanel panelTitle = new JPanel();
		frame.getContentPane().add(panelTitle);
		
		JLabel lbTitle = new JLabel("Login");
		lbTitle.setForeground(Color.RED);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelTitle.add(lbTitle);
		
		JPanel panelUserName = new JPanel();
		frame.getContentPane().add(panelUserName);
		
		JPanel panelPassword = new JPanel();
		frame.getContentPane().add(panelPassword);
		
		JPanel panelRole = new JPanel();
		frame.getContentPane().add(panelRole);
		
		
		JLabel lbUsername = new JLabel("User Name");
		panelUserName.add(lbUsername);
		
		tfUsername = new JTextField();
		panelUserName.add(tfUsername);
		tfUsername.setColumns(15);
		
		JLabel lbPassword = new JLabel("Password");
		panelPassword.add(lbPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		panelPassword.add(passwordField);
		
		JLabel lbRole = new JLabel("Role");
		panelRole.add(lbRole);
		
		JComboBox Role = new JComboBox();
		Role.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User"}));
		panelRole.add(Role);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUsername.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in blank");
				}
				else {
					if(c.SignIn(tfUsername.getText(), passwordField.getText(), Role.getSelectedItem().toString())>0) {
						JOptionPane.showMessageDialog(null, "Sign in successfully");
						frame.dispose();
						if(Role.getSelectedItem().toString()=="Admin") {
							VehicleGUIad vAd = new VehicleGUIad();
						}
						else {
							VehicleGUIus vUs = new VehicleGUIus();
						}
					}	
					else
						JOptionPane.showMessageDialog(null, "Wrong password or username");
				}
			}
		});
		
		JPanel panel = new JPanel();
		panelRole.add(panel);
		
		JPanel panel_1 = new JPanel();
		panelRole.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panelRole.add(panel_2);
		panelRole.add(btnSignIn);
		
		
		// Add Icon
		
		ImageIcon loginIcon = new ImageIcon("LoginIcon.png");
		lbTitle.setIcon(loginIcon);
		ImageIcon userNameIcon = new ImageIcon("UserNameIcon.png");
		lbUsername.setIcon(userNameIcon);
		ImageIcon passwordIcon = new ImageIcon("PasswordIcon.png");
		lbPassword.setIcon(passwordIcon);
		ImageIcon roleIcon = new ImageIcon("RoleIcon.png");
		lbRole.setIcon(roleIcon);
		ImageIcon signInIcon = new ImageIcon("SignInIcon.jpg");
		btnSignIn.setIcon(signInIcon);
		
		
		
		frame.pack();
		frame.setLocation(500,150);
		frame.setSize(400,300);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new LoginGUI();
	}
}
