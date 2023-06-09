package br.com.zago.model;

public class BookSample {

	private String name;
	private String author;
	private int pages;

	public BookSample(String name, String author, int pages) {
		this.name = name;
		this.author = author;
		this.pages = pages;
	}

	public BookSample() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "BookSample [name=" + name + ", author=" + author + ", pages=" + pages + "]";
	}

	
}
