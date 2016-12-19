package ramp.sample.springjdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ramp.sample.springjdbctemplate.dao.AppDAO;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-beans.xml");

		AppDAO appDAO = (AppDAO) context.getBean("appDAO");
		String packageName = null;
		if (args != null && args.length > 0) {
			packageName = args[0]; 
		} else {
			packageName ="com.intuit.random.acra";
		}
		
		System.out.println(appDAO.findApp(packageName));
	}

}
