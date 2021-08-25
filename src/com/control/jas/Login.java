package com.control.jas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameInput;
	private JFrame f = this;
	private String account = "";

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
	 */
	public Login() {		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/control/jas/maple.png")));
		SqlConnect sc = new SqlConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u7D00\u9304\u4F7F\u7528\u8005");
		chckbxNewCheckBox.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		chckbxNewCheckBox.setBounds(270, 81, 109, 30);
		contentPane.add(chckbxNewCheckBox);
		
		FileReader fileReader;
		try {
			fileReader = new FileReader("maplecontrol.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			chckbxNewCheckBox.setSelected(true);
			try {
				this.account = bufferedReader.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			chckbxNewCheckBox.setSelected(false);
		}
		
		JLabel lblNewLabel = new JLabel("\u4F7F\u7528\u8005");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setBounds(79, 82, 75, 26);
		contentPane.add(lblNewLabel);
		
		userNameInput = new JTextField();
		userNameInput.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		userNameInput.setBounds(155, 81, 109, 27);
		contentPane.add(userNameInput);
		userNameInput.setColumns(10);
		userNameInput.setText(account);
		
		JButton loginBtn = new JButton("\u767B\u5165");
		loginBtn.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(sc.CheckAccount(userNameInput.getText(), chckbxNewCheckBox.isSelected()))
					{
						case 1:
							Main frame = new Main(userNameInput.getText());
							frame.setVisible(true);
							frame.getContentPane();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							f.dispose();
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "名稱錯誤");
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "名稱未輸入");
							break;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginBtn.setBounds(155, 144, 109, 43);
		contentPane.add(loginBtn);
	}
}
