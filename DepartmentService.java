/**
 *  Class: CMIS440 6980 Advanced Programming in Java (2145)
 *  Student Name: Adam Abbott
 *  Instructor: Dr. Alla Webb
 *  Assignment #: Project 2
 *  Description: Using the example 9 from Course Module 3 as a template, develop
 *  a Web App for hiring Employees.  The user shall input parameters via "form.jsp"
 *  which are then handed off to the Servlet and then passed to the Beans that use 
 *  them.  The data shall be stored into 2 text files, Employees.txt which will 
 *  list pertinent info about each employee and Hirings.txt which will include
 *  employee name and department hired to.  A display of all hired employees to date
 *  shall also be included.
 *  Due Date: 7/6/2014
 *  I pledge that I have completed the programming assignment independently.
 *  I have not copied the code from a student or any source.
 *  I have not given my code to any student.
 *  Sign here: Adam R Abbott
 *  Additional Comments:I struggled with how to get the display working.  With 
 *  help from the Professor I understood the functionality that needed to be added
 *  and her examples helped me to implement that code into my project to get it working.
 *  Although not as refined as I might like it, it has the basic functionality.
 */

//This object performs Department services, such as retrieving a Department object
// from the Departments.txt file, or creating a new Department object.
package domain;
import java.io.*;
import java.util.Scanner;
public class DepartmentService {
   //This method finds a specified Department object in the Departments.txt file.
   public Department getDept(String deptName, String deptDesc, String path) {
      Department department=null;
      String dn;
      String dd=null;
      String line;
      try {
         Scanner scanner=new Scanner(new FileReader(path+"Departments.txt"));
         while (scanner.hasNextLine()){
             line = scanner.nextLine();
            String[] dept = line.split("#");
            dn=dept[0];
	    if (dn.equals(deptName)) {
	        department=new Department(-1,dn,dd);
                break;
	    }
         }
      }
      catch (FileNotFoundException exception) {}
      return department;
   }
   //This method adds a new Department object to the Departments.txt file.
   public Department createDept(String deptName, String deptDesc, String path) {
      Department department=new Department(-1,deptName, deptDesc);
      BufferedWriter buffer;
      try {
         buffer=new BufferedWriter(new FileWriter(path+"Departments.txt",true));
         buffer.write(deptName);
         buffer.newLine();
         buffer.close();
      }
      catch (IOException exception) {}
      return department;
   }
}