package com.kg.library_1.donate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kg.library_1.PageService;


@Service
public class DonateService {
	@Autowired private DonateMapper mapper;	
	
	public void donateForm(String cp, Model model, String search, String select) {
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(cp);
		}catch(Exception e) {
			currentPage = 1;
		}
		
		int pageBlock = 10;
		int end = pageBlock * currentPage;
		int begin = end - pageBlock + 1;
		
		List<DonateDTO> board = mapper.donateForm(begin, end, select, search);
		int totalCount = mapper.totalCount(select, search);
		if(totalCount == 0) {
			return;
		}
		
		String url = "donateForm?currentPage=";
		String result = PageService.printPage(url, totalCount, pageBlock, currentPage);
		
		model.addAttribute("count", totalCount);		
		model.addAttribute("donate", board);
		model.addAttribute("result", result);
	}

	public int donateWriteProc(DonateDTO dto) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dto.setReg_date(sdf.format(new Date()));
		int res = mapper.donateWriteProc(dto);
		return res;
	}

	public DonateDTO donateContent(String no) {
		DonateDTO board = mapper.donateContent(no);
		return board;
	}
	public int donateDeleteProc(String no) {
		int res = mapper.donateDeleteProc(no);
		return  res;
		
	}
	
}
