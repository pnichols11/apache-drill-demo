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
 *  This example is using a csv file that contains State, City, and ZipCode information
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
public class CSVStateCityZip {
	private static final Logger LOG=LoggerFactory.getLogger(CSVStateCityZip .class.getName());
    
    
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
            sql.append("SELECT columns[0] AS ID, columns[1] AS ZIPCODE,");
            sql.append("columns[2] AS ZIPCODE_TYPE,columns[3] AS CITY,");
            sql.append("columns[4] AS STATE,columns[5] AS LOCATION_TYPE,");
            sql.append("columns[6]AS LATITUDE, columns[7] AS LONGITUDE  FROM ");
            //sql.append(" FROM dfs.`/export/programs/apache-drill-1.14.0/sample-data/free-zipcode-database.csv`");
            sql.append(datasource);
            sql.append(" ORDER BY STATE");
            sql.append("");
		    ResultSet rs = stmt.executeQuery(sql.toString());

            while(rs.next()) {
              //  int id  = rs.getInt("employee_id");
               // String first = rs.getString("first_name");
              //  String last = rs.getString("last_name");
               String id=rs.getString("ID");
               String zip=rs.getString("ZIPCODE");
               String ziptype=rs.getString("ZIPCODE_TYPE");
               String city=rs.getString("CITY");
               String state=rs.getString("STATE");
               String lat=rs.getString("LATITUDE");
               String lng=rs.getString("LONGITUDE");
               System.out.printf("\n-------------------------------------------------------------");
               System.out.printf("\nID: %s",id);
               System.out.printf("\nZipCode:  %s",zip);
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
        	LOG.error("SQLError executing statement.");
        	LOG.error("Stack Tace",se);
        } 
        catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } 
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
                if(conn !=null) {
                	conn.close();
                	conn=null;
                }
            } 
            catch(SQLException se2) {
            	LOG.error("SQLException encountered while running query.");
                LOG.error("Stack Trace", se2);
            }
          	catch(Exception se) {
          		LOG.error("Error closing statement and Connection.");
            	LOG.error("Stack Tace",se);
            }
        }
	 } 
          
}
