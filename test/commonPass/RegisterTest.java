package commonPass;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class RegisterTest {
	@InjectMocks
	private Register r = new Register();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void Register_OK_test() {
		assertEquals(0, r.Register_OK);

	}

	@Test
	public void Register_NG_UPDATE_test() {
		assertEquals(1, r.Register_NG_UPDATE);
	}


	@Test
	public void Register_NG_Factory_test() {
		assertEquals(2, r.Register_NG_Factory);
	}
}
