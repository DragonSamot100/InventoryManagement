package application;

import java.sql.*;
import org.h2.jdbcx.JdbcDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
public class test 
{
	public static void main(String[]args){

	    JdbcDataSource ds = new JdbcDataSource();
	    ds.setURL("jdbc:h2:./database/management");
	    ds.setUser("sa");
	    ds.setPassword("sa");
	    try {
	        Connection conn = ds.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    

	}
}
