package com.control.jas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField newPassword;
	private JPasswordField newPassword_1;
	private JPasswordField oldPassword;
	private JFrame frame = this;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChangePassword frame = new ChangePassword(name);
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
	public ChangePassword(String name) {
		setTitle("Maple Control Change Password");		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/control/jas/maple.png")));
		SqlConnect sc = new SqlConnect();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("輸入舊密碼 :");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblNewLabel.setBounds(103, 36, 89, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("輸入新密碼 :");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblNewLabel_1.setBounds(103, 76, 89, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("新密碼確認 :");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(103, 116, 89, 15);
		contentPane.add(lblNewLabel_1_1);
		
		oldPassword = new JPasswordField();
		oldPassword.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		oldPassword.setBounds(193, 33, 103, 21);
		contentPane.add(oldPassword);
		
		newPassword = new JPasswordField();
		newPassword.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		newPassword.setBounds(193, 74, 103, 21);
		contentPane.add(newPassword);
		
		newPassword_1 = new JPasswordField();
		newPassword_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		newPassword_1.setBounds(193, 114, 103, 21);
		contentPane.add(newPassword_1);
		
		JButton btnNewButton = new JButton("送出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch (sc.changePasswordCheck(name, oldPassword.getText(), newPassword.getText(), newPassword_1.getText()))
					{
						case 0 -> JOptionPane.showMessageDialog(null, "舊密碼錯誤");
						case 1 -> JOptionPane.showMessageDialog(null, "新密碼輸入不一致");
						case 2 -> 
						{
							JOptionPane.showMessageDialog(null, "更改成功");
							frame.dispose();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 15));
		btnNewButton.setBounds(156, 169, 87, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
