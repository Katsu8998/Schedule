package model;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

public class ScheduleBeansTest {
/*
 * 引数6つ
 */
	@Test
	public void ScheduleBeansTest01() {
		int id=1;
		Date sqlDate= Date.valueOf("2023-03-02");
		String start = "16:00";
		String end = "21;00";
		String title = "School";
		String detail = "Python";

		ScheduleBeans SB = new ScheduleBeans(id, sqlDate, start, end, title, detail);
		assertEquals(id, SB.getId());
		assertEquals(sqlDate, SB.getDate());
		assertEquals(start, SB.getStart());
		assertEquals(end, SB.getEnd());
		assertEquals(title, SB.getTitle());
		assertEquals(detail, SB.getDetail());

	}

	@Test
	public void ScheduleBeansTest02() {
		int id=1;
		Date sqlDate= Date.valueOf("2023-03-02");
		String start = "16:00";
		String end = "21;00";
		String title = "School";
		String detail = "Python";
		String schedule_id = "0x23";

		ScheduleBeans SB = new ScheduleBeans(id, sqlDate, start, end, title, detail, schedule_id);
		assertEquals(id, SB.getId());
		assertEquals(sqlDate, SB.getDate());
		assertEquals(start, SB.getStart());
		assertEquals(end, SB.getEnd());
		assertEquals(title, SB.getTitle());
		assertEquals(detail, SB.getDetail());
		assertEquals(schedule_id, SB.getSchedule_id());
	}

}