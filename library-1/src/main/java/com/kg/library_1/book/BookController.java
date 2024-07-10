package com.kg.library_1.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

	@Autowired
	private BookService service;
	@Autowired 
	private HttpSession session;
	
	@RequestMapping("book/bookForm") // 도서 검색 url
	public String bookForm(String search,Model model,
			@RequestParam(value="currentPage", required = false)String cp, String select, 
			@RequestParam(name = "parameterName", required = false) String Id) {
//		if(Id != null && !Id.trim().isEmpty())	
//		session.setAttribute("id", Id);
//		
//		session.setAttribute("id", "admin");
//		
//		String sessionId = (String) session.getAttribute("id");
//		System.out.println("bookform id 확인." + sessionId);
			
		//검색 시 select 값으로 검색(search) 확인.
			if(search == null || search.trim().isEmpty()) {
				search = ""; select = "title"; 
			}

			model.addAttribute("menu", "board");
			service.bookForm(cp, model, search, select); //DB 검색 및 정렬

		return "book/bookForm";
	}
	
	@GetMapping("book/bookRegist") //도서 등록 url
	public String bookRegist(Model model) {
	
		String sessionId = (String) session.getAttribute("id");
		if (sessionId==null || sessionId.trim().isEmpty()) {
			System.out.println(sessionId);
			return "redirect:bookForm";
		}
		if(!sessionId.equals("admin")) {
			return "redirect:bookForm";
		}
	//관리자가 아니면 등록 불가, 회원이 url을 직접적으로 치고 들어올 경우 반환하기 위하여 설정. 회원가입 확인 되면 주석 풀것
		model.addAttribute("menu", "board");
		return "book/bookRegist";
	}
	
	@RequestMapping("book/bookRegistProc")
	public String bookRegistProc(MultipartHttpServletRequest multi, RedirectAttributes ra) {

		String path = service.bookRegistProc(multi);
		return path;
	}
	
	@GetMapping("book/bookContent")
	public String bookContent(String no,Model model) {
		
		BookDTO board = service.bookContent(no, model);
		if(board == null) {
			return "redirect:bookForm";
		}

		model.addAttribute("menu", "board");
		model.addAttribute("board", board);
		return "book/bookContent";
	}
	
	@GetMapping("book/rentalProc")
	public String rentalProc(String no) {
			
		String sessionId = (String) session.getAttribute("id");
		if (sessionId == null || sessionId.trim().isEmpty()) 
		return "redirect:bookContent";
		
		service.rentalProc(no, sessionId);
		return "redirect:bookContent";	
	}
	
	@GetMapping("book/returnProc")
	public String returnProc(String no) {
			
		String sessionId = (String) session.getAttribute("id");
		if (sessionId == null || sessionId.trim().isEmpty()) 
		return "redirect:bookContent";
		
		service.returnProc(no);
		return "redirect:bookContent";	
	}
	
	@GetMapping("book/bookDeleteProc")
	public String bookDeleteProc(String no) {
		String sessionId = (String) session.getAttribute("id");
		if (sessionId == null || !sessionId.equals("admin") || sessionId.trim().isEmpty()) {
			return "redirect:bookForm";
		}
		
		service.bookDeleteProc(no);
		return "redirect:bookForm";	
	}

	@GetMapping("book/apiBookRegist") //도서 등록 url
	public String apiBookRegist(Model model) {
		
		String sessionId = (String)session.getAttribute("id");
		if (sessionId == null || !sessionId.equals("admin") || sessionId.trim().isEmpty()) {
			System.out.println(sessionId);
			return "redirect:../login";
		}
	//관리자가 아니면 등록 불가, 회원이 url을 직접적으로 치고 들어올 경우 반환하기 위하여 설정. 회원가입 확인 되면 주석 풀것
		model.addAttribute("menu", "board3");
		return "book/apiBookRegist";
	}
	
	@PostMapping("book/apiRegistProc") //도서 등록
	public String apiRegistProc(String pageNum, String select, String search, Model model,
			@RequestParam(name = "parameterName", required = false) String Id) {
		if(Id != null && !Id.trim().isEmpty())	
		session.setAttribute("id", Id);
		String sessionId = (String) session.getAttribute("id");
		System.out.println("api id 확인." + sessionId);

		if(search == null || search.trim().isEmpty()) {
			return "redirect:apiBookRegist";
		}
		if(select == null || select.trim().isEmpty()) {
			return "redirect:apiBookRegist";
		}

		service.apiRegistProc(pageNum, select, search, model);
		System.out.println("등록");

		return "book/apiAlert";
	}
	
	@GetMapping("book/hit_book")
	 @ResponseBody
	 public ResponseEntity<ArrayList<BookDTO>> hitBook()  {
		   try {
			   System.err.println("hit book 요청 연결 성공");
	            ArrayList<BookDTO> hitbooks = service.hitBook();

	            // 받아온 데이터 출력
	            for (BookDTO b : hitbooks) {
	                System.out.println("No: " + b.getNo());
	                System.out.println("Image: " + b.getImage());
	                System.out.println("Title Info: " + b.getTitle_info());
	                System.out.println("Author Info: " + b.getAuthor_info());
	            }

	            return ResponseEntity.ok(hitbooks); // 정상적인 응답
	        } catch (Exception e) {
	            // 예외 발생 시 출력
	            System.err.println("hit book 요청 연결 실패");
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
	        }
	    }
	 @GetMapping("book/new_Book")
	 @ResponseBody
	    public ResponseEntity<ArrayList<BookDTO>> newBook() {
		   try {
			   System.err.println("new book 요청 연결 성공");
	            ArrayList<BookDTO> newBooks = service.newBook();

	            // 받아온 데이터 출력
	            for (BookDTO b : newBooks) {
	            	System.out.print("\u001B[31m");
	                System.out.println("8087newbook No: " + b.getNo());
	                System.out.println("8087newbook Image: " + b.getImage());
	                System.out.println("8087newbook Title Info: " + b.getTitle_info());
	                System.out.println("8087newbook Author Info: " + b.getAuthor_info());
	                System.out.print("\u001B[0m");
	            }

	            return ResponseEntity.ok(newBooks);
	        } catch (Exception e) {
	            // 예외 발생 시 출력
	            System.err.println("new book 요청 연결 실패");
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
	        }
	    }
	
	 @GetMapping("book/dataStatus")
	 @ResponseBody
	    public ResponseEntity<List<Map<String, Object>>> dataStatus(BookDTO board, Model model) {
		   try {
			   System.err.println("datastatus 요청 연결 성공");
	            List<Map<String, Object>> dataStatus = service.dataStatus(board);
	            // 받아온 데이터 출력
	           
	            // 받아온 데이터 출력 (디버깅 용도로 출력)
	            for (Map<String, Object> data : dataStatus) {
	                System.out.println("8087Data: " + data);
	            }
	            
	            return ResponseEntity.ok(dataStatus);
	        } catch (Exception e) {
	            // 예외 발생 시 출력
	            System.err.println("datastatus요청 연결 실패");
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
	        }
	    }
	
	/////////////////////////////////
	 
	    @PostMapping("book/requestMyBook") //return으로 데이터를 넘김
	    public ResponseEntity<String> requestMyBook(@RequestBody Map<String, String> requestBody) {//외부 서버 데이터 받기
	        // 요청 바디를 받아서 처리하는 로직을 작성
	    	System.out.println("my book 되나?");
	    	String reID = requestBody.get("id");
	    	
	    	if(reID == null || reID.trim().isEmpty()) {
	    		return ResponseEntity.ok("ERROR");
	    	}
	    	
	    	List<BookDTO> requestmybook = service.requestMyBook(reID);
	    	String myBookJson;
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            myBookJson = objectMapper.writeValueAsString(requestmybook);
	            System.out.println(myBookJson.toString());
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	            return ResponseEntity.ok("ERROR");
	        }
	        
	    //	System.out.println("bookdto = " + myBookJson);
	    //    System.out.println("Received request body: " + reID);

	        // 응답 생성 (예: "Request received successfully"라는 메시지를 응답)
	        String responseBody = "Request received successfully";
	        return ResponseEntity.ok(myBookJson);
	    }
	 
	    @PostMapping("book/requestDateExtend") //return으로 데이터를 넘김
	    public ResponseEntity<String> requestDateExtend(@RequestBody Map<String, String> requestBody) {//외부 서버 데이터 받기
	        // 요청 바디를 받아서 처리하는 로직을 작성
	    	String id = requestBody.get("id");
	    	String no = requestBody.get("no");
	    	//no 
	    	service.borrowDateExtend(no,id);

	        // 응답 생성 (예: "Request received successfully"라는 메시지를 응답)
	        String responseBody = "Request received successfully";
	        return ResponseEntity.ok(responseBody);
	    }
	
	    @PostMapping("book/requestreturnProc2") //return으로 데이터를 넘김
	    public ResponseEntity<String> requestreturnProc2(@RequestBody Map<String, String> requestBody) {//외부 서버 데이터 받기
	        // 요청 바디를 받아서 처리하는 로직을 작성
	    	String no = requestBody.get("no");
	    	//no 
	    	service.requestreturnProc2(no);

	        // 응답 생성 (예: "Request received successfully"라는 메시지를 응답)
	        String responseBody = "Request received successfully";
	        return ResponseEntity.ok(responseBody);
	    }
	    
	
	
	//공지사이드바 템플릿
	@RequestMapping("bookheader")
	public String bookheader() {
		return "book/bookheader";
	}
	
	@RequestMapping("bookfooter")
	public String bookfooter() {
		return "book/bookfooter";
	}
}
