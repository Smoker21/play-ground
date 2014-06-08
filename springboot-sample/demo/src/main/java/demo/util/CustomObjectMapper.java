package demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {
	public CustomObjectMapper() {
		super();
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

	}
}
