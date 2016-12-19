package ramp.sample.swagger.swg;


import org.springframework.beans.factory.annotation.Value;

import com.wordnik.swagger.jaxrs.ConfigReader;

import javax.servlet.ServletConfig;

/**
 * 
 * @author Rama Palaniappan
 * @since Aug 31, 2013
 */
public class SwaggerConfigReader extends ConfigReader {

	@Value("${sample.ramp.swagger.base.url}")
	private String swaggerBaseUrl;

	@Value("${sample.ramp.swagger.api.version}")
	private String apiVersion;

	public SwaggerConfigReader(ServletConfig config) {
	}

	@Override
	public String basePath() {
		return swaggerBaseUrl;
	}

	@Override
	public String swaggerVersion() {
		return com.wordnik.swagger.core.SwaggerSpec.version();
	}

	// set your api version here
	@Override
	public String apiVersion() {
		return apiVersion;
	}

	// if you only want to scan certain model packages, you can include them in
	// a CSV-formatted string
	// like com.myapp.models,com.yourapp.stuff
	// otherwise, return null
	@Override
	public String modelPackages() {
		return "java.lang,ramp.sample.swagger.types";
	}

	// if you have a filter class to handle access to apis, return it as a
	// string
	@Override
	public String apiFilterClassName() {
		return null;
	}
}