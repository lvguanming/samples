
package ramp.sample.swagger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import ramp.sample.swagger.types.User;

/** Example resource class hosted at the URI path "/api"
 */
@Path("/myresource")
@Api(value = "/myresource", description = "Test API")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    @ApiOperation(
            value = "Simple get method",
            notes = "Hello World equivalent",
            tags = "get",
            httpMethod = "GET",
            responseClass = "java.lang.String")
    public String getIt() {
        return "Hi there!";
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("users")
    @ApiOperation(
            value = "Get User",
            notes = "Simple jaxb object in http get",
            tags = "get",
            httpMethod = "GET",
            responseClass = "ramp.sample.swagger.types.User")
    public User getUser() {
    	User user = new User();
    	user.setEmailId("test.email.id@i.com");
    	user.setFirstName("fName");
    	user.setLastName("lName");
    	return user;
    }
}