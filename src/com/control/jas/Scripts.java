package com.control.jas;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sql.jas.SqlConnect;

public class Scripts {
	
	SqlConnect sqlConn = new SqlConnect();
	
	public String getVersion() throws SQLException {
		String select = "select version from version";
		String version = null;
		ResultSet resultSet = sqlConn.sm.executeQuery(select);
		
		while(resultSet.next())
		{
			version = resultSet.getString(1);
		}
		
		return version;
	}
	
	public void changeVersion(String version) throws SQLException {
		String select = "UPDATE version set version='" + version + "'";
		sqlConn.sm.executeUpdate(select);
	}
	
	public void refreshTextArea(JTextArea textArea, JCheckBox setAcVisble, JComboBox statusBox) {
		textArea.setText("");
		String userSelect = statusBox.getSelectedItem().toString();
		if (setAcVisble.isSelected())
		{
			if (userSelect.equals("全部"))
				try {
					ResultSet rs = sqlConn.find("*");
					ResultSetMetaData rsmd = rs.getMetaData();
					textArea.append(" ");
					int countLine = 0;
					for (int i = 1 ; i <= rsmd.getColumnCount() ; i++)
					{
						textArea.append(rsmd.getColumnLabel(i) + "\t| ");
					}
					textArea.append("\n");
					textArea.append("—————————————————————————————————————————————————————————\n");
					while (rs.next())
					{
						textArea.append(" " + rs.getString(1) + 
								"\t|  " +  rs.getString(2) + 
								"\t|  " + rs.getString(3) + 
//									"\t|  " + rs.getTimestamp(4).toString().substring(5, 19) +
								"\t|  " + rs.getString(4) +
								"\t|  " + rs.getString(5) + 
								"\t|  " + rs.getString(6) + 
								"\t|  " + rs.getString(7) + 
								"\t|  " + rs.getString(8) + 
								"\t|  " +
								"\n"
						);
						textArea.append("—————————————————————————————————————————————————————————\n");
						countLine++;
					}
					textArea.setCaretPosition(0);
					textArea.insert(" 搜尋 ' "+ userSelect + " '\t結果總共有 : " + countLine + " 隻角色\n", 1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else
			{
				try {
					ResultSet rs = sqlConn.find(userSelect);
					ResultSetMetaData rsmd = rs.getMetaData();
					textArea.append(" ");
					int countLine = 0;
					for (int i = 1 ; i <= rsmd.getColumnCount() ; i++)
					{
						textArea.append(rsmd.getColumnLabel(i) + "\t| ");
					}
					textArea.append("\n");
					textArea.append("————————————————————————————————————————————————————————\n");
					while (rs.next())
					{
						textArea.append(" " + rs.getString(1) + 
								"\t|  " +  rs.getString(2) + 
								"\t|  " + rs.getString(3) + 
//									"\t|  " + rs.getTimestamp(4).toString().substring(5, 19) +
								"\t|  " + rs.getString(4) + 
								"\t|  " + rs.getString(5) + 
								"\t|  " + rs.getString(6) + 
								"\t|  " + rs.getString(7) + 
								"\t|  " + rs.getString(8) + 
								"\t|  " +
								"\n"
						);
						textArea.append("————————————————————————————————————————————————————————\n");
						countLine++;
					}
					textArea.setCaretPosition(0);
					textArea.insert(" 搜尋 ' "+ userSelect + " '\t結果總共有 : " + countLine + " 隻角色\n", 1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else
		{
			if (userSelect.equals("全部"))
				try {
					ResultSet rs = sqlConn.find("*");
					ResultSetMetaData rsmd = rs.getMetaData();
					textArea.append(" ");
					int countLine = 0;
					for (int i = 1 ; i <= rsmd.getColumnCount() ; i++)
					{
						if (rsmd.getColumnLabel(i).equals("樂豆帳號") || rsmd.getColumnLabel(i).equals("樂豆密碼"))
							continue;
						textArea.append(rsmd.getColumnLabel(i) + "\t| ");
					}
					textArea.append("\n");
					textArea.append("———————————————————————————————————————————\n");
					while (rs.next())
					{
						textArea.append(" " + rs.getString(1) + 
								"\t|  " +  rs.getString(2) + 
								"\t|  " + rs.getString(3) + 
//									"\t|  " + rs.getTimestamp(4).toString().substring(5, 19) +
								"\t|  " + rs.getString(4) +
								"\t|  " + rs.getString(5) + 
								"\t|  " + rs.getString(6) + 
								"\t|  " +
								"\n"
						);
						textArea.append("———————————————————————————————————————————\n");
						countLine++;
					}
					textArea.setCaretPosition(0);
					textArea.insert(" 搜尋 ' "+ userSelect + " '\t結果總共有 : " + countLine + " 隻角色\n", 1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else
			{
				try {
					ResultSet rs = sqlConn.find(userSelect);
					ResultSetMetaData rsmd = rs.getMetaData();
					textArea.append(" ");
					int countLine = 0;
					for (int i = 1 ; i <= rsmd.getColumnCount() ; i++)
					{
						if (rsmd.getColumnLabel(i).equals("樂豆帳號") || rsmd.getColumnLabel(i).equals("樂豆密碼"))
							continue;
						textArea.append(rsmd.getColumnLabel(i) + "\t| ");
					}
					textArea.append("\n");
					textArea.append("———————————————————————————————————————————\n");
					while (rs.next())
					{
						textArea.append(" " + rs.getString(1) + 
								"\t|  " +  rs.getString(2) + 
								"\t|  " + rs.getString(3) + 
//									"\t|  " + rs.getTimestamp(4).toString().substring(5, 19) +
								"\t|  " + rs.getString(4) + 
								"\t|  " + rs.getString(5) + 
								"\t|  " + rs.getString(6) + 
								"\t|  " +
								"\n"
						);
						textArea.append("———————————————————————————————————————————\n");
						countLine++;
					}
					textArea.setCaretPosition(0);
					textArea.insert(" 搜尋 ' "+ userSelect + " '\t結果總共有 : " + countLine + " 隻角色\n", 1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public ResultSet getJobs() throws SQLException {
		String select = "select job from jobs";
		ResultSet resultSet = sqlConn.sm.executeQuery(select);
		
		return resultSet;
	}
	
	public void refreshMessage(JComboBox name, JComboBox statusBox, JComboBox jobBox, JTextField levelText, JTextField noticeText) throws SQLException {
		String select = "select status,job,level,notice from account where name='" + name.getSelectedItem().toString() + "'";
		ResultSet resultSet = sqlConn.sm.executeQuery(select);
		
		while (resultSet.next())
		{
			statusBox.setSelectedItem(resultSet.getString(1));
			jobBox.setSelectedItem(resultSet.getString(2));
			levelText.setText(resultSet.getString(3));
			noticeText.setText(resultSet.getString(4));
		}
		
	}
	
}
