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

import dao.SearchDAO;

public class SearchLogicTest {
	@InjectMocks
	private SearchLogic SrL = new SearchLogic();

	@Mock
	private SearchDAO dao = new SearchDAO();

	@Mock
	private User user = new User ();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void execute_SearchLogicTest() {
		int id = 1;
		Date date = Date.valueOf("2023-03-31");
		SearchBeans SrB = new SearchBeans(1, "Alice",
		date, "12:00", "18:00", "study", "python");
		List<SearchBeans>Sr = new ArrayList<>();
		Sr.add(SrB);
		when(dao.find(id)).thenReturn(Sr);
		assertEquals("Alice", SrL.execute(id).get(0).getName());

	}

	@Test
	public void execute02_SearchLogicTest02() {
		int id = 1;
		user = new User("12345", "Alice", 1);
		when(dao.finds(id)).thenReturn(user);
		user = SrL.execute02(id);
		assertEquals("Alice", user.getName());

	}

}
