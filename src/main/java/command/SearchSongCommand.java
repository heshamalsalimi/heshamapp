package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Song;
import connectionprovider.ConnectionProvider;

public class SearchSongCommand {

	public Song execute(String title) {
		Song s = new Song();
		try {
			Connection connection = ConnectionProvider.getConnection();
			// Statement stmt = connection.createStatement();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM Songs WHERE title = ?");
			stmt.setString(1, title);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				s.setArtist(rs.getString("artist"));
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
