package com.kg.library_1.bookHope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookHopeController {

	@Autowired
	private BookHopeService service;
	@Autowired 
	private HttpSession session;
	
	@RequestMapping("book/bookHopeForm") // 희망 도서 url
	public String bookHopeForm(String search,Model model,
			@RequestParam(value="currentPage", required = false)String cp, String select, 
			@RequestParam(name = "parameterName", required = false) String Id) {

			if(search == null || search.trim().isEmpty()) {
				search = ""; select = "title"; 
			}
			model.addAttribute("menu", "board2");
			service.bookHopeForm(cp, model, search, select); //DB 검색 및 정렬

		return "bookHope/bookHopeForm";
	}
	
	@GetMapping("book/bookHopeRegist") //도서 등록 url
	public String bookHopeRegist(Model model) {
		String sessionId = (String) session.getAttribute("id");
		if (sessionId == null || sessionId.trim().isEmpty()) {
			return "redirect:../login";
		}
		model.addAttribute("menu", "board2");
			return "bookHope/bookHopeRegist";
	}
	
	@RequestMapping("book/bookHopeRegistProc")
	public String bookHopeRegistProc(BookHopeDTO bookHopeDTO, RedirectAttributes ra) {

		String path = service.bookHopeRegistProc(bookHopeDTO);
		return path;
	}
	
	@GetMapping("book/bookHopeContent")
	public String bookHopeContent(String no,Model model) {
		
		BookHopeDTO board = service.bookHopeContent(no, model);
		if(board == null) {
			return "redirect:bookHopeForm";
		}

		model.addAttribute("menu", "board2");
		model.addAttribute("board", board);
		return "bookHope/bookHopeContent";
	}
	
	@GetMapping("book/bookHopeDeleteProc")
	public String bookHopeDeleteProc(String no) {
		String sessionId = (String) session.getAttribute("id");
		if (sessionId == null || sessionId.trim().isEmpty()) {
			return "redirect:../login";
		}
		
		service.bookHopeDeleteProc(no);
		return "redirect:bookHopeForm";	
	}

	
}
