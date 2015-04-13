package controllers;
import java.sql.*;

public class SQLDB {


	private static Connection con;
	private Statement stat;
	//private String[] qsnResult[];
	public SQLDB()  {

		// sqlite driver
		try {
			Class.forName("org.sqlite.JDBC");

			// database path, if it's new database,
			// it will be created in the project folder

			con = DriverManager.getConnection("jdbc:sqlite:mydb.db");
			stat = con.createStatement();
			stat.executeUpdate("drop table if exists Questions");

			//creating table
			stat.executeUpdate("create table Questions"+"(id integer,"+ "Qsns TEXT,"+ "YesCount INT," + "NoCount INT,"+ "TimePosted integer," + "primary key (id));");//varchar(30)
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertQsn(String Qsn) throws Exception{
		// inserting data
		String time =System.currentTimeMillis()+"";
		PreparedStatement prep = con.prepareStatement("insert into Questions values(?,?,?,?,?);");
		prep.setString(2, Qsn);
		prep.setString(3, "0");
		prep.setString(4, "0");
		prep.setString(5, time);		
		prep.execute();
	}
	public String getQsn() throws Exception{

		// getting data
		String qsnResult = "";
		int i =0;
		ResultSet res = stat.executeQuery("select * from Questions");
		while (res.next()) {
			//qsnResult[i] = res.getString("id")+"$"+res.getString("Question")+"$"+res.getString("TimePosted");
			//i++;
			qsnResult = qsnResult + res.getString("id")+"_"+res.getString("Qsns")+"_"+res.getString("YesCount")+"_"+res.getString("TimePosted")+"<--->";
			/*System.out.println(res.getString("id") + " " + res.getString("age")
					+ " " + res.getString("firstName") + " "
					+ res.getString("sex") + " " + res.getString("weight")
					+ " " + res.getString("height"));*/
		}
		return qsnResult;
	}
}
