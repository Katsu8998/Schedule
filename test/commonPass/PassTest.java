package commonPass;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class PassTest {

	@InjectMocks
	private Pass pass = new Pass();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void PASSWORD_OK_test() {
	assertEquals(0, pass.PASSWORD_OK);
	}

	@Test
	public void PASSWORD_NG_FACTORY_test() {
	assertEquals(1, pass.PASSWORD_NG_FACTORY);
	}

	@Test
	public void PASSWORD_NG_UNMACH_test() {
		assertEquals(2, pass.PASSWORD_NG_UNMACH);
	}

	@Test
	public void PASSWORD_NG_UPDATE_test() {
		assertEquals(3, pass.PASSWORD_NG_UPDATE);
	}



}
