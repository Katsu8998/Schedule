package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import dao.ScheduleDAO;
import model.Parameter;
import model.ScheduleBeans;
import model.ScheduleLogic;
import model.User;

public class ScheduleTest {
	@InjectMocks
	private Schedule scheudle = new Schedule();

	@Mock
	private ScheduleDAO dao = new ScheduleDAO();

	@Mock
	private ScheduleLogic SL = new ScheduleLogic();

	@Mock
	private ScheduleBeans s = new ScheduleBeans();

	@Mock
	private ScheduleBeans SD = new ScheduleBeans();

	@Mock
	private User user = new User();

	@Mock
	private Parameter parameter = new Parameter();

	@Mock
	private ScheduleLogic logic = new ScheduleLogic();

	@Mock
	private ScheduleBeans SB = new ScheduleBeans();

	@Mock
	private ScheduleBeans Sd = new ScheduleBeans();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@ParameterizedTest
	@ValueSource(strings = {"view", "edit", "delete", "input"})
	public void doGet01_Schedule(String result) throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);


		request.setParameter("schedule", result);
		request.setParameter("day", "1");
		request.setParameter("month", "1");
		request.setParameter("year", "2022");
		request.setParameter("s_id", "0x111");

		String day = "1";
		String month = "1";
		String year = "2022";

		List<ScheduleBeans>SBs = new ArrayList<>();
		int id = 1;
		Date date = Date.valueOf("2022-01-01");
		String start = "8:00";
		String end = "17:00";
		String title = "study";
		String detail = "java";
		String S_id = "0x111";
		SB = new ScheduleBeans(id, date, start, end, title, detail, S_id);
		SBs.add(SB);
		when(dao.findAll()).thenReturn(SBs);

		List<ScheduleBeans>SBs2 = new ArrayList<>();
		ScheduleBeans SB2 = new ScheduleBeans(id, date, start, end, title, detail, S_id);
		SBs2.add(SB2);
		when(SL.dayCheck(SBs, day, month, year)).thenReturn(SBs2);



		s = new ScheduleBeans(id, date, start, end, title, detail, S_id);
		when(SL.selectS(S_id)).thenReturn(s);


		SD = new ScheduleBeans(id, date, start, end, title, detail, S_id);
		when(SL.selectS(S_id)).thenReturn(SD);

		String forwardPath = "";

		scheudle.doGet(request, response);

		switch(result) {
		case "view":
			forwardPath = "/WEB-INF/jsp/view.jsp";

		//	assertNotNull(app.getAttribute("SB"));
			break;

		case "edit":
			forwardPath = "/WEB-INF/jsp/edit.jsp";

			assertNotNull(session.getAttribute("SB_e"));
			break;

		case "delete":
			forwardPath = "/WEB-INF/jsp/deleteCheck.jsp";

			assertNotNull(session.getAttribute("SB_d"));

			break;
		case "input":
			forwardPath = "/WEB-INF/jsp/input.jsp";
			break;
		}

		assertEquals(forwardPath, response.getForwardedUrl());

	}

	@ParameterizedTest
	@ValueSource(strings = {"s_input", "s_edit", "s_delete"})
	public void doPost01_test(String result) throws ServletException, IOException{
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);
		String forwardPath = "/WEB-INF/jsp/in_fail.jsp";
		request.setParameter("action", result);
		request.getCharacterEncoding();

		int id = 1;
		Date date = Date.valueOf("2022-01-01");
		String start = "8:00";
		String end = "17:00";
		String title = "study";
		String detail = "java";
		String S_id = "0x111";
		SB = new ScheduleBeans(id, date, start, end, title, detail, S_id);

		String pass = "1234";
		String name = "Alice";
		user = new User (pass, name, id);
		session.setAttribute("user", user);
		when(parameter.execute(request, user)).thenReturn(SB);
		when(logic.execute01(SB)).thenReturn(true);
		when(logic.setS_Id(SB)).thenReturn(SB);


		int id2 = 2;
		Date date2 = Date.valueOf("2022-01-01");
		String start2 = "8:00";
		String end2 = "17:00";
		String title2 = "study";
		String detail2 = "java";
		String s_id2 = "xx0222";
		s = new ScheduleBeans(id2, date2, start2, end2, title2, detail2, s_id2);
		when(parameter.execute(request, s)).thenReturn(s);
		when(logic.execute03(s)).thenReturn(true);
		session.setAttribute("SB_e", s);


		int id3 = 3;
		Date date3 = Date.valueOf("2022-01-01");
		String start3 = "8:00";
		String end3 = "17:00";
		String title3 = "study";
		String detail3 = "java";
		String S_id3 = "0x333";
		Sd = new ScheduleBeans(id3, date3, start3, end3, title3, detail3, S_id3);
		when(logic.delete(Sd)).thenReturn(true);
		session.setAttribute("SB_d", Sd);

		scheudle.doPost(request, response);

		switch(result) {
		case "s_input":
			forwardPath = "/WEB-INF/jsp/input_success.jsp";
			assertNotNull("SB");
		break;

		case "s_edit":

			forwardPath = "/WEB-INF/jsp/edit_success.jsp";

			break;

		case "s_delete":

			forwardPath = "/WEB-INF/jsp/deleteSucess.jsp";
		break;
		}

		assertEquals(forwardPath, response.getForwardedUrl());

	}

}
