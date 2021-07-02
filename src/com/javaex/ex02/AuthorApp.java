package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList = authorDao.getAuthorList();

		//리스트출력
		printList(authorList);
		//작가등록
		int iCount = authorDao.authorInsert("김영기", "뚱이");
		if (iCount>0) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}

//		//리스트출력
		authorList = authorDao.getAuthorList();
		printList(authorList);

//		//작가수정
		int uCount = authorDao.authorUpdate(3, "김영기", "뚱이");
		if (uCount>0) {
			System.out.println("[수정되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요(" + uCount + ")]");
		}
//		//리스트출력
		authorList = authorDao.getAuthorList();
		printList(authorList);
		
//		//작가삭제
		int dCount = authorDao.authorDelete(7);
		if (dCount>0) {
			System.out.println("[삭제되었습니다.]");
		} else {
			System.out.println("[관리자에게 문의하세요ㄴ(" + uCount + ")]");
		}
//		//리스트출력
		authorList = authorDao.getAuthorList();
		printList(authorList);
		
		
//		authorDao.getAuthorOne();
//		//리스트출력
//		authorList = authorDao.getAuthorList();
//		printList(authorList);

	}

	public static void printList(List<AuthorVo> authorList) {
		// 리스트를 for문으로 출력
//		for(int i = 0; i < authorList.size(); i++) {
			
//			AuthorVo authorVo = authorList.get(i);
			
//			int authorId = authorVo.getAuthorId();
//			String authorName = authorVo.getAuthorName();
//			String authorDesc = authorVo.getAuthorDesc();
			
//			System.out.println(authorId + "\t" + authorName + "\t\t" + authorDesc);
		
		//향상된 for문
		for (AuthorVo list : authorList) {
			String authorDesc = list.getAuthorDesc();
			int authorId = list.getAuthorId();
			String authorName = list.getAuthorName();
			System.out.println(authorName + "\t \t" + authorId + "\t" + authorDesc);
		}
	}
}
