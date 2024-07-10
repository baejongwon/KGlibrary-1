package com.kg.library_1.donate;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DonateMapper {

	List<DonateDTO> donateForm(@Param("begin")int begin, @Param("end") int end,
			@Param("select")String select, @Param("search")String search);

	int donateWriteProc(DonateDTO dto);

	int totalCount(@Param("select")String select,@Param("search")String search);

	DonateDTO donateContent(String no);

	int donateDeleteProc(String no);

}