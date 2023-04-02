package testUtil;

import java.lang.reflect.Field;

public class TestUtil {

	public static void setPrivateField(Object target_object, String field_name, Object value) throws Exception {
		Class<? extends Object> c = target_object.getClass();
		Field fld = c.getDeclaredField(field_name);
		fld.setAccessible(true);
		fld.set(target_object, value);
	}

}
