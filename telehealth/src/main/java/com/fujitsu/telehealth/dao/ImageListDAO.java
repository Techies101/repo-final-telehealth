package com.fujitsu.telehealth.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fujitsu.telehealth.model.LabModel;
import com.fujitsu.telehealth.utils.DBConnection;
import com.fujitsu.telehealth.utils.SQLQuery;

public class ImageListDAO extends SQLQuery {

	public List<LabModel> labImageList(String th_uid) throws ClassNotFoundException, SQLException, IOException {
		List<LabModel> lab = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_LAB_BY_PATIENT);
			preparedStatement.setString(1, th_uid);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("th_id");
				String date = rs.getString("th_date");
				String time = rs.getString("th_time");
				String uid = rs.getString("th_uid");
				Blob blob = rs.getBlob("th_image");
				lab.add(new LabModel(id, date, time, uid, blob));
				System.out.println(blob);

			}
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return lab;
	}
}