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

<%@ page session="false"%>

<%--Retrieve the Course and Student beans from the Request scope--%>
<jsp:useBean id="department" scope="request" class="domain.Department"/>
<jsp:useBean id="employee" scope="request" class="domain.Employee"/>
<jsp:useBean id="hiringList" scope="request" class="java.util.ArrayList"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Hiring Confirmation</title></head>
<body>
   <p><b>Hiring Confirmation</b></p>
      Thank you, <b><jsp:getProperty name="employee" property="name"/></b>, 
      has been added to the <b>
      <jsp:getProperty name="department" property="deptName"/></b> department.
  
   <form action="/CMIS440P2" method="post">
      <input type="submit" value="Click to Enter another Employee" name="more" />
   </form>      
      
      <p><b>Below is a list of all hired Employees to date:</b></p>   
      <p>&nbsp;&nbsp;Name&nbsp;|&nbsp;
      Job Title&nbsp;|&nbsp;
      Year Hired&nbsp;|&nbsp;
      Gender</p>
      <c:forEach var="reg" items="${hiringList}">
        <c:out value="${reg}"></c:out>
        <BR>
    </c:forEach>
</body>
</html>