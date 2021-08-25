package com.sql.jas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import javax.swing.JRadioButton;

public class SqlConnect {
	
	public static Connection conn;
	public static Statement sm;
	public static ResultSet rs;

	public SqlConnect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver連接成功");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver連接失敗");
		}
		
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://104.199.216.245/controlsystem?"
					+ "serverTimezone=GMT&useSSL=false&" + "user=jas&password=1234");
			this.sm = conn.createStatement();
			System.out.println("連線成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("連線失敗");
		}
	}
	
	public int CheckAccount(String name, boolean save) throws SQLException {
		
		if (name.equals(""))
			return 3;
		
		String select = "select name from users";
		ResultSet resultSet = this.sm.executeQuery(select);
		
		while (resultSet.next())
		{
			if (resultSet.getString(1).equals(name))
			{
				if (save)
				{
					try {
						writeAccountFile(name);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return 1;
			}
		}
		
		return 2;
	}
	
	public void writeAccountFile(String user) throws IOException {
		FileWriter fileWriter = new FileWriter("maplecontrol.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		bufferedWriter.write(user);
		bufferedWriter.flush();
		fileWriter.close();
	}
	
	public ResultSet GetAccount() throws SQLException {
		String select = "select name from account";
		ResultSet resultSet = this.sm.executeQuery(select);
		
		return resultSet;
	}
	
	public ResultSet find(String select) throws SQLException {
		String sql;
		
		if (select.equals("*"))
			sql = "select name 名稱,status 狀態,user 使用者,notice 備註,beanaccount 樂豆帳號,beanpassword 樂豆密碼 from account";
		else
			sql = "select name 名稱,status 狀態,user 使用者,notice 備註,beanaccount 樂豆帳號,beanpassword 樂豆密碼 from account where status='" + select + "';";
				
		ResultSet resultSet = this.sm.executeQuery(sql);
		
		return resultSet;
	}
	
	public int update(String name, String status, String user, String notice) throws SQLException {
		if (name.equals("請選擇"))
			return 1;
		
		if (status.equals("請選擇"))
			return 2;
		
		String updateStr;
		if (status.equals("掛錢中") || status.equals("練等中"))
			updateStr = "UPDATE account SET status='" +  status + "', user='" + user + "', notice = '" + notice + "' WHERE name='" + name + "';";
		else
			updateStr = "UPDATE account SET status='" +  status + "', user='無', notice = '" + notice + "' WHERE name='" + name + "';";
		
		this.sm.executeUpdate(updateStr);
		
		return 0;
	}
	
	public boolean createAccount(String selectAccount, String beanAccount, String beanPassword) {
		String sql = "UPDATE account SET beanaccount='" + beanAccount + "', beanpassword='" + beanPassword + "' WHERE name='" + selectAccount + "';";
		
		try {
			this.sm.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int insert(int row, int num) throws SQLException {
		for (int i = row + 1 ; i <= row + num ; i++)
		{
			for (int j = 1 ; j <= 2 ; j++)
			{
				String insertsql = "INSERT INTO account (name) VALUES ('random" + i + "t" + j + "');";
				this.sm.executeUpdate(insertsql);
			}
		}
		return 0;
	}
	
	public ResultSet getUpdateImformation() throws SQLException {
		String sql = "Select * from imformation";
		ResultSet rs = this.sm.executeQuery(sql);
		
		return rs;
	}
	
	public int getUserData(String name) throws SQLException {
		String sql = "select permission from users where name='" + name + "';";
		ResultSet rs = this.sm.executeQuery(sql);
		
		while (rs.next())
		{
			return rs.getInt(1);
		}
		
		return 0;
	}
	
	public String[] getUserList(String name) throws SQLException {
		String sql = "select name from users where not name='" + name +"'";
		ResultSet rs = this.sm.executeQuery(sql);
		int count = 0;
		while (rs.next())
		{
			count++;
		}
		String[] list = new String[count];
		ResultSet rs1 = this.sm.executeQuery(sql);
		int i = 0;
		while (rs1.next())
		{
			list[i] = rs1.getString(1);
			i++;
		}
		
		return list;
	}

	public int manageUser(String adminName, String userName, JRadioButton[] btnlist) throws SQLException {
		int adminPermissionLevel = 0;
		int selectUserPermissionLevel = 0;
		
		String sql = "select * from users where name='" + adminName +"'";
		ResultSet rs = this.sm.executeQuery(sql);
		while(rs.next())
		{
			adminPermissionLevel = rs.getInt(3);
		}
		
		String sql1 = "select * from users where name='" + userName +"'";
		ResultSet rs1 = this.sm.executeQuery(sql1);

		while(rs1.next())
		{
			selectUserPermissionLevel = rs1.getInt(3);
		}
		
		if (!btnlist[0].isSelected() && !btnlist[1].isSelected() && !btnlist[2].isSelected())
			return 4;
		
		if (btnlist[0].isSelected() && adminPermissionLevel > 1)
		{
			String sqldel = "delete from users where name='" + userName + "'";
			this.sm.executeQuery(sqldel);
		}
		else if (adminPermissionLevel == 1)
			return 0;
		
		
		if (btnlist[1].isSelected() && adminPermissionLevel > 1)
		{
			if (selectUserPermissionLevel > 1)
				return 1;
			
			String upPermissionLevel = "update users set permission=" + (selectUserPermissionLevel + 1) + " where name='" + userName + "'";
			this.sm.executeUpdate(upPermissionLevel);
		}
		else if (adminPermissionLevel == 1)
			return 0;
		
		if (btnlist[2].isSelected() && adminPermissionLevel > 1)
		{
			if (selectUserPermissionLevel < 1)
				return 2;
			
			String upPermissionLevel = "update users set permission=" + (selectUserPermissionLevel - 1) + " where name='" + userName + "'";
			this.sm.executeUpdate(upPermissionLevel);
		}
		else if (adminPermissionLevel == 1)
			return 0;

		
		return 3;
	}
	
	public ResultSet getLinkImformation(String dataBaseName) throws SQLException {
		String sql = "select * from " + dataBaseName;
		ResultSet rs = this.sm.executeQuery(sql);
		
		return rs;
	}
}
