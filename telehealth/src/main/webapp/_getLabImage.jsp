<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.fujitsu.telehealth.utils.DBConnection"%>
<%
    String id = request.getParameter("id");
    try {
    	
        Connection con = DBConnection.connect();
        PreparedStatement ps = con.prepareStatement("select * from tbl_lab where th_id=?");
        
        ps.setString(1, id);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("th_image");
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("image/gif");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
            
        } else {
            System.out.println("No image found with this id.");
        }
    } catch (Exception e) {
        out.println(e);
    }
%>
