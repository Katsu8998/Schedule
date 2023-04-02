package servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import model.SearchBeans;
import model.SearchLogic;
import model.User;

public class SearchTest {
	@InjectMocks
	private Search Sr = new Search();

	@Mock
	private SearchLogic logic = new SearchLogic();

	@Mock
	private User user = new User();

	@BeforeEach
	void setUp () throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doPost01_test() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);

		List<SearchBeans>SrBs = new ArrayList<>();
		request.setParameter("id", "1");
		int id = 1;
		String name = "Alice";
		Date date = Date.valueOf("2022-01-01");
		String start = "8:00";
		String end = "17:00";
		String title = "study";
		String detail = "java";
		SearchBeans SrB = new SearchBeans(id, name, date, start, end, title, detail);
		SrBs.add(SrB);
		when(logic.execute(id)).thenReturn(SrBs);
		Sr.doPost(request, response);

		assertNotNull(request.getAttribute("research"));
		assertEquals("/WEB-INF/jsp/searchResult.jsp", response.getForwardedUrl());

	}


	@Test
	public void doPost02_test() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);

		List<SearchBeans>SrBs = new ArrayList<>();
		request.setParameter("id", "1");
		int id = 1;
		user = new User ("1234", "Alice", id);
		when(logic.execute02(id)).thenReturn(user);
		Sr.doPost(request, response);

		assertNotNull(request.getAttribute("user"));
		assertEquals("/WEB-INF/jsp/searchResult.jsp", response.getForwardedUrl());

	}
}
