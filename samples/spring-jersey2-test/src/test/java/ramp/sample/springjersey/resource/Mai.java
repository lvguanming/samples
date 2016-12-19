package ramp.sample.springjersey.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ramp.sample.springjersey.resource.api.UsersResource;
import ramp.sample.springjersey.types.Users;

public class Mai {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UsersResource usersResource = context.getBean(UsersResource.class);
		Users users = usersResource.getAll();
		System.out.println(users);
	}

}
