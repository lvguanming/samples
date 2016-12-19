package ramp.sample.swagger.swg;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;

/**
 * 
 * @author Rama Palaniappan
 * @since Aug 31, 2013
 */
@Component
@Path("/swagger-docs")
@Api("/swagger-docs")
@Produces("application/json")
public class SwaggerApiListingResourceJSON extends ApiListingResourceJSON {
}