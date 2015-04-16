package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.book;
import connectionprovider.ConnectionProvider;

public class CreateBooksCommand {

	public String execute(book s) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO books(title, author) VALUES(?, ?) Returning id");
			stmt.setString(1, s.getTitle());
			stmt.setString(2, s.getAuthor());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("id");
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	public static void main(String[] args) {
		CreateBooksCommand demo = new CreateBooksCommand();
		book aa = new book();
		
			aa.setTitle("firstbook");
			aa.setAuthor("firstauthor");
		//demo.execute(aa);
		System.out.println(demo.execute(aa));
	}

}
