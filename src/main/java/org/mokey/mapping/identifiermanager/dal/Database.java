package org.mokey.mapping.identifiermanager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mokey.mapping.identifiermanager.models.Identifier;
import org.mokey.mapping.identifiermanager.utils.DataSourceUtils;

public class Database {
	
	/**
	 * Insert Identifier parsed from client into database.
	 */
	public boolean insert(Identifier id){
		String sql = "INSERT INTO identities(uuid,ip3, ip, ua,os, osvs, browser, lng)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement statm = null;
		try {
			conn = DataSourceUtils.getConnection();
			conn.setAutoCommit(false);
			statm = conn.prepareStatement(sql);
			statm.setString(1, id.getUuid());
			statm.setString(2, id.getIp3());
			statm.setString(3, id.getIp());
			statm.setString(4, id.getUa());
			statm.setString(5, id.getOs());
			statm.setInt(6, id.getOsvs());
			statm.setString(7, id.getBrowser());
			statm.setString(8, id.getLng());
			int res = statm.executeUpdate();
			conn.commit();
			return res != -1;		
		} catch (Exception e) {
			if(conn != null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		finally{
			try {
				if(statm != null){
					statm.close();
				}
				if(conn != null){
					conn.setAutoCommit(true);
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
