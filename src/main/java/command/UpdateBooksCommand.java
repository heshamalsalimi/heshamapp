package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.book;
import connectionprovider.ConnectionProvider;

public class UpdateBooksCommand {

	public String execute(book s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("UPDATE BOOKS SET title=?, authort=? WHERE id=?");
			stmt.setString(1, s.getTitle());
			stmt.setString(2, s.getAuthor());
			stmt.setInt(3, s.getId());
			stmt.executeUpdate();

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
