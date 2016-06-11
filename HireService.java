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

//This object performs employee hiring services – it acts as a
//Facade into the business logic of hiring an Employee in a Department.  
package domain;
import java.util.*;
import java.io.*;
public class HireService {
   public HireService() {}
   //This method finds the specified Department object
   public Department getDept(String deptName, String deptDesc, String path) {
      //Delegate to the HireService method
      DepartmentService deptSvc=new DepartmentService();
      return deptSvc.getDept(deptName, deptDesc, path);
   }
   public Employee createEmployee(String name,String jobTitle,
         String yearHired,String gender) {
      return new Employee(-1,name,jobTitle,yearHired,gender);}
   public void hire(Department department, Employee employee, String path) 
         throws IOException {
      BufferedWriter buffer;
      try {
         //Store the Employee object into Employees.txt file
         buffer=new BufferedWriter(new FileWriter(path+"Employees.txt",true));
         buffer.write(employee.getName()+"#"+ employee.getJobTitle()+ 
            "#"+employee.getYearHired()+"#"+                      
            employee.getGender());
        buffer.newLine();
        buffer.close();
        //Store the Employee and Department hired into Hirings.txt file
        buffer=new BufferedWriter(
                 new FileWriter(path+"Hirings.txt",true));
        buffer.write(employee.getName()+"#"+department.getDeptName());
        buffer.newLine();
        buffer.close();
     }
     catch (IOException exception) {}
   }
   
   //THis method creates the array and reads in all employees from Employees.txt
   //for displaying on the confirmation.jsp page
   public ArrayList getHiring(String path ) throws Exception
    {
        ArrayList hiringList = new ArrayList();
        String dispEmps;
        String n = null,jt = null,yh = null,g = null;
        //String finalDisp;
        Scanner inputFile = new Scanner( new File(path+"Employees.txt") );
            
        while( inputFile.hasNextLine() ){
            dispEmps=inputFile.nextLine();
            String[] splitEmps = dispEmps.split("#");
            for (int i = 0; i < splitEmps.length; i++){
                n=splitEmps[0];
                jt=splitEmps[1];
                yh=splitEmps[2];
                g=splitEmps[3];
            }
            hiringList.add( n+"  |  "+jt+"  |  "+yh+"  |  "+g ); 
        }

        return hiringList;
    }       
}