package com.javaex.ex05;

public class BookVo {

	// Field
	private int bookId;
	private String title;
	private String pubs;
	private String pubDate;
	private int authorId;

	// Constructor
	public BookVo() {
		super();
	}

	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorId = authorId;
	}

	// DB에서 Like 사용

	// Method - G/S
	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getPubs() {
		return pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	// Method - Ordinary
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate
				+ ", authorId=" + authorId + "]";
	}
	
	public String printList() {
		return bookId + ", " + title + ", " + pubs + ", " + pubDate + ", " + authorId;
	}

}
