/**
 * 
 */
package ramp.sample.springjdbctemplate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import ramp.sample.springjdbctemplate.model.App;

/**
 * @author Rama Palaniappan
 * @since Jan 8, 2014
 */
@Repository("appDAO")
public class AppDAOImpl extends BaseDAO implements AppDAO {

	public static final String SELECT_APP_QUERY = "select app_id, name, package_name from app where package_name=?";

	public List<App> findApp(String packageName) {
		ParameterizedRowMapper<App> mapper = new ParameterizedRowMapper<App>() {

			public App mapRow(ResultSet rs, int rowNum) throws SQLException {
				App app = new App();
				app.setAppId(rs.getInt("app_id"));
				app.setName(rs.getString("name"));
				app.setPackageName(rs.getString("package_name"));
				return app;
			}
		};
		List<App> apps = jdbcTemplate.query(SELECT_APP_QUERY, mapper,
				new Object[] { packageName });
		return apps;
	}

}
