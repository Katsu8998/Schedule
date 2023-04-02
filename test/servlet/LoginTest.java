package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import dao.UserDAO;
import model.CalendarBeans;
import model.CalendarLogic;
import model.User;
import model.UserLogic;

public class LoginTest {
	@InjectMocks
	private Login login = new Login();

	@Mock
	private CalendarBeans beans = new CalendarBeans();

	@Mock
	private CalendarBeans now = new CalendarBeans();

	@Mock
	private UserDAO uDao = new UserDAO();

	@Mock
	private User UN = new User();

	@Mock
	private UserLogic userLogic = new UserLogic();

	@Mock
	private CalendarLogic beansLogic = new CalendarLogic();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doGet01_LoginTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);
		String action = "";

		request.setAttribute("action", null);
		UN = new User("12345", "Alice", 1);
		request.setAttribute("user", UN);
		String forwardPath = "/WEB-INF/jsp/login.jsp";

		login.doGet(request, response);
		assertEquals(forwardPath, response.getForwardedUrl());
	}

	@ParameterizedTest
	@ValueSource(strings= {"logout", "change", "register", "research"})
	public void doGet02_LoginTest(String result) throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);


		request.setParameter("action", result);
		UN = new User("12345", "Alice", 1);
		session.setAttribute("user", UN);
		String action = result;
		String forwardPath = "/WEB-INF/jsp/login.jsp";
		login.doGet(request, response);

		if(action.equals("logout")) {
			assertNull(session.getAttribute("user"));
		}else if (action.equals("change")) {
			forwardPath = "/WEB-INF/jsp/change.jsp";
		} else if (action.equals("register")) {
			forwardPath = "/WEB-INF/jsp/register.jsp";
		} else if(action.equals("research")) {
			forwardPath = "/WEB-INF/jsp/research.jsp";

		}

		assertEquals(forwardPath, response.getForwardedUrl());
	}

	@Test
	public void doPost01_loginTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);

		//now.setYear(2023);
		//now.setMonth(12);
		beans.setYear(2023);
		beans.setMonth(1);
		when(beansLogic.execute2()).thenReturn(beans);
		when(beansLogic.execute(beans.getYear(), beans.getMonth())).thenReturn(now);

		request.setParameter("id", "1");
		request.setParameter("password", "1234");


		List<User>users = new ArrayList<>();
		UN = new User("1234", "Alice", 1);
		users.add(UN);
		int id = UN.getId();
		String password = UN.getPassword();
		when(uDao.findAll()).thenReturn(users);
		when(userLogic.selectUser(users, id, password)).thenReturn(UN);

		login.doPost(request, response);
		assertNotNull(session.getAttribute("user"));
		assertNotNull(session.getAttribute("now"));
		assertEquals("/WEB-INF/jsp/calendar.jsp", response.getForwardedUrl());

	}

	@Test
	public void doPost02_loginTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);

		beans.setYear(2023);
		beans.setMonth(1);
		when(beansLogic.execute2()).thenReturn(beans);
		when(beansLogic.execute(beans.getYear(), beans.getMonth())).thenReturn(now);

		request.setParameter("id", "1");
		request.setParameter("password", "1234");


		List<User>users = new ArrayList<>();
		UN = new User("12345", "Alice", 1);
		users.add(UN);
		int id = UN.getId();
		String password = UN.getPassword();
		when(uDao.findAll()).thenReturn(users);
		when(userLogic.selectUser(users, id, password)).thenReturn(UN);

		login.doPost(request, response);
		assertEquals("/WEB-INF/jsp/loginFail.jsp", response.getForwardedUrl());

	}

}
