package br.ufpr.qrcdoor.controller;

import java.sql.Connection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ReportController {

	@Autowired
	JdbcTemplate jdbc;
	
	private Connection conn = DataSourceUtils.getConnection(jdbc.getDataSource());
	
	private final byte[] processReport(Map<String, Object> parameters) throws Exception {
		final Report report = new Report();
		byte[] data = null;
		return null;
	}
	
	private class Report {
		
		private byte[] data;

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}
		
	}

}
