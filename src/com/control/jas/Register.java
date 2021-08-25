package com.control.jas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.sql.jas.SqlConnect;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Register frame = new Register();
//					frame.getContentPane();
//					frame.setLocationRelativeTo(null);
//					frame.setResizable(false);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setTitle("Maple Control Register");
		SqlConnect sc = new SqlConnect();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/control/jas/maple.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("使用者名稱 :");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblNewLabel.setBounds(112, 36, 89, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("使用者密碼 :");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblNewLabel_1.setBounds(112, 76, 89, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼再確認 :");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(112, 116, 89, 15);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		textField.setBounds(202, 34, 103, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		passwordField.setBounds(202, 74, 103, 21);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		passwordField_1.setBounds(202, 114, 103, 21);
		contentPane.add(passwordField_1);
		
		btnNewButton = new JButton("送出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(sc.registerSystem(textField.getText(), passwordField.getText(), passwordField_1.getText()))
					{
						case 0 -> JOptionPane.showMessageDialog(null, "已有相同名稱使用者存在");
						case 1 -> JOptionPane.showMessageDialog(null, "兩次密碼輸入不一致");
						case 2 -> JOptionPane.showMessageDialog(null, "註冊成功");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		btnNewButton.setBounds(169, 165, 87, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
