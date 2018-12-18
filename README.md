<html>
<body>
<h1> Apache Drill Demo -- Alpha 1 Version </h1>
<img src="http://computer-logic.us/clc/apache-drill.png" alt="">
<h1>Required Software</h1>
<div>
In order to run this demo, you will need the following applications and runtimes installed on your machine
<ol>
<li>Java JRE 1.8.x</li> 
<li>Apache Drill  version 1.14.0</li>
<li>The free-zipcode-database.csv file </li>
<li>Apache Maven</li>
<li>The self contained jar file.</li>
</div>
<div>
 <h1>Obtaining the Software</h1>
 <p>
   To obtains the software, you can download from the following sites
</p>
<p>
  <ol>
  <li> <a href = "https://www.oracle.com/technetwork/java/javase/downloads/index.html">Oracle Java SDK 1.8</a></li>
  <li> <a href= "https://openjdk.java.net/install/"> Open Java JDK 1.8</a></li>
  <li> <a href= "https://drill.apache.org/download/">Apache Drill 1.14.0</a></li>
  <li> <a href="https://maven.apache.org/download.cgi">Apache Maven</a></li>
  <li> <a href="computer-logic.us/clc/apache-drill-embedded.jar"> Self Contained Jar File, apache-drill-embedded.jar</a></li>
  <li> <a href=""> The free-zipcode-database.csv file</a></li>
  </ol>
  </p>
</div>
<div>
 <h1>Running the Softwate </h1>
 <p>
   <h2> Self-Contained Jar File </h2>
      <h4>Two Ways to Run</h4>
      <p>
        <ol> 
          <li><b>As Is:</b></li>
             <p>
             You can run the jar file <b>AS IS</b> provide you follow the directions below.
             <br/>
              1. You must have all of the required software installed.
             <br/> 
              2. You must install Apache Drill 1.14.0 in a folder called <b><i>/export/programs/apache-drill-1.14.0</b></i>
             <br/>
              3. You must install the free-zipcode-database.csv file under the <b><i>/export/program/apache-drill-1.14.0/sample-data</b></i> folder
             (This is located in the data folder on this site).
            <p>
         <li> <b>Modify the jar file contents</b> (application.yml) file. </li>
             <p>
             You can modify the jar file's application.yml file and change the location of apache drill and you can
             run the jar file from another location.
             </br>
             1. Change the application.yml file's <b><i> datasource.location</b></i> to point to your apache drill installation directories
             for the csv and parquet sample data locations. Make sure to leave the '' in place. 
             <br/>
             2.Once you edit this file, make  sure to save it back to the original jar file.
            </p>
        <p>
         <li><b>Executing and running either the unmodified or modified jar file.</b></li>
         <p>
          To run either the unmodified or modified jar file open a terminal (Windows, Linux. Mac) 
          and where you downloaded the jar file, type in the following at the terminal.
          Cmd/><b><i>java -jar apache-drill-embedded.jar</i></b>
        </p>
       </p> 
     </ol>      
 </p>
 </div>
 <div>
 <h1> Building from Source </h1>
   <p>
     To build from source, you can use an IDE or Maven. Once built, it will execute like any Spring Boot command line
     application.
   </p>
</div>
<div>
  <h1>Updates</h1>
  I will be updating and adding additional functionality to this Apache Drill Demo in the future, so stay tune!
</div>
