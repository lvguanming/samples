/**
 * 
 */
package ramp.sample.fasterxml;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Rama Palaniappan
 * @since Apr 9, 2014
 */
public class JsonSerializer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"Ram\", \"age\":100}";
		User user = mapper.readValue(jsonString, User.class);
		System.out.println("Name: " + user.getName());
		System.out.println("Age: " + user.getAge());
	}

}
