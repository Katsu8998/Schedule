package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import model.CalendarBeans;
import model.CalendarLogic;

public class CalendarTest {
	@InjectMocks
	private Calendar c = new Calendar();

	@Mock
	private CalendarLogic calendarLogic = new CalendarLogic();

	@Mock
	private CalendarBeans beans = new CalendarBeans();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@ParameterizedTest
	@CsvSource({
			"12",
			"0",
			"13"
	})
	public void doGest_test01(String month) throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();

		request.setParameter("year", "2023");
		request.setParameter("month", month);

		c.doGet(request, response);

		assertEquals("/WEB-INF/jsp/calendar.jsp", response.getForwardedUrl());

	}

	@Test
	public void doGest_test02() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();

		request.setParameter("year", "2023");
		request.setParameter("month", "1");

		int year = 2023;
		int month = 1;

		when(calendarLogic.execute(year,month)).thenReturn(beans);
		session.setAttribute("now", beans);

		c.doGet(request, response);
		CalendarBeans b = (CalendarBeans) session.getAttribute("now");
		assertNotNull(b);

	}

}
