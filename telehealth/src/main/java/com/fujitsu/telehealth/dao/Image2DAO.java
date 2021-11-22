package com.fujitsu.telehealth.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import com.fujitsu.telehealth.utils.DBConnection;
import com.fujitsu.telehealth.utils.SQLQuery;

public class Image2DAO extends SQLQuery {

	public void labImage(String uid, Part image) throws SQLException, IOException {
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_UPLOAD_LAB_HISTORY);
			Date date = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE MMM d,yyyy");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
			String dateAsString = dateFormatter.format(date);
			String timeAsString = timeFormatter.format(date);
			InputStream is = image.getInputStream();
			preparedStatement.setString(1, uid);
			preparedStatement.setBlob(2, is);	
			preparedStatement.setString(3, dateAsString);
			preparedStatement.setString(4, timeAsString);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		}finally {
			con.close();
		}
	}
}