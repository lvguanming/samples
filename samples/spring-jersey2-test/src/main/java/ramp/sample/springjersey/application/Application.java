/**
 * 
 */
package ramp.sample.springjersey.application;

import java.util.Set;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.reflections.Reflections;

import ramp.sample.springjersey.resource.impl.StudentsResourceImpl;
import ramp.sample.springjersey.resource.impl.UsersResourceImpl;

/**
 * @author Rama Palaniappan
 * @since Apr 16, 2014
 */
public class Application extends ResourceConfig {
	public Application() {
//		packages("ramp.sample.springjersey.resource");
		register(RequestContextFilter.class);
//		register(UsersResourceImpl.class);
		registerClasses(UsersResourceImpl.class, StudentsResourceImpl.class);
//		registerClasses(getResourceClasses());
	}
//    public Set<Class<?>> registerClasses() { 
//            Set<Class<?>> implementations = new HashSet<Class<?>>(); 
//
//            /* explicitly add implementation class */ 
////            implementations.add(ramp.sample.springjersey.resource.impl.UsersResourceImpl.class); 
////            implementations.add(ramp.sample.springjersey.resource.impl.StudentsResourceImpl.class); 
//            implementations.add(ramp.sample.springjersey.resource.api.UsersResource.class); 
//            implementations.add(ramp.sample.springjersey.resource.api.StudentResource.class); 
//
//            return implementations; 
//
//    } 
	
	private Set<Class<?>> getResourceClasses() {
		Reflections reflections = new Reflections("ramp.sample.springjersey.resource");

		 Set<Class<? extends Object>> allClasses = 
		     reflections.getSubTypesOf(Object.class);
		 System.out.println(allClasses);
		 return allClasses;
	}
	
	public static void main(String[] args) {
		new Application().getResourceClasses();
	}
}
