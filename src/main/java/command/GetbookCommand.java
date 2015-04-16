package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.book;
import connectionprovider.ConnectionProvider;

public class GetbookCommand {

	public book execute(int id) {
		book s = new book();
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM books WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				s.setAuthor(rs.getString("author"));
				s.setTitle(rs.getString("title"));
				s.setId(rs.getInt("id"));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static void main(String[] args) {
		GetbookCommand demo = new GetbookCommand();
		book b = demo.execute(1);
		System.out.println(b.getAuthor());System.out.println(b.getTitle());
		
	}

}
