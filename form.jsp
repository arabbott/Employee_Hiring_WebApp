<%--Class: CMIS440 6980 Advanced Programming in Java (2145)
    Student Name: Adam Abbott
    Instructor: Dr. Alla Webb
    Assignment #: Project 2
    Description: Using the example 9 from Course Module 3 as a template, develop
    a Web App for hiring Employees.  The user shall input parameters via "form.jsp"
    which are then handed off to the Servlet and then passed to the Beans that use 
    them.  The data shall be stored into 2 text files, Employees.txt which will 
    list pertinent info about each employee and Hirings.txt which will include
    employee name and department hired to.  A display of all hired employees to date
    shall also be included.
    Due Date: 7/6/2014
    I pledge that I have completed the programming assignment independently.
    I have not copied the code from a student or any source.
    I have not given my code to any student.
    Sign here: Adam R Abbott
    Additional Comments:I struggled with how to get the display working.  With 
    help from the Professor I understood the functionality that needed to be added
    and her examples helped me to implement that code into my project to get it working.
    Although not as refined as I might like it, it has the basic functionality.
--%>
<%@page session="false" import="java.util.Iterator"%>
<%--Retrieve the Status bean from the Request scope--%>
<jsp:useBean id="status" scope="request" class="util.Status"/>
<html>
<head><title>Hiring Form</title></head>
<body>
<p><b>Hiring Form</b></p>
<%--Verification error presentation
The Controller verifies the form parameters and adds Exception 
objects to the Status object to flag missing data, then forwards 
the request back to this form.jsp page View to present the user 
with the registration form again and to display the list of 
verification errors using red bullets.--%>
<%if ((status!=null) && !status.isSuccessful()) {%>
   <font color="red">There were problems processing your request:
   <ul><%Iterator errors=status.getExceptions();
   while (errors.hasNext()) {
      Exception ex=(Exception) errors.next();%>
      <li><%= ex.getMessage()%><%}%></ul></font><%}%>
<%--Input fields presentation ? lists all the fields for entering 
Web application parameters--%>
<form action="hire" method="post"><hr/>
<table>
<%--The if statements inside <option ?> tags fill in the default values 
of the combo-boxes from the parameters passed in from last request--%>
<tr><td>Department:</td>
   <td><select name="department">
      <%String department=request.getParameter("department");
      if (department==null) {department="";}%>
      <option name="department" value="unknown"
         <%if (department.equals("unknown")) 
            {out.print(" selected");}%>>select?</s:option>
      <option name="department" value="Human-Resources"
         <%if (department.equals("Human-Resources")) 
            {out.print(" selected");}%>>Human-Resources</s:option>
      <option name="department" value="Software-Development"
         <%if (department.equals("Software-Development")) 
            {out.print(" selected");}%>>Software-Development</s:option>
      <option name="department" value="Media-Relations"
         <%if (department.equals("Media-Relations")) 
            {out.print(" selected");}%>>Media-Relations</s:option></select></td></tr>
         
</table><hr/>
<table>

<%--The default values of text fields are filled in from 
    the parameters passed in from last request--%>
<tr><td>Name:</td>
   <td><% String name=request.getParameter("name");
      if (name==null) name="";%>
      <input type="text" name="name" value="<%=name%>" size="50"></td></tr>
<tr><td>Job Title:</td>
   <td><%String jobTitle=request.getParameter("jobTitle");
      if (jobTitle==null) jobTitle="";%>
      <input type="text" name="jobTitle" value="<%=jobTitle%>" size="50"></td>
</tr>
<tr><td>Year Hired:</td>
   <td><% String yearHired=request.getParameter("yearHired");
      if (yearHired==null) yearHired="";%>
      <input type="text" name="yearHired" value="<%=yearHired%>" size="20"></td></tr>
<tr><td>Gender:</td>
   <td><% String gender=request.getParameter("gender");
      if (gender==null) gender="";%>
      <input type="text" name="gender" value="<%=gender%>" size="2"></td></tr>

</table><hr/>
<input type="submit" value="Hire">
</form>
</body>
</html>