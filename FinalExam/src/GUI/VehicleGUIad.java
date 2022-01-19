package GUI;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import Model.Vehicle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.ConnectDB;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;

public class VehicleGUIad {
	ConnectDB c = new ConnectDB();
	JFrame frame = new JFrame("Vehical management for Admin");
	private JTextField tfOwnerName;
	private JTextField tfIdentityCard;
	private JTextField tfLicensePlate;
	private JTextField tfChassisNumber;
	private JTextField tfEngineNumber;
	private JTextField tfBrand;
	private JTextField tfSearch;
	JComboBox comboBox;
	JTable table;
	ResultSet rs;
	ResultSetMetaData rstmeta;

	
	public VehicleGUIad() {
		frame.getContentPane().setLayout(new GridLayout(2,1));
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.activeCaption);
		mainPanel.setLayout(new GridLayout(8,1));
		frame.getContentPane().add(mainPanel);
		
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelTitle);
		
		JLabel lbTitle = new JLabel("Vehicle Management");
		panelTitle.add(lbTitle);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTitle.setForeground(Color.RED);
		
		JPanel panelName = new JPanel();
		panelName.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelName);
		
		JLabel lbOwnerName = new JLabel("Owner Name");
		panelName.add(lbOwnerName);
		
		tfOwnerName = new JTextField();
		panelName.add(tfOwnerName);
		tfOwnerName.setColumns(50);
		
		JPanel panelIdentityCard = new JPanel();
		panelIdentityCard.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelIdentityCard);
		
		JLabel lbIdentityCard = new JLabel("Identity Card");
		panelIdentityCard.add(lbIdentityCard);
		
		tfIdentityCard = new JTextField();
		panelIdentityCard.add(tfIdentityCard);
		tfIdentityCard.setColumns(31);
		
		JPanel space3 = new JPanel();
		panelIdentityCard.add(space3);
		
		JLabel lbDate = new JLabel("Registration Date");
		panelIdentityCard.add(lbDate);
		
		JDateChooser dateChooser = new JDateChooser();
		panelIdentityCard.add(dateChooser);
		dateChooser.setDateFormatString("dd.mm.yyyy");
		
		JPanel panelVehicleTypeAndLicensePlate = new JPanel();
		panelVehicleTypeAndLicensePlate.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelVehicleTypeAndLicensePlate);
		
		JLabel lbVehicleType = new JLabel("Vehicle Type");
		panelVehicleTypeAndLicensePlate.add(lbVehicleType);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Car", "Motobike"}));
		
		panelVehicleTypeAndLicensePlate.add(comboBox);
		
		JPanel space = new JPanel();
		panelVehicleTypeAndLicensePlate.add(space);
		
		JLabel lbLicensePlate = new JLabel("License Plate");
		lbLicensePlate.setForeground(new Color(255, 0, 0));
		panelVehicleTypeAndLicensePlate.add(lbLicensePlate);
		
		tfLicensePlate = new JTextField();
		panelVehicleTypeAndLicensePlate.add(tfLicensePlate);
		tfLicensePlate.setColumns(15);
		
		JPanel space1 = new JPanel();
		panelVehicleTypeAndLicensePlate.add(space1);
		
		JLabel lbBrand = new JLabel("Brand");
		panelVehicleTypeAndLicensePlate.add(lbBrand);
		
		tfBrand = new JTextField();
		panelVehicleTypeAndLicensePlate.add(tfBrand);
		tfBrand.setColumns(13);
		
		JPanel panelChassisNumber = new JPanel();
		panelChassisNumber.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelChassisNumber);
		
		JLabel lbChassisNumber = new JLabel("Chassis Number");
		panelChassisNumber.add(lbChassisNumber);
		
		tfChassisNumber = new JTextField();
		panelChassisNumber.add(tfChassisNumber);
		tfChassisNumber.setColumns(19);
		
		JPanel space2 = new JPanel();
		panelChassisNumber.add(space2);
		
		JLabel lbEngineNumber = new JLabel("Engine Number");
		panelChassisNumber.add(lbEngineNumber);
		
		tfEngineNumber = new JTextField();
		panelChassisNumber.add(tfEngineNumber);
		tfEngineNumber.setColumns(19);
		
		JPanel panelDate = new JPanel();
		panelDate.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelDate);
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelButton);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateForm()) {
					Vehicle v = putToModel();
					if(c.Insert(v)>0) {
						JOptionPane.showMessageDialog(null, "Insert successfully");
						fillDataTable();
					}
					
				} 
				else {
					JOptionPane.showMessageDialog(null, "Please complete required field");
				}
			}
		});
		panelButton.add(btnInsert);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfLicensePlate.getText().isEmpty()==false) {
					Vehicle v = putToModel();
					if(c.Update(v)>0) {
						JOptionPane.showMessageDialog(null, "Update successfully");
						fillDataTable();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "You mustn't change the license plate, please choose again");
				}
			}
		});
		panelButton.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfLicensePlate.getText().isEmpty()==false) {
					Vehicle v = putToModel();
					if(c.Delete(v.getLicensePlate())>0) {
						JOptionPane.showMessageDialog(null, "Delete successfully");
						fillDataTable();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Choose again");
				}
			}
		});
		panelButton.add(btnDelete);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			
			public void newTextField() {
				tfOwnerName.setText("");
				tfIdentityCard.setText(String.valueOf(""));
				comboBox.setSelectedIndex(0);
				tfLicensePlate.setText("");
				tfBrand.setText("");
				tfChassisNumber.setText("");
				tfEngineNumber.setText("");
			}
			
			public void actionPerformed(ActionEvent e) {
				newTextField();
				fillDataTable();
			}
		});
		panelButton.add(btnRefresh);
		
		JPanel panelFunction = new JPanel();
		panelFunction.setBackground(SystemColor.activeCaption);
		mainPanel.add(panelFunction);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Model = (DefaultTableModel) table.getModel();
				
				Connection conn = null;
				ResultSet rs = null;
				PreparedStatement sttm = null;
				
				try {
					String sql = "Select [Owner Name], [Identity Card], [Vehicle Type], [License Plate], [Brand], [Chassis Number], [Engine Number] From Vehicle where [Owner Name] like '%"+tfSearch.getText()+"%'";
					conn = c.getDBConnect();
					sttm = conn.prepareStatement(sql);
					rs = sttm.executeQuery();
							
					if(rs.next()==false) {
						JOptionPane.showMessageDialog(null, "Not found");
					}
					else {
						Model.setRowCount(0);
						for (Vehicle v : c.getVehicleByOwnerName(tfSearch.getText())){
							Object dataRow[] = new Object[7];
							dataRow[0] = v.getOwnerName();
							dataRow[1] = v.getIdentityCard();
							dataRow[2] = v.getType();
							dataRow[3] = v.getLicensePlate();
							dataRow[4] = v.getBrand();
							dataRow[5] = v.getChassisNumber();
							dataRow[6] = v.getEngineNumber();
							Model.addRow(dataRow);
						}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		
		JLabel lbSearch = new JLabel("Search");
		panelFunction.add(lbSearch);
		
		tfSearch = new JTextField();
		panelFunction.add(tfSearch);
		tfSearch.setColumns(20);
		
		panelFunction.add(btnSearch);
		
		JButton btnSignOut = new JButton("Sign Out");
		panelFunction.add(btnSignOut);
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sign out successfully");
				frame.dispose();
				LoginGUI log = new LoginGUI();
			}
		});
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panelTable);
		panelTable.setLayout( new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(border, " View Table ");
		panelTable.setBorder(titleBorder);
		
		
		
		
		
		
		JScrollPane tableResult = new JScrollPane();

		panelTable.add(tableResult);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Owner Name", "Identity Card", "Vehicle Type", "License Plate", "Brand", "Chassis Number", "Engine Number"
			}
		));
		tableResult.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			
			public void setTextField(Vehicle v) {
				tfOwnerName.setText(v.getOwnerName());
				tfIdentityCard.setText(String.valueOf(v.getIdentityCard()));
				comboBox.setSelectedItem(v.getType());
				tfLicensePlate.setText(v.getLicensePlate());
				tfBrand.setText(v.getBrand());
				tfChassisNumber.setText(v.getChassisNumber());
				tfEngineNumber.setText(v.getEngineNumber());
			}
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				String strLp = table.getValueAt(row, 3).toString();
				Vehicle v = c.getVehicleByLp(strLp);
				setTextField(v);			
				
			}
		});
		
		fillDataTable();
		
		
		// -----------Add Icon------------
		ImageIcon titleIcon = new ImageIcon("VehicleIcon.png");
		lbTitle.setIcon(titleIcon);
		ImageIcon insertIcon = new ImageIcon("InsertIcon.png");
		btnInsert.setIcon(insertIcon);
		ImageIcon updateIcon = new ImageIcon("UpdateIcon.png");
		btnUpdate.setIcon(updateIcon);
		ImageIcon deleteIcon = new ImageIcon("DeleteIcon.png");
		btnDelete.setIcon(deleteIcon);
		ImageIcon refreshIcon = new ImageIcon("RefreshIcon.png");
		btnRefresh.setIcon(refreshIcon);
		ImageIcon searchIcon = new ImageIcon("SearchIcon.png");
		btnSearch.setIcon(searchIcon);
		ImageIcon signOutIcon = new ImageIcon("SignOutIcon.png");
		btnSignOut.setIcon(signOutIcon);
		
		// --------------------
		
		
		
		
		
		
		frame.pack();
		frame.setLocation(100,30);
		frame.setSize(1200,600);
		frame.setVisible(true);
		
	}
	
	public boolean validateForm() {
		if(tfLicensePlate.getText().isEmpty() || tfIdentityCard.getText().isEmpty() || tfBrand.getText().isEmpty() || tfOwnerName.getText().isEmpty() || tfChassisNumber.getText().isEmpty() || tfEngineNumber.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Vehicle putToModel() {
		Vehicle v = new Vehicle();
		v.setOwnerName(tfOwnerName.getText());
		v.setIdentityCard(Integer.parseInt(tfIdentityCard.getText()));
		v.setType(comboBox.getSelectedItem().toString());
		v.setLicensePlate(tfLicensePlate.getText());
		v.setBrand(tfBrand.getText());
		v.setChassisNumber(tfChassisNumber.getText());
		v.setEngineNumber(tfEngineNumber.getText());
		return v;
	}
	
	public void fillDataTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for(Vehicle v : c.getAllVehicle()) {
			Object dataRow[] = new Object[8];
			dataRow[0] = v.getOwnerName();
			dataRow[1] = v.getIdentityCard();
			dataRow[2] = v.getType();
			dataRow[3] = v.getLicensePlate();
			dataRow[4] = v.getBrand();
			dataRow[5] = v.getChassisNumber();
			dataRow[6] = v.getEngineNumber();
			dataRow[7] = v.getDate();
			model.addRow(dataRow);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		VehicleGUIad v = new VehicleGUIad();

	}
}
