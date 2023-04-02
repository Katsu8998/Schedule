package model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalendarLogicTest {
	@InjectMocks
	private CalendarLogic CL = new CalendarLogic();

	@Mock
	private CalendarBeans CB;

	@Mock
	private Calendar c;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 引数なし
	 */


	@Test
	public void CalendarLogicTest1() {
		CB =  CL.execute();

		c = Calendar.getInstance();
		int a =  c.get(Calendar.MONTH)+1;
		assertEquals(CB.getMonth(), a);
		assertEquals(CB.getYear(), c.get(Calendar.YEAR));

		String[][] ans = CB.getDate();
		String result = ans [1][0];
		String day  = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		assertEquals(day, result);

	}

	/**
	 * 引数有り
	 * year 1999年
	 * month 1月
	 */

	@Test
	public void CalendarLogicTest02() {
		int year = 1999;
		int month = 0;

		CB = CL.execute(year, month);
		assertEquals(CB.getMonth(), 1);
		assertEquals(CB.getYear(), 1999);
		String[][] ans = CB.getDate();
		String result = ans [1][0];
		assertEquals(result, "3");
	}

	@Test
	public void CalendarLogicTest3() {
		c =  Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);

		CB = CL.execute2();

		assertEquals(CB.getYear(), year);
		assertEquals(CB.getMonth(), month);


	}



}
