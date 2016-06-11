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

//This domain object represents a department. The data attributes are 
//all package-private to allow access to them in the DepartmentService class.
package domain;
public class Department implements java.io.Serializable {
   int objectID;
   String deptName;
   String deptDesc;
   //The default constructor is public to be accessible from JSP View pages. 
   public Department() {this(-1,"","");} 
   //The full constructor is package-private to prevent misuse.  
   //The DepartmentService method createDepartment should be used to create 
   //a new Department object.    
   Department(int oID,String dn, String dd) {
      objectID=oID;
      deptName=dn;
      deptDesc=dd;
   }
   public String getDeptName() {return deptName;} 
   public String getDeptDesc() {return deptDesc;}
   public void setObjectID(int id) {objectID=id;}
   public int getObjectID() {return objectID;}
}