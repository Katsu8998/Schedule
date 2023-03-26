package model;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class CalendarBeansTest {
	/**
	 * CalendarBeans 引数なし
	 *
	 */
	@Test
	public void CalendarBeansTest() {
		CalendarBeans CB = new CalendarBeans();
		CB.setYear(1);
		CB.setMonth(1);

		String[][] date = new String[1][1];
		date[0][0] = "1";
		CB.setDate(date);

		assertEquals(CB.getYear(), 1);
		assertEquals(CB.getMonth(), 1);


		for (String[] r : CB.getDate()) {
			for (String result : r) {
				assertEquals("1", result);
			}
		}
	}

}
