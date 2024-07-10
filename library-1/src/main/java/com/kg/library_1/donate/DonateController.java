package com.kg.library_1.donate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class DonateController {
@Autowired private DonateService service;
@Autowired private HttpSession session;	
	
	// 도서 기증 목록보기--------------------------------
	@RequestMapping("book/donateForm")
	public String donateForm(String search, Model model, 
			@RequestParam(value="currentPage", required = false)String cp) {
		String select = "select";
		if(search == null || search.trim().isEmpty()) {
			search = "id";
			select = "all";
		}
		
		service.donateForm(cp, model, search, select);
		model.addAttribute("menu", "donateForm");
		return "/donate/donateForm";
	}
	
	// 도서 기증--------------------------------
	@GetMapping("book/donateWrite")
	public String donateWrite(Model model) {	
		String msg = "";
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null) {
			msg="로그인 후 가능합니다.";
			return "redirect:../login";
		}
		model.addAttribute("menu", "donateWrite");
		return "donate/donateWrite";
	}
	
	// 도서 기증 신청하기--------------------------------
	@PostMapping("book/donateWriteProc")
	public String donateWriteProc(DonateDTO donate, Model model) {
		String msg = "";
		if(donate.getContent()==""||donate.getContent().trim().isEmpty()) {
			msg="내용을 입력하세요.";
			model.addAttribute("msg",msg);
			model.addAttribute("donate", donate);
			return "donate/donateWrite";
		}
		
		int res = service.donateWriteProc(donate);
		return "redirect:donateForm";
	}
	// 도서 기증 상세보기 --------------------------------
	@RequestMapping("book/donateContent")
	public String donateContent(String no, Model model, RedirectAttributes ra) {
		String msg = "";
		DonateDTO board = service.donateContent(no);
		String sessionId = (String)session.getAttribute("id");
		if(board == null) {
			return "redirect:donateForm";
		}
		if(sessionId == null) {
			return "redirect:../login";
		}
		if(!sessionId.equals(board.getId())) {
			msg="작성자만 확인가능합니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:donateForm";
		}
		
		model.addAttribute("menu", "donateForm");
		model.addAttribute("board", board);
		return "donate/donateContent";
	}
	// 도서 기증 삭제실행 --------------------------------
	@RequestMapping("book/donateDeleteProc")
	public String donateDeleteProc(String no, Model model) {
		String msg="게시글 삭제가 실패되었습니다.";
		
		int res = service.donateDeleteProc(no);
		
		if(res>0) {
			msg = "게시글이 삭제되었습니다.";
			model.addAttribute(msg);
			return "redirect:donateForm";
		}
		model.addAttribute(msg);
		return "redirect:donateForm";
	}
	
	
	// 도서 기증 안내서   --------------------------------
	@RequestMapping("book/donateguide")
	public String donateguide(Model model) {
		model.addAttribute("menu", "donateguide");
		return "donate/donateguide";
	}
	
	// 도서 기증 사이드바 --------------------------------
	@RequestMapping("book/donateheader")
	public String donateheader() {
		return "donate/donateheader";
	}
	
	@RequestMapping("book/donatefooter")
	public String donatefooter() {
		return "donate/donatefooter";
	}
}