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
		String time =(System.currentTimeMillis()/1000)+"";
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
			qsnResult = qsnResult + res.getString("id")+"@"+res.getString("Qsns")+"@"+res.getString("TimePosted")+"<--->";
			/*System.out.println(res.getString("id") + " " + res.getString("age")
					+ " " + res.getString("firstName") + " "
					+ res.getString("sex") + " " + res.getString("weight")
					+ " " + res.getString("height"));*/
		}
		return qsnResult;
	}
	public void updateRspns(String Qsn_ID, String response) throws Exception{		
		int id = Integer.parseInt(Qsn_ID);
		ResultSet rs = stat.executeQuery("SELECT YesCount, NoCount FROM Questions WHERE id = "+ id);
		int yesCount = rs.getInt("YesCount");
		int noCount = rs.getInt("NoCount");
		if(response.equals("Yes")){
			yesCount+=1;
			PreparedStatement ps = con.prepareStatement("UPDATE Questions SET YesCount = ? WHERE id = ?");
			ps.setInt(1,yesCount);
			ps.setInt(2,id);
			ps.executeUpdate();
		    ps.close();
		}else{
			noCount+=1;
			PreparedStatement ps = con.prepareStatement("UPDATE Questions SET NoCount = ? WHERE id = ?");
			ps.setInt(1,noCount);
			ps.setInt(2,id);
			ps.executeUpdate();
		    ps.close();
		}			
	}
	public String getStats() throws Exception{
		String statsResult = "";
		int i = 0;
		ResultSet res = stat.executeQuery("select * from Questions");
		while (res.next()) {
			statsResult = statsResult + res.getString("id")+"@"+res.getString("Qsns")+"@"+res.getInt("YesCount")+"@"+res.getInt("NoCount")+"@"+res.getString("TimePosted")+"<--->";
		}
		return statsResult;
	}
}
