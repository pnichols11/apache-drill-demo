/**
 * 
 */
package com.clc.drill.queries;

import java.sql.Connection;
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
 *  This example is using a parquet file structure that contains employee information
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
public class ParquetNations {
  private static final Logger LOG =LoggerFactory.getLogger(ParquetNations.class.getName());
    
    @Autowired
    DrillService drillService;
    
    /**
     * Description: Executes Query
     * @param String
     */
    public void executeQuery(String datasource){
    	Connection conn= DrillService.getConnection(); 
 	     Statement stmt = null;
  	        try{
  	            stmt = conn.createStatement();
  	            // Perform a select on data in the classpath storage plugin. */
  			    StringBuilder sql = new StringBuilder();
  			    //sql.append("select columns[0] as x1, columns[1] as x2 from dfs.`/applications/programming/spark/spark_class/sample_data/user.psv`");
  	            sql.append("SELECT * FROM ");
  	            sql.append(datasource);
  	            sql.append("");
  			    ResultSet rs = stmt.executeQuery(sql.toString());

  	            while(rs.next()) {
  	              //  int id  = rs.getInt("employee_id");
  	               // String first = rs.getString("first_name");
  	              //  String last = rs.getString("last_name");
  	               String id=rs.getString("N_NATIONKEY");
  	               String country=rs.getString("N_NAME");
  	               String regionKey=rs.getString("N_REGIONKEY");
  	               String comment=rs.getString("N_COMMENT");
  	             
  	               System.out.printf("\n-------------------------------------------------------------");
  	               System.out.printf("\nID: %s",id);
  	               System.out.printf("\nCountry:  %s",country);
  	               System.out.printf("\nRegion Key:  %s",regionKey);
  	               System.out.printf("\nComment:  %s",comment);
  	               System.out.printf("\n-------------------------------------------------------------"); 
  	            }

	            rs.close();
	            stmt.close();
	            conn.close();
	        } 
  	        catch(SQLException se) {
  	        	LOG.error("SQLError executing statement.");
  	        	LOG.error("Stack Tace",se);
	            
	        } 
  	        catch(Exception e) {
  	        	LOG.error("Error executing statement.");
  	        	LOG.error("Stack Tace",e);
	        } 
  	        finally {
	            try{
	                if(stmt!=null)
	                    stmt.close();
	                if(conn!=null)
	 	               conn.close();
	 	               conn=null;
	 	            } 
	            
	          	catch(Exception se) {
	                LOG.error("SQL Exception encountered while attempting to close objects");
	            }
	        }
	}        			
}
