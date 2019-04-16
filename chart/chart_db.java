package chart;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class chart_db {
	Connection conn;
	
	boolean jdbcCheck( String path ) {
		boolean check = false;
		
		try {
			Class.forName( path );
			check = true; 
		} catch (ClassNotFoundException e) {
			
		}
		
		return check;
		
	}
	
	boolean connectDb( String path ) {
		boolean check = false;
		
		try {
			
			conn = DriverManager.getConnection( path );
			
			check = true; 
		} catch (SQLException e) {
		
		} 
		
		return check;
	}
	

	void defTable( int num) {
		
		try {
			Statement s = conn.createStatement();
			
			String query = null;
			if( num == 0 ) { // DROP
				query = "DROP TABLE Chartlist";
			}
			else { // CREATE 
				query = "CREATE TABLE Chartlist(Genre TEXT, Rank TEXT, Song TEXT, Artist TEXT, Album TEXT)";
			}
			s.execute(query);
			
			
			s.close();
		} catch (SQLException e) {

		}
	}
	
	
	void insertData(String Genre, String Rank, String Song, String Artist, String Album) {
		
		
		try {
			
			String query = "INSERT INTO Chartlist (Genre, Rank, Song, Artist, Album) values(?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
	
			ps.setString(1, Genre);
			ps.setString(2, Rank);
			ps.setString(3, Song);
			ps.setString(4, Artist);
			ps.setString(5, Album);
			
			ps.executeUpdate(); 

			ps.close();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	void deleteData() {
		
		try {
			Statement s = conn.createStatement();
			String query = "DELETE FROM Chartlist";
			
			s.execute(query);
			
		
			s.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	ArrayList<chartVO> selectData_genre(String genre) {

		ArrayList<chartVO> list = new ArrayList<>();
		
		try {
			Statement s = conn.createStatement();
		
			String query = String.format("SELECT * FROM Chartlist WHERE Genre = '%s'", genre );
			ResultSet r = s.executeQuery(query);

			while ( r.next() ) { 
				String Genre;
				String Rank;
				String Song;
				String Artist;
				String Album;
				
				Genre	= r.getString("Genre");
				Rank	= r.getString("Rank");
				Song	= r.getString("Song");
				Artist	= r.getString("Artist");
				Album	= r.getString("Album");
				
				list.add( new chartVO(Genre, Rank, Song, Artist, Album) );
				if(list.size() == 10) {
					break;
				}
				 	
			}
			r.close();
			s.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	ArrayList<chartVO> selectData_artist(String artist) {
		ArrayList<chartVO> list = new ArrayList<>();
		
		try {
			Statement s = conn.createStatement();
			String query = String.format("SELECT * FROM Chartlist WHERE Artist = '%s'", artist );
			ResultSet r = s.executeQuery(query);
	
			while ( r.next() ) {
				String Genre;
				String Rank;
				String Song;
				String Artist;
				String Album;
				
				Genre	= r.getString("Genre");
				Rank	= r.getString("Rank");
				Song	= r.getString("Song");
				Artist	= r.getString("Artist");
				Album	= r.getString("Album");
				
				list.add( new chartVO(Genre, Rank, Song, Artist, Album) );
			}
			r.close();
			s.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public chart_db() {
		
		String projectPath_db 	= System.getProperty("user.dir");
		String dbPath 			= projectPath_db + "\\lib\\sqlite\\chartlist.db3";
		String connPath			= "jdbc:sqlite:" + dbPath;
		
		if( jdbcCheck("org.sqlite.JDBC") == false ) {
			JOptionPane.showMessageDialog(null, "jdbc 라이브러리를 확인하세요");
			return; // main 종료
			
		}
		
		if( !connectDb( connPath ) ) {
			JOptionPane.showMessageDialog(null, "db 연결 정보를 확인하세요.\n" + connPath);
			return; 
		}

		defTable(1);
	}
	
	
}
