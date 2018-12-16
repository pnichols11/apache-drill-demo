/**
 *<p>
 * <b>Description:</b>
 *</p>
 * This is a sample Apache Drill Application.
 *</p>
 *<p>
 * Apache Drill is a rather new technology to assist with BIG Data
 * work, by allowing you to query file structures using standard SQL
 * instead of having to write complex Map-Reduce or Spark jobs
 *</p>
 *<p>
 * Anyone with basic SQL skills can leverage Apache Drill to query
 * local data files, as well as BIG Data HADOOP files (using HDFS),
 * AMAZON Cloud Big Data Services, or Hive, HBase,and NOSQL
 * databases like Cassandra and MongoDB.
 *</p>
 *<p>
 * In addition, you can also query RDBMS data from mySQL, Oracle, etc.
 *</p>
 *<p>
 *  For smaller jobs, it can augment Apache SQOOP for creating flat files
 *  from larger datastores and insert HADOPP HDFS structures into other
 *  databases (Cassandra, Mongo, Oracle,MySQL, etc).
 *</p>
 *<p>
 * This demo of Apache Drill uses three different file types to show the power of Apache Drill.
 *</p>
 *<p>
 *  <ol>
 *    <li> JSON File Format</li>
 *    <li> CSV File Format</li>
 *    <li> Parquet File Format</li>
 *  </ol>   
 *</p>
 *<p>
 * Since we did not want to require a HDFS or Hadoop VM, we are using the local
 * embedded DRILL version. You will need to download DRILL 1.14.0 and install it
 * on your local machine in order to use this program. We will also make available
 * the sample csv file we are using for this exercise.
 *</p>
 * 
 */
package com.clc.drill;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clc.drill.queries.CSVStateCityZip;
import com.clc.drill.queries.JsonEmployees;
import com.clc.drill.queries.ParquetNations;
/**
 * @author nichols
 *
 */
@SpringBootApplication
public class DrillApplication implements CommandLineRunner{
     private static final Logger LOG=LoggerFactory.getLogger(DrillApplication.class.getName());
     private static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
     //private static final String DB_URL="jdbc:drill:drillbit=localhost";
     private static final String DB_URL="jdbc:drill:drillbit=localhost";
     private static final Scanner INPUT = new Scanner(System.in); 
     
    @Value("${datasource.locations.json}")
    private String json;
    @Value("${datasource.locations.csv}")
    private String csv;
    @Value("${datasource.locations.parquet}")
    private String parquet;
    
   
   /**
    * 
    * @param args
    */
	public static void main (String args[]) {
		SpringApplication app = new SpringApplication(DrillApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

	/**
	 * 
	 */
	@Override
	public void run(String... args) throws Exception {
	   LOG.info("STARTING DRILL SERVICE .. ");
  	   showMenu();
	}
	/**
	 * Description: Shows a Menu to select options
	 */
	private void showMenu() {
		StringBuilder menu= new StringBuilder();
		 menu.append("\n\t\t========================================================");
		 menu.append("\n\t\t=              APACHE DRILL TEST MENU                  =");
		 menu.append("\n\t\t=              ----------------------                  =");
		 menu.append("\n\t\t=  1. Run a JSON Query Example                         =");
		 menu.append("\n\t\t=  2. Run a CSV Query Example                          =");
		 menu.append("\n\t\t=  3. Run a Parquet Query Example                      =");
		 menu.append("\n\t\t=  4. Quit                                             =");
		 menu.append("\n\t\t========================================================");
		 menu.append("\n");
		 menu.append("\n\t Enter an option and Press <ENTER>");
		 System.out.printf("%s",menu.toString());
		 String option = INPUT.nextLine();
		 int selected = Integer.parseInt(option);
		 runQueries(selected);
		 
	}
	/**
	 * Takes the selected and runs the query
	 * @param selected
	 */
	private void runQueries(int selected) {
		switch(selected) {
		case 1: {
			JsonEmployees jsonEmp= new JsonEmployees();
			  jsonEmp.executeQuery(json);
			  displayMenu();
			break;
		}
		case 2: {
			CSVStateCityZip csvQuery= new CSVStateCityZip();
			  csvQuery.executeQuery(csv);
			  displayMenu();
			break;
		}
		case 3:{
			ParquetNations pn= new ParquetNations();
			  pn.executeQuery(parquet);
			  displayMenu();
			break;
		}
		case 4: {
			System.out.println("\t Are you sure you want to exit <y/n>");
			String input=INPUT.nextLine();
			 if(input.toLowerCase().equals("y"))
				 System.exit(0);
			break;
		}
		default:{
			System.out.println("\n\t\tYou did not select a valid option from the menu");
			displayMenu();
			break;
		}
	  }
	}
	
	private void displayMenu(){
		System.out.println("\n\t Press <ENTER> to continue.");
		String input=INPUT.nextLine();
		showMenu();
		
	}
}
