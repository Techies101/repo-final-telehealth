package com.fujitsu.telehealth.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.Part;

import com.fujitsu.telehealth.utils.DBConnection;
import com.fujitsu.telehealth.utils.SQLQuery;

public class ImageDAO extends SQLQuery {

	public void paymentImage(int id, Part image) throws ClassNotFoundException, SQLException, IOException {
			Connection con = null;
			try {
				con = DBConnection.connect();
				PreparedStatement preparedStatement = con.prepareStatement(SQL_UPLOAD_PROOF_PAYMENT);
	            InputStream is = image.getInputStream();
	            preparedStatement.setBlob(1, is);
	            preparedStatement.setFloat(2, id);
				preparedStatement.executeUpdate();
			}catch (SQLException ex) {
				DBConnection.printSQLException(ex);
			}finally {
				con.close();
			}
	}
}
