package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

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

import dao.UserDAO;
import model.LoginCheck;
import model.User;

public class ChangePasswordTest {
	@InjectMocks
	private ChangePassword c = new ChangePassword();

	@Mock
	private User user = new User();

	@Mock
	private LoginCheck loginCheck = new LoginCheck();

	@Mock
	private UserDAO dao = new UserDAO();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4})
	public  void doPost01_test(int result) throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);


		request.setParameter("password", "1234");
		request.setParameter("new_pass", "123");
		String pass = request.getParameter("password");
		String new_pass = request.getParameter("new_pass");

		user = new User ("1234", "Alice", 1);
		session.setAttribute("user", user);
		when(loginCheck.execute(user, new_pass, pass)).thenReturn(result);
		when(dao.changePass(user, new_pass)).thenReturn(true);
		String forwardPath = "/WEB-INF/jsp/changePass.jsp";
		c.doPost(request, response);

		switch(result) {
		case 0:
			assertNotNull(request.getAttribute("changePassMsg"));
			forwardPath = "/WEB-INF/jsp/changePassResult.jsp";
			break;

		case 1:
			assertNotNull(request.getAttribute("errorMsg1"));
			break;

		case 2:
			assertNotNull(request.getAttribute("errorMsg1"));
			break;

		case 3:
			assertNotNull(request.getAttribute("errorMsg2"));
			break;


		default:
			assertNotNull(request.getAttribute("errorMsg1"));
			break;
		}
		assertEquals(forwardPath, response.getForwardedUrl());

	}

}
