README

Project Management System for Poised engineering firm

About:

This is a Project Management System for an engineering firm 
called Poised that allows the user to add, update, search 
and finalize projects. The administrator can also add and edit contacts.

It is capable of adding multiple projects and contacts. 
All projects can be tracked with reports and also be finalised 
with an invoice generated if there is still an outstanding balance owed.

It is built in Java and uses a MySQL Database. I also used JavaDoc.

Contributors:

No contributions at this time.

Usage:

Requirements for running

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.ResultSet;

SQL details
See SQL.txt for database tables
String url = "jdbc:mysql://localhost:3306/poisedpms?useSSL=false";
String user = "otheruser";
String password = "swordfish";
