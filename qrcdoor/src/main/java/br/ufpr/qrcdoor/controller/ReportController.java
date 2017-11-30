package br.ufpr.qrcdoor.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ReportController {

	@Autowired
	JdbcTemplate jdbc;
	
	private Connection conn = DataSourceUtils.getConnection(jdbc.getDataSource());

}
