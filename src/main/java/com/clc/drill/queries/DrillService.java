/**
 * 
 */
package com.clc.drill.queries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <b>Description: </b>:
 * </p>
 * <p>
 * This class instance will provide connectivity to the Drill Datasource
 * </p>
 * <p>
 * This datasource is using the Drill Embeeded Service for Demo purposes. For production 
 * on the Amazon, Azure or other Big Data Cloud servers, you will need to change to
 * fir the actual Cloud URLs. and include the User ID and Password
 * </p>
 * <p>
 * Amazon S3 Example
 * </p>
 * <p>
 * 	<b><i>jdbc:drill:zk=local> USE `s3`.`root`;</b></i>
 * </p>
 * <p>
 * For Hadoop Internal Clusters, you would use the URLs for the HDFS cluster, with UserID
 * and Password.
 * </p>
 * <p>
 *  HDFS Example:
 * </p>
 * <p>
 * 	<b><i>DB_URL = jdbc:drill:zk=local</b></i>
 * </p>
 * @author Paul W. Nichols
 * @version 1.0
 */
@Service
public class DrillService {
  private static Logger LOG =LoggerFactory.getLogger(DrillService.class.getName());
  private static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
  //private static final String DB_URL="jdbc:drill:drillbit=localhost";
  private static final String DB_URL="jdbc:drill:drillbit=localhost";
  private static final String USER_ID="admin";
  private static final String PASSWORD="admin";
  
  
  
  
 public  DrillService () {
	 
  }
 
 public static Connection getConnection() {
	 Connection conn=null;
	   try {
		 Class.forName(JDBC_DRIVER);
	     conn = DriverManager.getConnection(DB_URL);

	   }
	   catch(Exception e) {
		  LOG.error("Could not establish Connection.");
		  LOG.error(String.format("Error message is: %s", e.getMessage()));
		  LOG.error("Stack trace:",e);
	   }
	  return conn; 
	 } 
 }
 
 

