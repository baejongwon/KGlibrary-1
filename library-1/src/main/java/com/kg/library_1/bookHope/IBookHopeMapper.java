package com.kg.library_1.bookHope;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IBookHopeMapper {

	List<BookHopeDTO> bookHopeForm(@Param("begin")int begin, @Param("end") int end,
			@Param("select")String select, @Param("search")String search);

	int totalCount(@Param("select")String select,@Param("search")String search);

	void bookHopeDeleteProc(int n);

	BookHopeDTO bookHopeContent(int n);

	void bookHopeRegistProc(BookHopeDTO board);

}
