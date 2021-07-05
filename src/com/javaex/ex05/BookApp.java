package com.javaex.ex05;

import java.util.List;
import java.util.Scanner;


public class BookApp {

	public static void main(String[] args) {
		
		TableSequenceDao tsd = new TableSequenceDao();
		Scanner input = new Scanner(System.in);
		BookDao bookDao = new BookDao();
		AuthorDao authorDao = new AuthorDao();
		
		//Delete(초기화)
//		tsd.dropAuthor();
//		tsd.dropBook();
//		tsd.dropSequence("author");
//		tsd.dropSequence("book");
		//작가 테이블 책 테이블 작성
		tsd.createAuthor();
		tsd.createBook();
		//작가 테이블 시퀀스, 책 테이블 시퀀스 작성
		tsd.createSequence("book");
		tsd.createSequence("author");
		
		//authorDao.authorInsert(); 작가6명 추가
		AuthorVo iAuthorVo1 = new AuthorVo("이문열", "경북 영양");
		AuthorVo iAuthorVo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo iAuthorVo3 = new AuthorVo("유시민", "17대 국회의원");
		AuthorVo iAuthorVo4 = new AuthorVo("기안84", "기안동에서 산 84년생");
		AuthorVo iAuthorVo5 = new AuthorVo("강풀", "온라인 만화가 1세대");
		AuthorVo iAuthorVo6 = new AuthorVo("김영하", "알쓸잡신");
		AuthorVo iAuthorVo7 = new AuthorVo("김영기", "돼지");
		authorDao.authorInsert(iAuthorVo1);
		authorDao.authorInsert(iAuthorVo2);
		authorDao.authorInsert(iAuthorVo3);
		authorDao.authorInsert(iAuthorVo4);
		authorDao.authorInsert(iAuthorVo5);
		authorDao.authorInsert(iAuthorVo6);
		authorDao.authorInsert(iAuthorVo7);
		
		//bookDao.bookInsert(); 책 8개 추가
		BookVo iBookVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		BookVo iBookVo2 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		BookVo iBookVo3 = new BookVo("토지", "마로니에북스", "2012-08-15", 2);
		BookVo iBookVo4 = new BookVo("자바 프로그래밍 입문", "위키북스", "2015-04-01", 3);
		BookVo iBookVo5 = new BookVo("패션왕", "'중앙북스(books)", "2012-02-22", 4);
		BookVo iBookVo6 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		BookVo iBookVo7 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		BookVo iBookVo8 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(iBookVo1);
		bookDao.bookInsert(iBookVo2);
		bookDao.bookInsert(iBookVo3);
		bookDao.bookInsert(iBookVo4);
		bookDao.bookInsert(iBookVo5);
		bookDao.bookInsert(iBookVo6);
		bookDao.bookInsert(iBookVo7);
		bookDao.bookInsert(iBookVo8);
		//책수정, 삭제 각자 알아서 테스트
		//Update
		AuthorVo uAuthorVo= new AuthorVo(3, "김영기", "서울특별시");
		int uCount = authorDao.authorUpdate(uAuthorVo);
		if(uCount>0) {
			System.out.println("[수정되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + uCount + ")]");
		}
		//Delete
		int dCount = authorDao.authorDelete(7);
		if(dCount>0) {
			System.out.println("[삭제되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + dCount + ")]");
		}
		
		//책리스트 출력
		List <BookVo> bookList;
		bookList = bookDao.getBookList();
		printList(bookList);
		
		
		//스캐너를 통해서 사용자한테 키워드 입력받음
		System.out.println("검색어를 입력해주세요");
		//"검색어: 문"
		String search = input.nextLine();
		bookList = bookDao.bookSearch(search);
		printList(bookList);
		
		//책 정보 출력 되도록
		input.close();
	}
	
	public static void printList(List<BookVo> bookList) {
		
		for(int i=0; i<bookList.size(); i++) {
			
			
			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId() + "," + bookVo.getTitle() + "," + bookVo.getPubs() + "," + bookVo.getPubDate() + "," + bookVo.getAuthorName());
			
			/*
			AuthorVo authorVo = authorList.get(i);
			
			int authorId = authorVo.getAuthorId();
			String authorName = authorVo.getAuthorName();
			String authorDesc = authorVo.getAuthorDesc();
			
			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
			*/
			
			
			/*
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
			*/
		}
		
		System.out.println("=================================");
		System.out.println("");
		
	}

}
