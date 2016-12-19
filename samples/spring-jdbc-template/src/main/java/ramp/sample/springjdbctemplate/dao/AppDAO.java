package ramp.sample.springjdbctemplate.dao;

import java.util.List;

import ramp.sample.springjdbctemplate.model.App;

public interface AppDAO {
	public List<App> findApp(String packageName);
}
