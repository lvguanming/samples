package ramp.sample.springjdbctemplate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDAO {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
