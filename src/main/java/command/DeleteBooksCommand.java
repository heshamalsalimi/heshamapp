package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.book;
import connectionprovider.ConnectionProvider;

public class DeleteBooksCommand {

	public String execute(book s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("DELETE FROM BOOK WHERE title=?, author=? WHERE id=?");
			stmt.setString(1, s.getTitle());
			stmt.setString(2, s.getAuthor());
			stmt.setInt(3, s.getId());
			stmt.executeQuery();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
