package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.book;
import connectionprovider.ConnectionProvider;

public class SearchBooksCommand {

	public book execute(String title) {
		book s = new book();
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM books WHERE title = ?");
			stmt.setString(1, title);
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

}
