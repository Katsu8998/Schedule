package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.ScheduleDAO;
import passwordUtil.PasswordUtil;

public class ScheduleLogicTest {
	@InjectMocks
	private ScheduleLogic SL = new ScheduleLogic();

	@Mock
	private ScheduleDAO dao = new ScheduleDAO();

	@Mock
	private ScheduleBeans SB = new ScheduleBeans();

	@Mock
	private PasswordUtil PU = new PasswordUtil();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void exeute01_ScheduleLogic() {
		Date date = Date.valueOf("2023-03-29");
		ScheduleBeans SB2 = new ScheduleBeans(
				1, date, "16:00", "21:00", "Study", "Java");
		when(dao.create(SB2)).thenReturn(true);
		assertTrue(SL.execute01(SB2));
	}

	@Test
	public void exeute02_ScheduleLogic() {
		List<ScheduleBeans> SB = new ArrayList<ScheduleBeans>();
		when(dao.findAll()).thenReturn(SB);
		assertNotNull(SL.execute02());
	}

	@Test
	public void execute03_ScheduleLogic() {
		Date date = Date.valueOf("2023-03-29");
		ScheduleBeans SB2 = new ScheduleBeans(
				1, date, "16:00", "21:00", "Study", "Java");

		SB2.setEnd("22:00");
		when(dao.edit(SB2)).thenReturn(true);
		assertTrue(SL.execute03(SB2));

	}

	@Test
	public void setS_Id_ScheduleLogic() {
		Date date = Date.valueOf("2023-03-29");
		ScheduleBeans SB2 = new ScheduleBeans(
				1, date, "16:00", "21:00", "Study", "Java");

		when(PU.execute02("123")).thenReturn("12345");
		when(SB.getSchedule_id()).thenReturn("12345");
		assertEquals("12345", SL.setS_Id(SB2).getSchedule_id());

	}

	@Test
	public void selectS_ScheduleLogicTest() {
		Date date = Date.valueOf("2023-03-29");
		SB = new ScheduleBeans(1, date, "16:00", "21:00", "Study", "Java", "12345");
		String s_id = "12345";
		when(dao.finds(s_id)).thenReturn(SB);
		SL.selectS(s_id);
		assertEquals(s_id, SB.getSchedule_id());

	}

	@Test
	public void delete_ScheduleLogicTest() {
		Date date = Date.valueOf("2023-03-29");
		ScheduleBeans SB2 = new ScheduleBeans(1, date, "16:00", "21:00", "Study", "Java", "12345");
		when(dao.deleteFunc(SB2)).thenReturn(true);
		assertTrue(SL.delete(SB2));
	}

	@Test
	public void dayCheck_ScheduleLogicTest() {
		List<ScheduleBeans>SB2 = new ArrayList<>();
		Date date = Date.valueOf("2023-03-29");
		SB = new ScheduleBeans(1, date, "16:00", "21:00", "Study", "Java", "12345");
		SB2.add(SB);
		String year  = "2023";
		String month = "03";
		String day = "29";

		List<ScheduleBeans>SB3 = new ArrayList<>();
		SB3 = SL.dayCheck(SB2, day, month, year);

		assertEquals(date, SB3.get(0).getDate());

	}

}
