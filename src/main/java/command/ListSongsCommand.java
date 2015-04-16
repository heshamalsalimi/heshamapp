package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.book;
import connectionprovider.ConnectionProvider;

public class ListSongsCommand {

	public ArrayList<book> execute() {
		ArrayList<book> ret = new ArrayList<book>();
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM books");
			while (rs.next()) {
				book s = new book();
				s.setAuthor(rs.getString("author"));
				s.setTitle(rs.getString("title"));
				s.setId(rs.getInt("id"));
				ret.add(s);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static void main(String[] args) {
		ListSongsCommand demo = new ListSongsCommand();
		ArrayList<book>  b = demo.execute();
		for(book i : b){
			System.out.println(i.getAuthor());System.out.println(i.getTitle());
			
		}
		
		
	}

}
