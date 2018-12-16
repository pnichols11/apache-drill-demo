/**
 * 
 */
package com.clc.drill.queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 *  This example is using a json file that contains employee information
 *  that is queried to act as though it is a standard database file.
 * </p>
 * <p>
 * This is the beauty of Apache Drill. It let's you take common structured files
 * and treat them as if they are residing in a Database.
 * </p>
 * <p>
 * Pay particular attention to the datasource settings in the application.properties
 * file. This will need to change according to your actual location for file streams
 * and when you are connecting to HDFS or Amazon /Azure.
 * </p>
 * @author Paul W. Nichols
 * @version 1.0
 *
 */
public class JsonEmployees {
	private static final Logger LOG=LoggerFactory.getLogger(JsonEmployees .class.getName());
    
	@Autowired
	private DrillService drillService;
    
    /**
     *  Description: Executes the query
     * @param String
     */
	public void executeQuery(String datasource){
    	Connection conn= DrillService.getConnection(); 
  	     Statement stmt = null;
  	        try{
    	      stmt = conn.createStatement();
  	            // Perform a select on data in the classpath storage plugin. */
  	
		    StringBuilder sql = new StringBuilder();
		    //This will limit to the first 25 records. Remove this for more options/
		    sql.append("SELECT * FROM ");
		    sql.append(datasource);
		    sql.append(" LIMIT 25");
		    ResultSet rs = stmt.executeQuery(sql.toString());

            while(rs.next()) {
              //  int id  = rs.getInt("employee_id");
               // String first = rs.getString("first_name");
              //  String last = rs.getString("last_name");
               String id=rs.getString("employee_id");
               String zip=rs.getString("first_name");
               String ziptype=rs.getString("last_name");
               String city=rs.getString("position_title");
               String state=rs.getString("store_id");
               String lat=rs.getString("Hire_date");
               String lng=rs.getString("salary");
               System.out.printf("\n-------------------------------------------------------------");
               System.out.printf("\nID: %s",id);
               System.out.printf("\nFirst NAME:  %s",zip);
               System.out.printf("\nZipCode Type:  %s",ziptype);
               System.out.printf("\nCity:  %s",city);
               System.out.printf("\nState:  %s",state);
               System.out.printf("\nLatitude  %s", lat);
               System.out.printf("\nLongitude  %s",lng);
               System.out.printf("\n-------------------------------------------------------------"); 
            }

            rs.close();
            stmt.close();
            conn.close();
        } 
        catch(SQLException se) {
        	  LOG.error("SQLException encountered while running query.");
              LOG.error("Stack Trace", se);
        } 
        catch(Exception e) {
            //Handle errors for Class.forName
            LOG.error("Exception in running query.");
            LOG.error("Stack Trace", e);
        } 
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
                if(conn!=null) {
                	conn.close();
                    conn=null;
                }    
                
            } 
            catch(SQLException se2) {
            	LOG.error("Error closing statement and Connection.");
            	LOG.error("Stack Tace",se2);
            }
	          
	    }
	}        			
}
