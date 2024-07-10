
package com.kg.library_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kg.library_1.book.BookService;


@Controller
public class HomeController {

//	@Autowired
//	private BookService Book_Service;
//	
//	@RequestMapping("index")
//	public void index() {}
	
//	@RequestMapping("book/header")
//	public String header() {
//		return "default/header";
//	}	
////	
////	@RequestMapping("main")
////	public String main(Model model) {
////	//	Book_Service.hit_book(model);
////	//	Book_Service.new_book(model);
////		return "default/main";
////	}
////	
//	@RequestMapping("book/footer")
//	public String footer() {
//		return "default/footer";
//	}

}
