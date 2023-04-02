package model;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

public class ParameterTest {

	@InjectMocks
	private Parameter p = new Parameter();

	@Mock
	private User user = new User();

	@Mock
	private ScheduleBeans SB = new ScheduleBeans();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void execute_ParamterTest01() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("day", "2023-03-30");
		request.setParameter("start", "16:00");
		request.setParameter("finish", "21:00");
		request.setParameter("title", "Study");
		request.setParameter("detail", "Python");
		user = new User("1234", "Alice", 1);
		SB = p.execute(request, user);
		assertEquals(1, SB.getId());
		Date sqlDate = Date.valueOf("2023-03-30");
		assertEquals(sqlDate, SB.getDate());
		assertEquals("16:00", SB.getStart());
		assertEquals("21:00", SB.getEnd());
		assertEquals("Study", SB.getTitle());
		assertEquals("Python", SB.getDetail());

	}


	@Test
	public void execute_ParameterTest02() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("day", "2023-03-30");
		request.setParameter("start", "16:00");
		request.setParameter("finish", "21:00");
		request.setParameter("title", "Study");
		request.setParameter("detail", "Python");

		Date sqlDate = Date.valueOf("2023-03-30");

		SB = new ScheduleBeans()
;		SB = p.execute(request, SB);

		assertEquals(sqlDate, SB.getDate());
		assertEquals("16:00", SB.getStart());
		assertEquals("21:00", SB.getEnd());
		assertEquals("Study", SB.getTitle());
		assertEquals("Python", SB.getDetail());
	}

	@Test
	public void register_ParameterTest03() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("id", "1");
		request.setParameter("name", "Alice");
		request.setParameter("password", "1234");
		user = p.register(request);
		assertEquals(1, user.getId());
		assertEquals("Alice", user.getName());
		assertEquals("1234", user.getPassword());

	}


}
