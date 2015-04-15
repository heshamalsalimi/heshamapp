package model;

public class book {
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public int getId() {
		return isbn;
	}
	public void setId(int isbn) {
		this.isbn = isbn;
	}
	String title;
	String auther;
	int isbn;
}
