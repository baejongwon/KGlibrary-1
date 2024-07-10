package com.kg.library_1.bookHope;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kg.library_1.PageService;

import jakarta.servlet.http.HttpSession;

@Service
public class BookHopeService {

    
	@Autowired IBookHopeMapper mapper;
	@Autowired 
	private HttpSession session;
	
	public void bookHopeForm(String cp, Model model, String search, String select) { //책 검색 게시판
	
			int currentPage = 1;
			try{
				currentPage = Integer.parseInt(cp);
			}catch(Exception e){
				currentPage = 1;
			}
			
			int pageBlock = 10; // 한 페이지에 보일 데이터의 수 
			int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
			int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
			

			List<BookHopeDTO> boards = mapper.bookHopeForm(begin, end, select, search); //도서 리스트
			int totalCount = mapper.totalCount(select, search); //희망 도서 리스트가 0인지 아닌지
			if(totalCount == 0) { // db에 리스트 정보가 없으면(0) 되돌아 감.
				return ;
				
			}
			
			String url = "bookHopeForm?select=" + select + "&search=" + search + "&currentPage=";
			String result = PageService.printPage(url, totalCount, pageBlock, currentPage);
			

			model.addAttribute("count", totalCount);
			model.addAttribute("boards", boards);
			model.addAttribute("result", result);
		}

	public String bookHopeRegistProc(BookHopeDTO bookHopeDTO) { // 책 등록
			
			
			String sessionId = (String) session.getAttribute("id");
			if (sessionId == null)
				return "redirect:../login";
			
			BookHopeDTO board = new BookHopeDTO();
			
			board.setBoard_title(bookHopeDTO.getBoard_title()); //제목		
			board.setHope_user(sessionId); //신청자
			board.setCategory(bookHopeDTO.getCategory()); // 카테고리
			board.setTitle_info(bookHopeDTO.getTitle_info()); //도서 제목
			board.setAuthor_info(bookHopeDTO.getAuthor_info()); // 저작자
			
			String pub_info = bookHopeDTO.getPub_info(); //발행자
			if(pub_info == null || pub_info.trim().isEmpty()) { //발행자가 없을 때 비움
			board.setPub_info(" ");} 
			else { //발행자
			board.setPub_info(pub_info);}
			
			board.setPub_year_info(bookHopeDTO.getPub_year_info()); // 발행연도
			
			String contents = bookHopeDTO.getReason(); //testarea enter값 변경 후 저장
			contents = contents.replace("\r\n","<br>"); // 수정을 하겠다면 그부분 다시 relpace 해줘야함
			board.setReason(contents); //신청 이유
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			board.setHopedate(sdf.format(new Date())); //신청 작성일
	
			mapper.bookHopeRegistProc(board);
			
			
			return "redirect:bookHopeForm";

	}

	public BookHopeDTO bookHopeContent(String no, Model model) { // 책 상세정보
		
		int n = 1;
		try{
			n = Integer.parseInt(no);
		}catch(Exception e){
			return null;
		}
		
		BookHopeDTO board = mapper.bookHopeContent(n);
		return board;
	}

	
	public void bookHopeDeleteProc(String no) {
		int n = 1;
		try{
			n = Integer.parseInt(no);
		}catch(Exception e){
		}
		
		mapper.bookHopeDeleteProc(n);
	}
		
	
	
}
