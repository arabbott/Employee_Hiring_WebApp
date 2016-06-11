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
package web;
//Business logic component imports
import domain.Department;
import domain.Employee;
import domain.HireService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Status;
public class HiringServlet extends HttpServlet {
   public void doPost(HttpServletRequest request, 
         HttpServletResponse response) throws IOException, ServletException {
      //Dispatch the request
      processRequest(request, response);
   }
   //The method that performs the Control aspects of the Web application
   public void processRequest(HttpServletRequest request, 
      HttpServletResponse response) throws IOException, ServletException {
      //Declare the dispatcher for the View
      RequestDispatcher view=null; 
      //Create the business logic object
      HireService hireService=null;
      //Create the Status object and store it in the request 
      //for use by the 'Hire Form' View (if necessary)
      Status status=new Status();
      request.setAttribute("status", status);
      //Retrieve the HTML form parameters
      String deptName=request.getParameter("department");
      String name=request.getParameter("name");
      String jobTitle=request.getParameter("jobTitle");
      String yearHired=request.getParameter("yearHired");
      String gender=request.getParameter("gender");
      String deptDesc=null;
      //Verify form fields data; create Exception objects if data are missing
      //These "error messages" are presented to the user 
      //in a red bulleted list
      if (deptName.equals("unknown"))
         status.addException(new Exception(
            "Please select a department. "));
      if ((name==null) || (name.length()==0))
         status.addException(new Exception("Please enter employee's name. "));
      if ((jobTitle==null) || (jobTitle.length()==0))
         status.addException(new Exception("Please enter employee's job title. "));
      if ((yearHired==null) || (yearHired.length()==0))
         status.addException(new Exception("Please enter year hired. "));
      if ((gender==null) || (gender.length()==0))
         status.addException(new Exception("Please enter employee's gender. "));
      
      //In case of errors, forward the request back to 
      //'Hire Form' View 
      //and return without proceeding with the rest of 
      //the business logic.
      if (!status.isSuccessful()) {
         view=request.getRequestDispatcher("form.jsp");
         view.forward(request, response);
         return;
      }
      //If no verification errors are found, the Controller
      //uses the business services to perform the registration.
      try {
         Properties sysprops=System.getProperties();
         String fs=sysprops.getProperty("file.separator");
         String path=getServletContext().getRealPath("/")+
                "WEB-INF" + fs + "storage"+fs; // Modified path to add fs after WEB-INF
         hireService=new HireService();
         //Retrieve the Department object and verify that it exists
         Department department=hireService.getDept(deptName, deptDesc, path);
         if (department==null) throw new Exception("The department you have "+
               " selected does not yet exist; please select another one.");
         //Create and populate the employee object
         Employee employee=hireService.createEmployee(
            name,jobTitle,yearHired,gender);
         //Delegate hiring to the HireService object
         hireService.hire(department, employee, path);
         //Create the array list of all employees from Employees.txt for display
         ArrayList hiringList = hireService.getHiring(path);
         request.setAttribute("department", department);
         request.setAttribute("employee", employee);
         request.setAttribute( "hiringList", hiringList);
         
         //Select the next View for the user in case 
         //hiring is successful
         //Forward to the confirmation.jsp View
         view=request.getRequestDispatcher("confirmation.jsp");
         view.forward (request, response);
      } 
      //Select the next View for the user in case of business logic errors.
      //Forward back to the 'Hiring Form' View to report 
      //what errors occurred. 
      catch (Exception e) {
         status.addException(e);
         //Select next View.
         //Exceptions are caught, forward back to the form.jsp View.
         view=request.getRequestDispatcher("form.jsp");
         view.forward(request, response);
      }
   }
}