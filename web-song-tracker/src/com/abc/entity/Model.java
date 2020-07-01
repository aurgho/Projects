package com.abc.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Model {

	private String userName;
	private String email;
	private String pwd;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	private List<String> song_list;

	public Model() {

		try {
			//load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//get the connection
			ResourceBundle bundle = ResourceBundle.getBundle("com.abc.utility.info");
			con = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("pwd"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<String> getSong_list() {
		return song_list;
	}

	public void setSong_list(List<String> song_list) {
		this.song_list = song_list;
	}

	@Override
	public String toString() {
		return "Model [userName=" + userName + ", email=" + email + ", pwd=" + pwd + ", con=" + con + "]";
	}

	public boolean signUp()
	{
		boolean result = false;
		try {
			//prepare the platform
			pstmt = con.prepareStatement("insert into songlover(user_name,email,pwd) values(?,?,?)");

			//set the data
			pstmt.setString(1, userName);
			pstmt.setString(2, email);
			pstmt.setString(3, pwd);

			//execute query
			result = (1==pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con,pstmt,null);
		}
		return result;
	}

	public boolean login()
	{
		boolean result=false;
		try {
			//prepare platform
			pstmt = con.prepareStatement("select * from songlover where email=? and pwd=?");

			//set the data
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);

			//execute query
			res = pstmt.executeQuery();

			if(res.next())
				result=true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, res);
		}
		return result;
	}

	public void insert(String engine,String song)
	{
		try {

			//prepare the platform
			if(engine.equals("Ganna"))
				pstmt = con.prepareStatement("insert into gannahistory(email,song_list) values(?,?)");
			else if(engine.equals("Youtube"))
				pstmt = con.prepareStatement("insert into youtubehistory(email,song_list) values(?,?)");
			else
				pstmt = con.prepareStatement("insert into googlehistory(email,song_list) values(?,?)");

			//set the data
			pstmt.setString(1, email);
			pstmt.setString(2, song);

			//execute  query
			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, null);
		}
	}


	public void engineHistory(String engine)
	{
		try {

			//prepare the platform
			if(engine.equals("Ganna"))
				pstmt = con.prepareStatement("select * from gannahistory where email = ?");
			else if(engine.equals("Youtube"))
				pstmt = con.prepareStatement("select * from youtubehistory where email = ?");
			else
				pstmt = con.prepareStatement("select * from googlehistory where email = ?");

			//set the data
			pstmt.setString(1, email);

			//execute query
			res = pstmt.executeQuery();

			//fetch the data
			if(song_list==null)
				song_list = new ArrayList<String>();

			while(res.next())
			{
				song_list.add(res.getString(2));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, res);
		}
	}

	public void deleteHistory(String engine)
	{
		try {

			//prepare the platform
			if(engine.equals("Ganna"))
				pstmt = con.prepareStatement("delete from gannahistory where email = ?");
			else if(engine.equals("Youtube"))
				pstmt = con.prepareStatement("delete from youtubehistory where email = ?");
			else
				pstmt = con.prepareStatement("delete from googlehistory where email = ?");

			//set the data
			pstmt.setString(1, email);

			//execute query
			pstmt.executeUpdate();


		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, null);
		}

	}

	
	public boolean check()
	{
		boolean result = false;
		try {
			//prepare the platform
			pstmt = con.prepareStatement("select * from songlover where email = ? and user_name= ?");
			
			//set data
			pstmt.setString(1, email);
			pstmt.setString(2, userName);
			
			//execute Query
			res = pstmt.executeQuery();
			
			//check user
			result=res.next();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, res);
		}
		
		return result;
	}
	
	
	public boolean reset()
	{
		boolean result = false;
		try {
			//prepare the platform
			pstmt = con.prepareStatement("update songlover set pwd = ? where email=?");
			
			//set data
			pstmt.setString(1, pwd);
			pstmt.setString(2, email);
			
			//execute query
			result = (1==pstmt.executeUpdate());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, null);
		}
		return result;
	}
	
	
	public boolean checkEmail()
	{
		boolean result = true;
		try {
			//prepare the platform
			pstmt = con.prepareStatement("select * from songlover where email = ?");
			
			//set data
			pstmt.setString(1, email);
			
			//execute query
			res = pstmt.executeQuery();
			
			//check if its a duplicate email id
			result = res.next();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeObjects(con, pstmt, res);
		}
		return result;
	}


	private void closeObjects(Connection con, Statement stmt, ResultSet res) {

		try {
			if(con!=null)
				con.close();
			if(stmt!=null)
				stmt.close();
			if(res!=null)
				res.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}




}





