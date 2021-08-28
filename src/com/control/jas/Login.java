package com.control.jas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sql.jas.SqlConnect;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameInput;
	private JFrame f = this;
	private String account = "";
	private String password = "";
	private JTextField textField;
	private JPasswordField passwordField;
	private String version = "1.0.2";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.getContentPane();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Login() {
		Scripts scripts = new Scripts();				
		setTitle("Maple Control");		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/control/jas/maple.png")));
		SqlConnect sc = new SqlConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			String nowVersion = scripts.getVersion();
			if (!version.equals(nowVersion)) JOptionPane.showMessageDialog(null, "目前已有新版推出, 請至下載中心下載最新版本", "版本訊息", 2);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("紀錄帳號密碼");
		chckbxNewCheckBox.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		chckbxNewCheckBox.setBounds(266, 50, 121, 30);
		contentPane.add(chckbxNewCheckBox);
		
		FileReader fileReader;
		try {
			fileReader = new FileReader("maplecontrol.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			chckbxNewCheckBox.setSelected(true);
			try {
				this.account = bufferedReader.readLine();
				this.password = bufferedReader.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			chckbxNewCheckBox.setSelected(false);
		}
		
		JLabel lblNewLabel = new JLabel("帳號");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(87, 51, 40, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("密碼");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel_3.setBounds(87, 90, 40, 26);
		contentPane.add(lblNewLabel_3);
		
		userNameInput = new JTextField();
		userNameInput.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		userNameInput.setBounds(151, 50, 109, 27);
		contentPane.add(userNameInput);
		userNameInput.setColumns(10);
		userNameInput.setText(account);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 92, 109, 26);
		contentPane.add(passwordField);
		passwordField.setText(password);
		
		JButton loginBtn = new JButton("\u767B\u5165");
		loginBtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(sc.CheckAccount(userNameInput.getText(), passwordField.getText(), chckbxNewCheckBox.isSelected()))
					{
						case 1 ->
						{
							Main frame = new Main(userNameInput.getText(), version);
							frame.setVisible(true);
							frame.getContentPane();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							f.dispose();
						}
						case 2 -> JOptionPane.showMessageDialog(null, "帳號或密碼錯誤");
						case 3 -> JOptionPane.showMessageDialog(null, "帳號未輸入");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginBtn.setBounds(151, 144, 109, 43);
		contentPane.add(loginBtn);
		
		JLabel lblNewLabel_1 = new JLabel("尚未擁有帳號 ?");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_1.setBounds(143, 207, 87, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("點我註冊");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_2.setBounds(229, 207, 51, 15);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.BLUE.darker());
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblNewLabel_1_1 = new JLabel("版本: " + version);
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(362, 236, 62, 15);
		contentPane.add(lblNewLabel_1_1);
		
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	Register frame = new Register();
				frame.getContentPane();
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);
		    }
			    
		});
	}
}
