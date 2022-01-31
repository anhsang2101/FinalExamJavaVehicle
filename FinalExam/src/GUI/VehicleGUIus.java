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

import Model.Owner;
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

import com.toedter.calendar.JDateChooser;

import DB.ConnectDB;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class VehicleGUIus {
	ConnectDB c = new ConnectDB();
	JFrame frame = new JFrame("Vehical management for User");
	private JTextField tfSearch;
	private JTextField tfSearch2;
	JComboBox comboBoxSearch;
	JComboBox comboBoxSearch2;
	JTable table, table2;
	private JTable table3;
	JLabel lbNumCar;
	JLabel lbNumMoto;
	JLabel lbNumAll;
	ResultSet rs;
	ResultSetMetaData rstmeta;
	
	public VehicleGUIus() {
		frame.getContentPane().setLayout(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		//tab 1
		
		JPanel mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(2,1));
		tabbedPane.add(mainMenu);

		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(SystemColor.activeCaption);
		menuPanel.setLayout(new GridLayout(2,1));
		mainMenu.add(menuPanel);
		
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(SystemColor.activeCaption);
		menuPanel.add(panelTitle);
		
		JLabel lbTitle = new JLabel("Vehicle Management");
		panelTitle.add(lbTitle);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTitle.setForeground(Color.RED);
		
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
		
		JButton btnRefresh = new JButton("Refresh");
		panelFunction.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				fillDataTable();
			}
		});
		
		JButton btnSignOut = new JButton("Sign Out");
		panelFunction.add(btnSignOut);
		btnSignOut.setHorizontalAlignment(SwingConstants.LEADING);
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
		fillDataTable();
	
		
		// tab2 ---------------------------------------------
		
		JPanel ownerMenu = new JPanel();
		ownerMenu.setLayout(new GridLayout(2,1));
		tabbedPane.add(ownerMenu);
		
		JPanel menuPanel2 = new JPanel();
		menuPanel2.setBackground(SystemColor.activeCaption);
		menuPanel2.setLayout(new GridLayout(2,1));
		ownerMenu.add(menuPanel2);
		
		
		JPanel panelTitle2 = new JPanel();
		panelTitle2.setBackground(SystemColor.activeCaption);
		menuPanel2.add(panelTitle2);
		
		JLabel lbTitle2 = new JLabel("Owner Information");
		panelTitle2.add(lbTitle2);
		lbTitle2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTitle2.setForeground(Color.RED);
		
		
		
		
		JPanel panelFunction2 = new JPanel();
		panelFunction2.setBackground(SystemColor.activeCaption);
		menuPanel2.add(panelFunction2);
		
		JButton btnSearch2 = new JButton("Search");
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxSearch2.getSelectedIndex()==0) {
					searchByOwnerName2();
				}
				else if(comboBoxSearch2.getSelectedIndex()==1){
					searchByID();
				}
				
			}
		});

		
		JLabel lbSearch2 = new JLabel("Search");
		panelFunction2.add(lbSearch2);
		
		tfSearch2 = new JTextField();
		panelFunction2.add(tfSearch2);
		tfSearch2.setColumns(20);
		
		comboBoxSearch2 = new JComboBox();
		comboBoxSearch2.setModel(new DefaultComboBoxModel(new String[] {"Owner Name", "Identity Card"}));
		panelFunction2.add(comboBoxSearch2);
		
		
		
		panelFunction2.add(btnSearch2);
		
		
		JButton btnRefresh2 = new JButton("Refresh");
		panelFunction2.add(btnRefresh2);
		btnRefresh2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				fillDataTable2();
			}
		});
		
		
		JPanel tablePanel2 = new JPanel();
		tablePanel2.setBackground(SystemColor.activeCaption);
		ownerMenu.add(tablePanel2);
		tablePanel2.setLayout( new BorderLayout());
		Border border2 = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titleBorder2 = BorderFactory.createTitledBorder(border, " View Table ");
		tablePanel2.setBorder(titleBorder2);
		
		
		
		
		
		
		JScrollPane tableResult2 = new JScrollPane();

		tablePanel2.add(tableResult2);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Identity Card", "Day Of Birth", "Gender", "Address", "Number of vehicles owned"
			}
		));
		tableResult2.setViewportView(table2);
		
		fillDataTable2();
		
		// tab3
		
		JPanel statisticalMenu = new JPanel();
		statisticalMenu.setLayout(new GridLayout(2,1));
		tabbedPane.add(statisticalMenu);
		
		JPanel menuPanel3;
		menuPanel3 = new JPanel();
		menuPanel3.setLayout(new GridLayout(2,1));
		statisticalMenu.add(menuPanel3);
	
		JPanel panelStatistical = new JPanel();
		panelStatistical.setLayout(new GridLayout(1,3));
		
		menuPanel3.add(panelStatistical);
		
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
		
		
		JPanel panelFunction3;
		panelFunction3 = new JPanel();
		panelFunction3.setLayout(new GridLayout(1,1));
		menuPanel3.add(panelFunction3);
		
		JPanel panelRadioCar = new JPanel();
		panelRadioCar.setLayout(new GridLayout(1,1));
		panelFunction3.add(panelRadioCar);
		
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
		panelFunction3.add(panelRadioMoto);
		
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
		
		
		
		JPanel tablePanel3;
		tablePanel3 = new JPanel();
		tablePanel3.setBackground(SystemColor.activeCaption);
		statisticalMenu.add(tablePanel3);
		tablePanel3.setLayout( new BorderLayout());
		Border border3 = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder titleBorder3 = BorderFactory.createTitledBorder(border2, " View Table ");
		tablePanel3.setBorder(titleBorder3);
		
		
		JScrollPane tableResult3;
		tableResult3 = new JScrollPane();
		tablePanel3.add(tableResult3);
		
		table3 = new JTable();
		table3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Owner Name", "Identity Card", "Vehicle Type", "License Plate", "Brand", "Chassis Number", "Engine Number","Registration Date"
			}
		));
		tableResult3.setViewportView(table3);
		
		
		
		
		
		
		// -----------Add Icon------------
		
			// icon tab1
		ImageIcon titleIcon = new ImageIcon("VehicleIcon.png");
		lbTitle.setIcon(titleIcon);
		ImageIcon refreshIcon = new ImageIcon("RefreshIcon.png");
		btnRefresh.setIcon(refreshIcon);
		ImageIcon signOutIcon = new ImageIcon("SignOutIcon.png");
		ImageIcon searchIcon = new ImageIcon("SearchIcon.png");
		btnSearch.setIcon(searchIcon);
		btnSignOut.setIcon(signOutIcon);
			
			//icon tab2
		ImageIcon titleIcon2 = new ImageIcon("Owner.png");
		lbTitle2.setIcon(titleIcon2);
		ImageIcon refreshIcon2 = new ImageIcon("RefreshIcon.png");
		btnRefresh2.setIcon(refreshIcon2);
		ImageIcon searchIcon2 = new ImageIcon("SearchIcon.png");
		btnSearch2.setIcon(searchIcon2);
		
			// icon tab3
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
		tabbedPane.setTitleAt(1, "Owner Menu");
		tabbedPane.setTitleAt(2,"Statistical Menu");
		//-------
		
		
		
		
		frame.setUndecorated(false);
		frame.pack();
		frame.setLocation(100,30);
		frame.setSize(1200,600);
		frame.setVisible(true);
		
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
		DefaultTableModel model2 = (DefaultTableModel) table3.getModel();
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
		DefaultTableModel model2 = (DefaultTableModel) table3.getModel();
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
	
	public void searchByOwnerName2() {
		DefaultTableModel Model = (DefaultTableModel) table2.getModel();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		
		try {
			String sql = "Select [Owner Name], [Identity Card], [Day Of Birth], [Gender], [Address] From Owner where [Owner Name] like '%"+tfSearch2.getText()+"%'";
			conn = c.getDBConnect();
			sttm = conn.prepareStatement(sql);
			rs = sttm.executeQuery();
					
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "Not found");
			}
			else {
				Model.setRowCount(0);
				for (Owner o : c.getOwnerByOwnerName(tfSearch2.getText())){
					Object dataRow[] = new Object[6];
					dataRow[0] = o.getName();
					dataRow[1] = o.getIdentityCard();
					dataRow[2] = o.getDayOfBirth();
					dataRow[3] = o.getGender();
					dataRow[4] = o.getAddress();
					dataRow[5] = c.getNumVehicle(o.getIdentityCard());
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
	
	public void searchByID() {
		DefaultTableModel Model = (DefaultTableModel) table2.getModel();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		
		try {
			String sql = "Select [Owner Name], [Identity Card], [Day Of Birth], [Gender], [Address] From Owner where [Identity Card] like '%"+tfSearch2.getText()+"%'";
			conn = c.getDBConnect();
			sttm = conn.prepareStatement(sql);
			rs = sttm.executeQuery();
					
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "Not found");
			}
			else {
				Model.setRowCount(0);
				for (Owner o : c.getOwnersByID(tfSearch2.getText())){
					Object dataRow[] = new Object[6];
					dataRow[0] = o.getName();
					dataRow[1] = o.getIdentityCard();
					dataRow[2] = o.getDayOfBirth();
					dataRow[3] = o.getGender();
					dataRow[4] = o.getAddress();
					dataRow[5] = c.getNumVehicle(o.getIdentityCard());
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
	
	public void fillDataTable2() {
		DefaultTableModel model = (DefaultTableModel) table2.getModel();
		model.setRowCount(0);
		
		for(Owner o : c.getAllOwner()) {
			Object dataRow[] = new Object[6];
			dataRow[0] = o.getName();
			dataRow[1] = o.getIdentityCard();
			dataRow[2] = o.getDayOfBirth();
			dataRow[3] = o.getGender();
			dataRow[4] = o.getAddress();
			dataRow[5] = c.getNumVehicle(o.getIdentityCard());
			model.addRow(dataRow);
		}
	}
	
	public Date utilToSqlDate(java.util.Date time){
	    java.sql.Date sqldate = new java.sql.Date(time.getTime());
	    return sqldate;
	}
	
	
	public static void main(String[] args) {
		VehicleGUIus v = new VehicleGUIus();

	}
}
