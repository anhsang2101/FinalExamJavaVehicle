package GUI;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DB.ConnectDB;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

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
	JComboBox comboBoxType;
	JComboBox comboBoxSearch;
	JDateChooser dateChooser;
	JTable table, table2;
	JLabel lbNumCar;
	JLabel lbNumMoto;
	JLabel lbNumAll;
	ResultSet rs;
	ResultSetMetaData rstmeta;

	
	public VehicleGUIad() {
		frame.getContentPane().setLayout(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		//tab 1
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(2,1));
		tabbedPane.add(mainMenu);

		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(SystemColor.activeCaption);
		menuPanel.setLayout(new GridLayout(8,1));
		mainMenu.add(menuPanel);
		
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelTitle);
		
		JLabel lbTitle = new JLabel("Vehicle Management");
		panelTitle.add(lbTitle);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTitle.setForeground(Color.RED);
		
		JPanel panelName = new JPanel();
		panelName.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelName);
		
		JLabel lbOwnerName = new JLabel("Owner Name");
		panelName.add(lbOwnerName);
		
		tfOwnerName = new JTextField();
		panelName.add(tfOwnerName);
		tfOwnerName.setColumns(50);
		
		JPanel panelIdentityCard = new JPanel();
		panelIdentityCard.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelIdentityCard);
		
		JLabel lbIdentityCard = new JLabel("Identity Card");
		panelIdentityCard.add(lbIdentityCard);
		
		tfIdentityCard = new JTextField();
		panelIdentityCard.add(tfIdentityCard);
		tfIdentityCard.setColumns(31);
		
		JPanel space3 = new JPanel();
		panelIdentityCard.add(space3);
		
		JLabel lbDate = new JLabel("Registration Date");
		panelIdentityCard.add(lbDate);
		
		dateChooser = new JDateChooser();
		panelIdentityCard.add(dateChooser);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		
		JPanel panelVehicleTypeAndLicensePlate = new JPanel();
		panelVehicleTypeAndLicensePlate.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelVehicleTypeAndLicensePlate);
		
		JLabel lbVehicleType = new JLabel("Vehicle Type");
		panelVehicleTypeAndLicensePlate.add(lbVehicleType);
		
		comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Car", "Motobike"}));
		
		panelVehicleTypeAndLicensePlate.add(comboBoxType);
		
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
		menuPanel.add(panelChassisNumber);
		
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
		
		JPanel panelSpace = new JPanel();
		panelSpace.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelSpace);
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelButton);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(validateForm()) {
					Vehicle v = putToModel();
					if(c.Checklp(v.getLicensePlate())>0)
						JOptionPane.showMessageDialog(null, "License Plate is existed");

					else if(c.Insert(v)>0) {
						JOptionPane.showMessageDialog(null, "Insert successfully");
						fillDataTable();
						refreshStatistical();
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
						newTextField();
						refreshStatistical();
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
			
			public void actionPerformed(ActionEvent e) {
				newTextField();
				fillDataTable();
			}
		});
		panelButton.add(btnRefresh);
		
		JPanel panelFunction = new JPanel();
		panelFunction.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelFunction);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxSearch.getSelectedIndex()==0) {
					searchByOwnerName();
				}
				else if(comboBoxSearch.getSelectedIndex()==1){
					searchByLicensePlate();
				}
				
			}
		});

		
		JLabel lbSearch = new JLabel("Search");
		panelFunction.add(lbSearch);
		
		tfSearch = new JTextField();
		panelFunction.add(tfSearch);
		tfSearch.setColumns(20);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"Owner Name", "License Plate"}));
		panelFunction.add(comboBoxSearch);
		
		
		
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
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(SystemColor.activeCaption);
		mainMenu.add(tablePanel);
		tablePanel.setLayout( new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(border, " View Table ");
		tablePanel.setBorder(titleBorder);
		
		
		
		
		
		
		JScrollPane tableResult = new JScrollPane();

		tablePanel.add(tableResult);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Owner Name", "Identity Card", "Vehicle Type", "License Plate", "Brand", "Chassis Number", "Engine Number","Registration Date"
			}
		));
		tableResult.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			
			public void setTextField(Vehicle v) {
				tfOwnerName.setText(v.getOwnerName());
				tfIdentityCard.setText(String.valueOf(v.getIdentityCard()));
				comboBoxType.setSelectedItem(v.getType());
				tfLicensePlate.setText(v.getLicensePlate());
				tfBrand.setText(v.getBrand());
				tfChassisNumber.setText(v.getChassisNumber());
				tfEngineNumber.setText(v.getEngineNumber());
				dateChooser.setDate(v.getDate());
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
		// tab2
		
		JPanel statisticalMenu = new JPanel();
		statisticalMenu.setLayout(new GridLayout(2,1));
		tabbedPane.add(statisticalMenu);
		
		JPanel menuPanel2 = new JPanel();
		menuPanel2.setLayout(new GridLayout(2,1));
		statisticalMenu.add(menuPanel2);
		
		JPanel panelStatistical = new JPanel();
		panelStatistical.setLayout(new GridLayout(1,3));
		
		menuPanel2.add(panelStatistical);
		
		JPanel panelCar = new JPanel();
		panelCar.setLayout(new GridLayout(2,1));
		panelStatistical.add(panelCar);
		
		JLabel lbNumCarTitle = new JLabel("Number of cars");
		lbNumCarTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbNumCarTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelCar.add(lbNumCarTitle);
		
		lbNumCar = new JLabel("");
		lbNumCar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbNumCar.setHorizontalAlignment(SwingConstants.CENTER);
		lbNumCar.setText(String.valueOf(c.countCar()));
		panelCar.add(lbNumCar);
		
		JPanel panelMoto = new JPanel();
		panelMoto.setBackground(SystemColor.activeCaption);
		panelMoto.setLayout(new GridLayout(2,1));
		panelStatistical.add(panelMoto);
		
		JLabel lbNumMotoTitle = new JLabel("Number of motobikes");
		lbNumMotoTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbNumMotoTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelMoto.add(lbNumMotoTitle);
		
		lbNumMoto = new JLabel("");
		lbNumMoto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbNumMoto.setHorizontalAlignment(SwingConstants.CENTER);
		lbNumMoto.setText(String.valueOf(c.countMoto()));
		panelMoto.add(lbNumMoto);
		
		JPanel panelAll = new JPanel();
		panelAll.setLayout(new GridLayout(2,1));
		panelStatistical.add(panelAll);
		
		JLabel lbNumAllTitle = new JLabel("Number of all vehicles");
		lbNumAllTitle.setForeground(Color.RED);
		lbNumAllTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbNumAllTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelAll.add(lbNumAllTitle);
		
		lbNumAll = new JLabel("");
		lbNumAll.setForeground(Color.RED);
		lbNumAll.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbNumAll.setHorizontalAlignment(SwingConstants.CENTER);
		lbNumAll.setText(String.valueOf(c.countAll()));
		panelAll.add(lbNumAll);
		
		JPanel panelFunction2 = new JPanel();
		panelFunction2.setLayout(new GridLayout(1,1));
		menuPanel2.add(panelFunction2);
		
		JPanel panelRadioCar = new JPanel();
		panelRadioCar.setLayout(new GridLayout(1,1));
		panelFunction2.add(panelRadioCar);
		
		JRadioButton rdbtnCar = new JRadioButton("Show All Cars");
		rdbtnCar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		rdbtnCar.setBackground(SystemColor.activeCaption);
		rdbtnCar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillDataCar();
			}
		});
		panelRadioCar.add(rdbtnCar);
		
		JPanel panelRadioMoto = new JPanel();
		panelRadioMoto.setLayout(new GridLayout(1,1));
		panelFunction2.add(panelRadioMoto);
		
		JRadioButton rdbtnMoto = new JRadioButton("Show All Motobikes");
		rdbtnMoto.setFont(new Font("Tahoma", Font.PLAIN, 30));
		rdbtnMoto.setBackground(SystemColor.activeCaption);
		rdbtnMoto.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnMoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillDataMotobike();
			}
		});
		panelRadioMoto.add(rdbtnMoto);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCar);
		radioGroup.add(rdbtnMoto);
		
		
		
		JPanel tablePanel2 = new JPanel();
		tablePanel2.setBackground(SystemColor.activeCaption);
		statisticalMenu.add(tablePanel2);
		tablePanel2.setLayout( new BorderLayout());
		Border border2 = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titleBorder2 = BorderFactory.createTitledBorder(border2, " View Table ");
		tablePanel2.setBorder(titleBorder2);
		
		
		JScrollPane tableResult2 = new JScrollPane();
		tablePanel2.add(tableResult2);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Owner Name", "Identity Card", "Vehicle Type", "License Plate", "Brand", "Chassis Number", "Engine Number","Registration Date"
			}
		));
		tableResult2.setViewportView(table2);
		
		
		
		
		
		
		// -----------Add Icon------------
		
			// icon tab1
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
			
			// icon tab2
		ImageIcon carIcon = new ImageIcon("CarIcon.png");
		lbNumCar.setIcon(carIcon);
		lbNumCar.setHorizontalTextPosition(JLabel.LEFT);
		ImageIcon motobikeIcon = new ImageIcon("MotobikeIcon.png");
		lbNumMoto.setIcon(motobikeIcon);
		lbNumMoto.setHorizontalTextPosition(JLabel.LEFT);
		ImageIcon statisticalIcon = new ImageIcon("StatisticalIcon.png");
		lbNumAll.setIcon(statisticalIcon);
		lbNumAll.setHorizontalTextPosition(JLabel.LEFT);
		// --------------------
		
		
		
		// Name tab
		tabbedPane.setTitleAt(0,"Main Menu");
		tabbedPane.setTitleAt(1,"Statistical Menu");
		//-------
		
		
		
		
		frame.setUndecorated(false);
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
		v.setType(comboBoxType.getSelectedItem().toString());
		v.setLicensePlate(tfLicensePlate.getText());
		v.setBrand(tfBrand.getText());
		v.setChassisNumber(tfChassisNumber.getText());
		v.setEngineNumber(tfEngineNumber.getText());
		v.setDate(utilToSqlDate(dateChooser.getDate()));
		return v;
	}
	
	public void newTextField() {
		tfOwnerName.setText("");
		tfIdentityCard.setText(String.valueOf(""));
		comboBoxType.setSelectedIndex(0);
		tfLicensePlate.setText("");
		tfBrand.setText("");
		tfChassisNumber.setText("");
		tfEngineNumber.setText("");
		dateChooser.setCalendar(null);
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
	
	public void fillDataMotobike() {
		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
		model2.setRowCount(0);
		
		for(Vehicle v : c.getAllMotobike()) {
			Object dataRow[] = new Object[8];
			dataRow[0] = v.getOwnerName();
			dataRow[1] = v.getIdentityCard();
			dataRow[2] = v.getType();
			dataRow[3] = v.getLicensePlate();
			dataRow[4] = v.getBrand();
			dataRow[5] = v.getChassisNumber();
			dataRow[6] = v.getEngineNumber();
			dataRow[7] = v.getDate();
			model2.addRow(dataRow);
		}
	}
	
	public void fillDataCar() {
		DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
		model2.setRowCount(0);
		
		for(Vehicle v : c.getAllCar()) {
			Object dataRow[] = new Object[8];
			dataRow[0] = v.getOwnerName();
			dataRow[1] = v.getIdentityCard();
			dataRow[2] = v.getType();
			dataRow[3] = v.getLicensePlate();
			dataRow[4] = v.getBrand();
			dataRow[5] = v.getChassisNumber();
			dataRow[6] = v.getEngineNumber();
			dataRow[7] = v.getDate();
			model2.addRow(dataRow);
		}
	}
	
	public void refreshStatistical(){
		lbNumAll.setText(String.valueOf(c.countAll()));
		lbNumCar.setText(String.valueOf(c.countCar()));
		lbNumMoto.setText(String.valueOf(c.countMoto()));
	}
	
	public void searchByOwnerName() {
		DefaultTableModel Model = (DefaultTableModel) table.getModel();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		
		try {
			String sql = "Select [Owner Name], [Identity Card], [Vehicle Type], [License Plate], [Brand], [Chassis Number], [Engine Number], [Registration Date] From Vehicle where [Owner Name] like '%"+tfSearch.getText()+"%'";
			conn = c.getDBConnect();
			sttm = conn.prepareStatement(sql);
			rs = sttm.executeQuery();
					
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "Not found");
			}
			else {
				Model.setRowCount(0);
				for (Vehicle v : c.getVehicleByOwnerName(tfSearch.getText())){
					Object dataRow[] = new Object[8];
					dataRow[0] = v.getOwnerName();
					dataRow[1] = v.getIdentityCard();
					dataRow[2] = v.getType();
					dataRow[3] = v.getLicensePlate();
					dataRow[4] = v.getBrand();
					dataRow[5] = v.getChassisNumber();
					dataRow[6] = v.getEngineNumber();
					dataRow[7] = v.getDate();
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
	
	public void searchByLicensePlate() {
		DefaultTableModel Model = (DefaultTableModel) table.getModel();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		
		try {
			String sql = "Select [Owner Name], [Identity Card], [Vehicle Type], [License Plate], [Brand], [Chassis Number], [Engine Number], [Registration Date] From Vehicle where [License Plate] like '%"+tfSearch.getText()+"%'";
			conn = c.getDBConnect();
			sttm = conn.prepareStatement(sql);
			rs = sttm.executeQuery();
					
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "Not found");
			}
			else {
				Model.setRowCount(0);
				for (Vehicle v : c.getVehiclesByLp(tfSearch.getText())){
					Object dataRow[] = new Object[8];
					dataRow[0] = v.getOwnerName();
					dataRow[1] = v.getIdentityCard();
					dataRow[2] = v.getType();
					dataRow[3] = v.getLicensePlate();
					dataRow[4] = v.getBrand();
					dataRow[5] = v.getChassisNumber();
					dataRow[6] = v.getEngineNumber();
					dataRow[7] = v.getDate();
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
	
	public Date utilToSqlDate(java.util.Date time){
	    java.sql.Date sqldate = new java.sql.Date(time.getTime());
	    return sqldate;
	}
	
	
	public static void main(String[] args) {
		VehicleGUIad v = new VehicleGUIad();

	}
}
