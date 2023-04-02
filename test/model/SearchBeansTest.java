package model;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class SearchBeansTest {
	@Test
	public void SearchBeansTest01() {
		int user_id=1;
		String name ="Alice";
		Date sqlDate= Date.valueOf("2023-03-02");
		String start = "16:00";
		String end = "21;00";
		String title = "School";
		String detail = "Python";
		String schedule_id = "0x1212";

		SearchBeans SrB = new SearchBeans(user_id, name, sqlDate, start, end, title, detail);
		assertEquals(user_id, SrB.getUser_id());
		assertEquals(name, SrB.getName());

		assertEquals(sqlDate, SrB.getDate());
		assertEquals(start, SrB.getStart());
		assertEquals(end, SrB.getEnd());
		assertEquals(title, SrB.getTitle());
		assertEquals(detail, SrB.getDetail());

	}




}