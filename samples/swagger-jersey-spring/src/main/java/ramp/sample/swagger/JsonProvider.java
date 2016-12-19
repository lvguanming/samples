package ramp.sample.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author Rama Palaniappan
 * @since Aug 31, 2013
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonProvider extends com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider {
    private static ObjectMapper commonMapper = null;

    public JsonProvider() {
        if (commonMapper == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            commonMapper = mapper;
        }
        super.setMapper(commonMapper);
    }


}