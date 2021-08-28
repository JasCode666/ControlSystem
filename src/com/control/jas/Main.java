package com.control.jas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Desktop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sql.jas.SqlConnect;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import javax.swing.JTree;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField userNameInput;
	private String name;
	private JTextField beanAccount;
	private JTextField beanPassword;
	private JTextField noticeLbl;
	private int adminPermission = 0;
	private String admin = "一般使用者";
	private JTextField levelLbl;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Main frame = new Main("Test");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Main(String name, String version) throws SQLException {
		setFont(new Font("微軟正黑體", Font.BOLD, 12));
		setTitle("Maple Control");
		setResizable(false);
		SpringLayout layout = new SpringLayout();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/control/jas/maple.png")));
		SqlConnect sc = new SqlConnect();
		Scripts scripts = new Scripts();
		Text text = new Text();
		try {
			this.adminPermission = sc.getUserData(name);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		switch (this.adminPermission)
		{
			case 1 -> this.admin = "管理員";
			case 2 -> this.admin = "超級管理員";
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = getToolkit().getScreenSize();
//		setSize((int)dim.getWidth() / 2, (int)dim.getHeight() / 2);
		setSize(960, 540);
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		tabbedPane.setBounds(0, 0, 944, 501);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("查看狀態", null, panel, null);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		textArea.setBounds(10, 28, 409, 194);
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(10, 28, 919, 432);
		panel.add(scrollBar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("更新狀態", null, panel_1, null);
		panel_1.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u8ACB\u9078\u64C7"}));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
//		comboBox_1.addItem("請選擇");
		comboBox_1.setBounds(137, 54, 202, 23);
		panel_1.add(comboBox_1);
		try {
			ResultSet resultSet = sc.GetAccount();
			while (resultSet.next())
			{
				comboBox_1.addItem(resultSet.getString(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel codeLbl = new JLabel("\u5E33\u865F\u4EE3\u865F :");
		codeLbl.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		codeLbl.setBounds(37, 56, 103, 19);
		panel_1.add(codeLbl);
		
		JLabel statusLbl = new JLabel("\u66F4\u65B0\u72C0\u614B :");
		statusLbl.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		statusLbl.setBounds(37, 98, 103, 19);
		panel_1.add(statusLbl);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"\u8ACB\u9078\u64C7", "\u639B\u9322\u4E2D", "\u7DF4\u7B49\u4E2D", "\u5DF2\u6B7B\u4EA1", "\u5DF2\u9000\u5F79", "\u672A\u5275\u89D2\u8272", "\u7B49\u8CB7\u5BF5\u7269\u4E2D", "\u53EF\u639B(\u672A\u4F7F\u7528)", "\u5275\u597D\u5DF2\u9818\u7269\u54C1", "\u5275\u597D\u672A\u9818\u7269\u54C1"}));
		comboBox_1_1.setBounds(137, 98, 202, 23);
		panel_1.add(comboBox_1_1);
		
		JLabel codeLbl_2 = new JLabel("角色職業 :");
		codeLbl_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		codeLbl_2.setBounds(37, 143, 103, 19);
		panel_1.add(codeLbl_2);
		
		JLabel statusLbl_2 = new JLabel("角色等級 :");
		statusLbl_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		statusLbl_2.setBounds(37, 185, 103, 19);
		panel_1.add(statusLbl_2);
		
		JComboBox comboBox_1_3 = new JComboBox();
		comboBox_1_3.setModel(new DefaultComboBoxModel(new String[] {"請選擇"}));
		comboBox_1_3.setToolTipText("");
		comboBox_1_3.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		comboBox_1_3.setBounds(137, 141, 202, 23);
		panel_1.add(comboBox_1_3);
		ResultSet rs = scripts.getJobs();
		while(rs.next())
		{
			comboBox_1_3.addItem(rs.getString(1));
		}
		
		levelLbl = new JTextField();
		levelLbl.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		levelLbl.setColumns(10);
		levelLbl.setBounds(137, 184, 202, 23);
		panel_1.add(levelLbl);
		
		JButton btnNewButton = new JButton("\u9001\u51FA\u66F4\u65B0");
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch (sc.update(comboBox_1.getSelectedItem().toString(), comboBox_1_1.getSelectedItem().toString(), name, levelLbl.getText(), comboBox_1_3.getSelectedItem().toString(),  noticeLbl.getText()))
					{
						case 0 -> {
							JOptionPane.showMessageDialog(null, "狀態更新完成");
							noticeLbl.setText("");
							levelLbl.setText("");
						}
						case 1 -> JOptionPane.showMessageDialog(null, "未選擇帳號");

						case 2 -> JOptionPane.showMessageDialog(null, "未選擇狀態");
						
						case 3 -> JOptionPane.showMessageDialog(null, "未選擇職業");
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
		btnNewButton.setBounds(37, 275, 128, 37);
		panel_1.add(btnNewButton);
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scripts.refreshMessage(comboBox_1, comboBox_1_1, comboBox_1_3, levelLbl, noticeLbl);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel statusLbl_1 = new JLabel("\u72C0\u614B\u5099\u8A3B :");
		statusLbl_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		statusLbl_1.setBounds(37, 225, 103, 19);
		panel_1.add(statusLbl_1);
		
		noticeLbl = new JTextField();
		noticeLbl.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		noticeLbl.setBounds(137, 225, 202, 23);
		panel_1.add(noticeLbl);
		noticeLbl.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("新增帳號", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u7D44\u6578 :");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel_2.setBounds(41, 33, 56, 22);
		panel_2.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		spinner.setBounds(104, 33, 97, 22);
		panel_2.add(spinner);
		
		JLabel lblNewLabel_3_1 = new JLabel("\u8A3B : \u4E00\u500B\u6A02\u8C46\u5E33\u865F\u70BA\u4E00\u7D44");
		lblNewLabel_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(41, 166, 264, 22);
		lblNewLabel_3_1.setForeground(Color.RED.darker());
		panel_2.add(lblNewLabel_3_1);
		
		JComboBox comboBox_1_2 = new JComboBox();
		comboBox_1_2.setModel(new DefaultComboBoxModel(new String[] {"\u8ACB\u9078\u64C7"}));
		comboBox_1_2.setToolTipText("");
		comboBox_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		comboBox_1_2.setBounds(612, 33, 202, 23);
		panel_2.add(comboBox_1_2);
		try {
			ResultSet resultSet = sc.GetAccount();
			while (resultSet.next())
			{
				comboBox_1_2.addItem(resultSet.getString(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton addBtn = new JButton("\u65B0\u589E");
		addBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = 0;
					ResultSet resultSet = sc.GetAccount();
					while (resultSet.next())
					{
						row++;
					}
					row /= 2;
					
					switch (sc.insert(row, (Integer)spinner.getValue()))
					{
						case 0 :
							JOptionPane.showMessageDialog(null, "新增成功");
							comboBox_1_2.removeAllItems();
							comboBox_1.removeAllItems();
							try {
								ResultSet resultSet1 = sc.GetAccount();
								while (resultSet1.next())
								{
									comboBox_1_2.addItem(resultSet1.getString(1));
									comboBox_1.addItem(resultSet1.getString(1));
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case 1 :
							JOptionPane.showMessageDialog(null, "新增失敗");
							break;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addBtn.setBounds(41, 96, 79, 31);
		panel_2.add(addBtn);
		
		JButton refreshBtn = new JButton("\u5237\u65B0");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1_2.removeAllItems();
				comboBox_1.removeAllItems();
				try {
					ResultSet resultSet = sc.GetAccount();
					while (resultSet.next())
					{
						comboBox_1_2.addItem(resultSet.getString(1));
						comboBox_1.addItem(resultSet.getString(1));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		refreshBtn.setBounds(160, 96, 79, 31);
		panel_2.add(refreshBtn);
		
		JLabel codeLbl_1 = new JLabel("\u5E33\u865F\u4EE3\u865F :");
		codeLbl_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		codeLbl_1.setBounds(512, 35, 103, 19);
		panel_2.add(codeLbl_1);
		
		JLabel codeLbl_1_1 = new JLabel("\u6A02\u8C46\u5E33\u865F :");
		codeLbl_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		codeLbl_1_1.setBounds(512, 80, 103, 19);
		panel_2.add(codeLbl_1_1);
		
		JLabel codeLbl_1_2 = new JLabel("\u6A02\u8C46\u5BC6\u78BC :");
		codeLbl_1_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		codeLbl_1_2.setBounds(512, 126, 103, 19);
		panel_2.add(codeLbl_1_2);
		
		beanAccount = new JTextField();
		beanAccount.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		beanAccount.setBounds(612, 79, 202, 22);
		panel_2.add(beanAccount);
		beanAccount.setColumns(10);
		
		beanPassword = new JTextField();
		beanPassword.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		beanPassword.setColumns(10);
		beanPassword.setBounds(612, 125, 202, 22);
		panel_2.add(beanPassword);
		
		JButton createBtn = new JButton("\u532F\u5165");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sc.createAccount(comboBox_1_2.getSelectedItem().toString(), beanAccount.getText(), beanPassword.getText()))
				{
					JOptionPane.showMessageDialog(null, "更新帳號密碼成功");
					beanAccount.setText("");
					beanPassword.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "更新帳號密碼失敗");
			}
		});
		createBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		createBtn.setBounds(554, 189, 79, 31);
		panel_2.add(createBtn);
		
		JButton clearBtn = new JButton("\u6E05\u7A7A");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beanAccount.setText("");
				beanPassword.setText("");
			}
		});
		clearBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		clearBtn.setBounds(719, 189, 79, 31);
		panel_2.add(clearBtn);
		
		JComboBox statusBox = new JComboBox();
		statusBox.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		statusBox.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u639B\u9322\u4E2D", "\u7DF4\u7B49\u4E2D", "\u5DF2\u6B7B\u4EA1", "\u5DF2\u9000\u5F79", "\u672A\u5275\u89D2\u8272", "\u672A\u5275\u5E33\u865F", "\u7B49\u8CB7\u5BF5\u7269\u4E2D", "\u53EF\u639B(\u672A\u4F7F\u7528)", "\u5275\u597D\u5DF2\u9818\u7269\u54C1", "\u5275\u597D\u672A\u9818\u7269\u54C1"}));
		statusBox.setBounds(55, 2, 148, 23);
		panel.add(statusBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("顯示帳號密碼");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				scripts.refreshTextArea(textArea, chckbxNewCheckBox, statusBox);
			}
		});

		chckbxNewCheckBox.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		chckbxNewCheckBox.setBounds(275, 2, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		JButton searchBtn = new JButton("\u67E5\u8A62");
		searchBtn.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scripts.refreshTextArea(textArea, chckbxNewCheckBox, statusBox);
			}
		});
		searchBtn.setBounds(210, 2, 60, 23);
		panel.add(searchBtn);
		
		JLabel lblNewLabel = new JLabel("\u72C0\u614B");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 6, 49, 15);
		panel.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("遊戲攻略", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTextArea textArea1 = new JTextArea();
		textArea1.setEditable(this.adminPermission > 0);
		textArea1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		textArea1.setBounds(10, 28, 409, 194);
		JScrollPane scrollBar1 = new JScrollPane(textArea1);
		scrollBar1.setBounds(10, 29, 419, 431);
		panel_3.add(scrollBar1);		
		textArea1.setText(text.str1);
		textArea1.setCaretPosition(0);
		JScrollPane scrollBar2 = new JScrollPane();
		scrollBar2.setBounds(458, 29, 471, 431);
		panel_3.add(scrollBar2);
		
		JTextArea textArea2 = new JTextArea();
		scrollBar2.setViewportView(textArea2);
		textArea2.setEditable(false);
		textArea2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		textArea2.setText(text.str2);
		textArea2.setCaretPosition(0);
		
		JLabel lblNewLabel_1 = new JLabel(" \u7DF4\u7D1A\u653B\u7565");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 4, 102, 21);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u661F\u529B\u653B\u7565");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(458, 4, 102, 21);
		panel_3.add(lblNewLabel_1_1);
		
		if (this.adminPermission > 0)
		{
			JPanel panel_4 = new JPanel();
			tabbedPane.addTab("管理選單", null, panel_4, null);
			panel_4.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("\u4F7F\u7528\u8005\u5217\u8868");
			lblNewLabel_4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			lblNewLabel_4.setBounds(35, 57, 85, 15);
			panel_4.add(lblNewLabel_4);
			

			String members[] = sc.getUserList(name);
			DefaultListModel demoList = new DefaultListModel();
			for (String x : members)
			{
				demoList.addElement(x);
			}
			JList list = new JList(demoList);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setBackground(SystemColor.scrollbar);
			list.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			list.setBounds(35, 86, 284, 206);
			panel_4.add(list);

			JLabel lblNewLabel_4_1 = new JLabel("您好 " + name + " 目前的身分組為 " + this.admin);
			lblNewLabel_4_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			lblNewLabel_4_1.setBounds(35, 10, 331, 15);
			panel_4.add(lblNewLabel_4_1);
			
			JRadioButton upBtn = new JRadioButton("身分組升級");
			upBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
			upBtn.setBounds(35, 313, 95, 23);
			panel_4.add(upBtn);
			
			JRadioButton downBtn = new JRadioButton("身分組降級");
			downBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
			downBtn.setBounds(35, 338, 95, 23);
			panel_4.add(downBtn);
			
			JRadioButton delBtn = new JRadioButton("刪除使用者");
			delBtn.setFont(new Font("微軟正黑體", Font.BOLD, 14));
			delBtn.setBounds(174, 313, 95, 23);
			panel_4.add(delBtn);
			
			JButton btnNewButton_1 = new JButton("送出");
			btnNewButton_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
			btnNewButton_1.setBounds(35, 420, 284, 23);
			panel_4.add(btnNewButton_1);
			
			upBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					downBtn.setSelected(false);
				}
			});
			
			downBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					upBtn.setSelected(false);
				}
			});
			
			JRadioButton[] buttonList = new JRadioButton[3];
			buttonList[0] = delBtn;
			buttonList[1] = upBtn;
			buttonList[2] = downBtn;
			
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					if (list.isSelectionEmpty())
						JOptionPane.showMessageDialog(null, "未選擇使用者");
					
					try {
						switch(sc.manageUser(name, list.getSelectedValue().toString(), buttonList))
						{
							case 0 ->  JOptionPane.showMessageDialog(null, "使用者權限不足");
							case 1 ->  JOptionPane.showMessageDialog(null, "使用者權限已達上限");
							case 2 ->  JOptionPane.showMessageDialog(null, "使用者權限已達下限");
							case 3 ->  
							{
								JOptionPane.showMessageDialog(null, "更新成功");
								String membersNew[] = sc.getUserList(name);
								DefaultListModel demoListNew = new DefaultListModel();
								for (String x : membersNew)
								{
									demoListNew.addElement(x);
								}
								list.setModel(demoListNew);
							}
							case 4 ->  JOptionPane.showMessageDialog(null, "功能未選擇");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("下載中心", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("檔案下載");
		lblNewLabel_5.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_5.setBounds(30, 7, 64, 23);
		panel_5.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("網頁連結");
		lblNewLabel_5_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(480, 7, 64, 23);
		panel_5.add(lblNewLabel_5_1);
		
		ResultSet infoRs = sc.getLinkImformation("imformation");
		int infoRow = 0, x = 0, y = 35;
		List<JLabel> msgs = new ArrayList<JLabel>();
		while(infoRs.next())
		{
			msgs.add(new JLabel(infoRs.getString(2)));
			JLabel info = msgs.get(infoRow);
			infoRow++;
			info.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			info.setBounds(30, y, 135, 25);
			panel_5.add(info);
			y += 35;
		}
		
		ResultSet infoRsDown = sc.getLinkImformation("imformation");
		int infoRowDown = 0, x1 = 0, y1 = 35;
		List<JLabel> downLink = new ArrayList<JLabel>();
		while(infoRsDown.next())
		{
			String url = infoRsDown.getString(3);
			if (url.equals("none"))
			{
				downLink.add(new JLabel("尚未更新"));
				JLabel downLinks = downLink.get(infoRowDown);
				infoRowDown++;
				downLinks.setFont(new Font("微軟正黑體", Font.BOLD, 16));
				downLinks.setBounds(180, y1, 64, 25);
				panel_5.add(downLinks);
				downLinks.setForeground(Color.RED.darker());
			}
			else
			{
				downLink.add(new JLabel("點我下載"));
				JLabel downLinks = downLink.get(infoRowDown);
				infoRowDown++;
				downLinks.setFont(new Font("微軟正黑體", Font.BOLD, 16));
				downLinks.setBounds(180, y1, 64, 25);
				panel_5.add(downLinks);
				downLinks.setForeground(Color.BLUE.darker());
				downLinks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				downLinks.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	try {
							Desktop.getDesktop().browse(new URI(url));
						} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
					    
				});
			}
			y1 += 35;
		}
		
		ResultSet linkMsgRs = sc.getLinkImformation("weblink");
		int linkMsgRow = 0, x2 = 0, y2 = 35;
		List<JLabel> linkMsgs = new ArrayList<JLabel>();
		while(linkMsgRs.next())
		{
			linkMsgs.add(new JLabel(linkMsgRs.getString(2)));
			JLabel info = linkMsgs.get(linkMsgRow);
			linkMsgRow++;
			info.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			info.setBounds(480, y2, 135, 25);
			panel_5.add(info);
			y2 += 35;
		}
		
		ResultSet infoLink = sc.getLinkImformation("weblink");
		int infoRowLink1 = 0, x3 = 0, y3 = 35;
		List<JLabel> links1 = new ArrayList<JLabel>();
		while(infoLink.next())
		{
			String url = infoLink.getString(3);
			links1.add(new JLabel("點我前往"));
			JLabel downLinks1 = links1.get(infoRowLink1);
			infoRowLink1++;
			downLinks1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			downLinks1.setBounds(630, y3, 64, 25);
			panel_5.add(downLinks1);
			downLinks1.setForeground(Color.BLUE.darker());
			downLinks1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			downLinks1.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	try {
						Desktop.getDesktop().browse(new URI(url));
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
				    
			});
			
			y3 += 35;
		}
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("顯示設定", null, panel_6, null);
		panel_6.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u8ACB\u9078\u64C7", "\u81EA\u9069\u61C9", "\u5168\u87A2\u5E55", "1024x768", "960x540 (\u5EFA\u8B70)"}));
		comboBox.setBounds(111, 42, 163, 27);
		panel_6.add(comboBox);
		
		JLabel lblNewLabel_6 = new JLabel("解析度 :");
		lblNewLabel_6.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_6.setBounds(45, 42, 56, 23);
		panel_6.add(lblNewLabel_6);
		
		JButton btnNewButton_2 = new JButton("更新");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (comboBox.getSelectedItem().toString())
				{
					case "請選擇" -> JOptionPane.showMessageDialog(null, "未選擇解析度");
					case "自適應" -> setSize((int)dim.getWidth() / 2, (int)dim.getHeight() / 2);
					case "全螢幕" -> setSize((int)dim.getWidth(), (int)dim.getHeight());
					case "1024x768" -> setSize(1024, 768);
					case "960x540 (建議)" -> setSize(960, 540);
				}
			}
		});
		btnNewButton_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		btnNewButton_2.setBounds(45, 96, 229, 32);
		panel_6.add(btnNewButton_2);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("個人設定", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("名稱 :");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3.setBounds(36, 35, 47, 21);
		panel_7.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_2 = new JLabel("" + name);
		lblNewLabel_3_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_2.setBounds(83, 35, 102, 21);
		panel_7.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("權限 :");
		lblNewLabel_3_3.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_3.setBounds(36, 75, 47, 21);
		panel_7.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("" + admin);
		lblNewLabel_3_2_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_2_1.setBounds(83, 75, 102, 21);
		lblNewLabel_3_2_1.setForeground(Color.green.darker());
		panel_7.add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("修改密碼");
		lblNewLabel_3_3_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_3_1.setBounds(36, 114, 64, 21);
		lblNewLabel_3_3_1.setForeground(Color.red.darker());
		lblNewLabel_3_3_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_7.add(lblNewLabel_3_3_1);
		
		JLabel lblNewLabel_3_4 = new JLabel("目前版本 :");
		lblNewLabel_3_4.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_4.setBounds(36, 155, 75, 21);
		panel_7.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("" + version);
		lblNewLabel_3_2_2.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel_3_2_2.setBounds(113, 155, 102, 21);
		panel_7.add(lblNewLabel_3_2_2);
		
//		if (name.equals("Jas"))
//		{
			JLabel lblNewLabel_3_4_1 = new JLabel("資料庫版本更改");
			lblNewLabel_3_4_1.setFont(new Font("微軟正黑體", Font.BOLD, 16));
			lblNewLabel_3_4_1.setBounds(36, 198, 119, 21);
			panel_7.add(lblNewLabel_3_4_1);
			
			textField = new JTextField();
			textField.setFont(new Font("微軟正黑體", Font.BOLD, 12));
			textField.setBounds(36, 229, 130, 21);
			panel_7.add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton_3 = new JButton("送出");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						scripts.changeVersion(textField.getText());
						textField.setText("");
						JOptionPane.showMessageDialog(null, "版本修改成功");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton_3.setFont(new Font("微軟正黑體", Font.BOLD, 12));
			btnNewButton_3.setBounds(36, 260, 87, 23);
			panel_7.add(btnNewButton_3);
//		}
		
		lblNewLabel_3_3_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	ChangePassword frame = new ChangePassword(name);
				frame.getContentPane();
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);
		    }
			    
		});
	}
}
