package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DB.ConnectDB;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VehicleGUIus {
	JFrame frame = new JFrame("Vehical management for User");
	ResultSet rs;
	ResultSetMetaData rstmeta;

	JTable table;
	public VehicleGUIus() {
		frame.getContentPane().setLayout(new GridLayout(2,1));
		
	    
	    JPanel panel = new JPanel();
	    frame.getContentPane().add(panel);
	    
	    JButton btnSignOut = new JButton("Sign Out");
	    btnSignOut.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "Sign out successfully");
				frame.dispose();
				LoginGUI log = new LoginGUI();
	    	}
	    });
	    panel.setLayout(new BorderLayout(0, 0));
	    panel.add(btnSignOut);
	    
	    JPanel panelTable = new JPanel();

	    JScrollPane tableResult = new JScrollPane(table);
	    panelTable.setLayout( new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(border, " View Table ");
		panelTable.setBorder(titleBorder);
		panelTable.add(tableResult);
		
	    frame.getContentPane().add(panelTable);
		frame.pack();
		frame.setLocation(200,30);
		frame.setSize(800,400);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new VehicleGUIus();
	}
}
