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

import model.Parameter;
import model.User;
import model.UserLogic;

public class User_RTest {
	@InjectMocks
	private User_R UR = new User_R();

	@Mock
	private Parameter p = new Parameter();

	@Mock
	private User user = new User();

	@Mock
	private UserLogic logic = new UserLogic();

	@BeforeEach
	void setUp () throws Exception{
		MockitoAnnotations.initMocks(this);

	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2})
	public void doPost01_test(int result) throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);

		user = new User("1234", "Alice", 1);
		when(p.register(request)).thenReturn(user);
		when(logic.register(user)).thenReturn(result);
		String forwardPath = "/WEB-INF/jsp/register_fail.jsp";

		UR.doPost(request, response);
		switch(result) {
		case 0:
			assertNotNull(session.getAttribute("user"));
			assertEquals("登録完了しました", request.getAttribute("RegisterOK"));
			forwardPath = "/WEB-INF/jsp/register_success.jsp";

			break;


		case 1:
			assertEquals("その他のIDを入力してください", request.getAttribute("errorMsg1"));
			break;

		case 2:
			assertEquals("不明のエラーが発生しました", request.getAttribute("errorMsg1"));
			break;
		}

		assertEquals(forwardPath, response.getForwardedUrl());

	}

}
