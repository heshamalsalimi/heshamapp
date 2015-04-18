package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.book;
import command.CreateBooksCommand;

import command.GetbookCommand;
import command.ListBooksCommand;

import util.Constants;

@Path("book")
public class Services {
	ObjectMapper mapper = new ObjectMapper();

	// Browse all books
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response browbooks(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		ListBooksCommand command = new ListBooksCommand();
		ArrayList<book> list = command.execute();
		for(book i : list){
			System.out.println(i.getAuthor());System.out.println(i.getTitle());
			
		}
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String booksString = null;
		try {
			booksString = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(booksString).build();
	}

	// get books by Id
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBooks(@PathParam("id") int id) {
		GetbookCommand command = new GetbookCommand();
		String booksString = null;
		try {
			booksString = mapper.writeValueAsString(command.execute(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(booksString).build();
	}

	// Add a book
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSongs(String payload) {
		CreateBooksCommand create = new CreateBooksCommand();
		book s = null;
		String i = "";
		try {
			s = mapper.readValue(payload, book.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			i = create.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}
	
	
}
