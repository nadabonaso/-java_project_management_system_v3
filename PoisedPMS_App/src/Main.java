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

/**
 * This is a Project Management System for a structural engineering firm called
 * "Poised". They want a Java program that they can use to keep track of the 
 * many projects on which they work. This tool allows the user to add, update, 
 * search and finalize projects. The administrator can also add and edit contacts.
 * 
 * @author Nada
 *
 */

public class Main {
	public int menu;
	public boolean isNumber = false;
	public static int counter = 0;
	private static int projId = 0;
	private static String projName = "";
	private static String structType = "";
	private static String structAddress = "";
	private static String erfNum = "";
	private static String dueD = "";
	private static String invNum = "";
	private static double invTotal = 0;
	private static double invBalance = 0;
	private static int custId = 0;
	private static int contId = 0;
	private static int archId = 0;
	private static int projManId = 0;
	private static int structEngId = 0;
	private static String projStatus = "In Progress";
	private static String finDate = "NA";
	private static ResultSet results;
	private static String firstName = "";
	private static String lastName = "";
	private static String phone = "";
	private static String email = "";
	private static String address = "";
	public static boolean usingName = false;
	public static boolean finalised = false;
	public static int recordCount = 0;
	public static boolean isNum = false;
	public static boolean nameExists = false;
	public static boolean contactExists = false;
	public static boolean custExists = false;
	public static boolean contExists = false;
	public static boolean archExists = false;
	public static boolean structEngExists = false;
	public static boolean projManExists = false;
	
	public static void main(String[] args) throws SQLException {
		boolean quit = false;
		Scanner input = new Scanner(System.in);

		int menu = 0;
		System.out.println("POISED\nProject Management System\n");

		// SQL details
		String url = "jdbc:mysql://localhost:3306/poisedpms?useSSL=false";
		String user = "otheruser";
		String password = "swordfish";

		PreparedStatement myStmt = null;
		Connection myConn = null;

		do {
			try {
				// Main Menu
				System.out.println("Main Menu\n--------------------------------");
				System.out.println("1 - Add Contact");
				System.out.println("2 - Edit Contact");
				System.out.println("3 - Add Project");
				System.out.println("4 - Edit Project");
				System.out.println("5 - Reports");
				System.out.println("6 - Exit");
				System.out.print("Enter your selection: ");
				if (isNum)
				{
					isNum = false;
				}
				finalised = false;			
				menu = input.nextInt();

				String s = input.nextLine(); // unaffected by previous nextInt()	
			
				switch (menu) {			
				case 1:
					// Add a new contact	
					do {
						System.out.println("\n1. Add New Contact\n--------------------------------");
						// Add Contact Menu
						System.out.println("1 - Add Customer");
						System.out.println("2 - Add Contractor");
						System.out.println("3 - Add Architect");
						System.out.println("4 - Add Structural Engineer");
						System.out.println("5 - Add Project Manager");
						System.out.println("6 - Main Menu");
						System.out.print("Enter your selection: ");
						menu = input.nextInt();
						input.nextLine(); // eats extra space									
						System.out.println();

						switch (menu) {
						case 1:						
							// Add New Customer
							System.out.println("1. Add Customer\n--------------------------------");
							try {
								//User inputs for all the info needed to create person
								firstName = firstNameInput(input, firstName);							
								lastName = lastNameInput(input, lastName);							
								phone = phoneInput(input, phone);						
								email = emailInput(input, email);							
								address = addressInput(input, address);							
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "INSERT INTO Customer" + " (first_name, last_name, phone, email, address)"
										+ " values (?, ?, ?, ?, ?)";
	
								myStmt = myConn.prepareStatement(sql);
								Statement statement = myConn.createStatement();
	
								// Set parameter values
								myStmt.setString(1, firstName);
								myStmt.setString(2, lastName);
								myStmt.setString(3, phone);
								myStmt.setString(4, email);
								myStmt.setString(5, address);
	
								// 3. Execute SQL query
								myStmt.executeUpdate();
	
								System.out.println("Insert complete.\n");
								System.out.println("Added to Customer List\n--------------------------------");
								printAllFromCustomerTable(statement);
								System.out.println();
								statement.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 2:
							// Add New Contractor
							System.out.println("2. Add Contractor\n--------------------------------");
							try {
								//User input methods for all the info needed to create person
								firstName = firstNameInput(input, firstName);							
								lastName = lastNameInput(input, lastName);							
								phone = phoneInput(input, phone);						
								email = emailInput(input, email);							
								address = addressInput(input, address);
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "INSERT INTO Contractor" + " (first_name, last_name, phone, email, address)"
										+ " values (?, ?, ?, ?, ?)";
	
								myStmt = myConn.prepareStatement(sql);
								Statement statement = myConn.createStatement();
	
								// Set parameter values
								myStmt.setString(1, firstName);
								myStmt.setString(2, lastName);
								myStmt.setString(3, phone);
								myStmt.setString(4, email);
								myStmt.setString(5, address);
	
								// 3. Execute SQL query
								myStmt.executeUpdate();
	
								System.out.println("Insert complete.\n");
								System.out.println("Added to Contractor List\n--------------------------------");
								printAllFromContractorTable(statement);
								System.out.println();
								statement.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 3:
							// Add New Architect
							System.out.println("3. Add Architect\n--------------------------------");
							try {
								//User input methods for all the info needed to create person
								firstName = firstNameInput(input, firstName);							
								lastName = lastNameInput(input, lastName);							
								phone = phoneInput(input, phone);						
								email = emailInput(input, email);							
								address = addressInput(input, address);	
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);	
	
								// 2. Create a statement
								String sql = "INSERT INTO Architect" + " (first_name, last_name, phone, email, address)"
										+ " values (?, ?, ?, ?, ?)";
	
								myStmt = myConn.prepareStatement(sql);
	
								// Set parameter values
								myStmt.setString(1, firstName);
								myStmt.setString(2, lastName);
								myStmt.setString(3, phone);
								myStmt.setString(4, email);
								myStmt.setString(5, address);
	
								// 3. Execute SQL query
								myStmt.executeUpdate();
								Statement statement = myConn.createStatement();
	
								System.out.println("Insert complete.\n");
								System.out.println("Added to Architect List\n--------------------------------");
								printAllFromArchitectTable(statement);
								System.out.println();
								statement.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 4:
							// Add New Structural Engineer
							System.out.println("4. Add Structural Engineer\n--------------------------------");
							try {
								//User inputs for all the info needed to create person
								firstName = firstNameInput(input, firstName);							
								lastName = lastNameInput(input, lastName);							
								phone = phoneInput(input, phone);						
								email = emailInput(input, email);							
								address = addressInput(input, address);	
								
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "INSERT INTO struct_eng" + " (first_name, last_name, phone, email, address)"
										+ " values (?, ?, ?, ?, ?)";
	
								myStmt = myConn.prepareStatement(sql);
								Statement statement = myConn.createStatement();
								
								// Set parameter values
								myStmt.setString(1, firstName);
								myStmt.setString(2, lastName);
								myStmt.setString(3, phone);
								myStmt.setString(4, email);
								myStmt.setString(5, address);
	
								// 3. Execute SQL query
								myStmt.executeUpdate();
	
								System.out.println("Insert complete.\n");
								System.out.println("Added to Structural Engineer List\n--------------------------------");
								printAllFromStructEngTable(statement);
								System.out.println();
								statement.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 5:
							// Add New Project Manager
							System.out.println("5. Add Project Manager\n--------------------------------");
							try {
								//User inputs for all the info needed to create person
								firstName = firstNameInput(input, firstName);							
								lastName = lastNameInput(input, lastName);							
								phone = phoneInput(input, phone);						
								email = emailInput(input, email);							
								address = addressInput(input, address);	
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "INSERT INTO proj_man" + " (first_name, last_name, phone, email, address)"
										+ " values (?, ?, ?, ?, ?)";
	
								myStmt = myConn.prepareStatement(sql);
								Statement statement = myConn.createStatement();
	
								// Set parameter values
								myStmt.setString(1, firstName);
								myStmt.setString(2, lastName);
								myStmt.setString(3, phone);
								myStmt.setString(4, email);
								myStmt.setString(5, address);
	
								// 3. Execute SQL query
								myStmt.executeUpdate();
	
								System.out.println("Insert complete.\n");
								System.out.println("Added to Project Manager List\n--------------------------------");
								printAllFromProjManTable(statement);
								System.out.println();
								statement.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 6:
							break;
	
						default:
							System.out.println(menu + " is not a valid menu option. Please try again.");
						}
	
					} while (menu != 6);
					break;
	
				case 2:
					// Update Contact Details
					do {
						// Edit Contact Menu
						System.out.println("\n2. Edit a Contact\n--------------------------------");
						System.out.println("1 - Edit a Customer");
						System.out.println("2 - Edit a Contractor");
						System.out.println("3 - Edit an Architect");
						System.out.println("4 - Edit a Structural Engineer");
						System.out.println("5 - Edit a Project Manager");
						System.out.println("6 - Main Menu");
						System.out.print("Enter your selection: ");
						menu = input.nextInt();
						input.nextLine();
						System.out.println();
						switch (menu) {
	
						case 1:
							// Customer Update Section
							try {								
								// Search for Customer by ID		
								try {
									System.out.print("Enter Customer ID: ");
									int searchId = input.nextInt();							
									custId = searchId;
									isNum = true;									

								} catch (Exception e) {
									String temp = input.nextLine();
									System.out.println("Please enter a numerical value.\n");
									isNum = false;
									break;
								}
									
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM customer WHERE cust_id = " + "\"" +custId+ "\"";
								myStmt = myConn.prepareStatement(sql);							
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if user input is in database table
								if (!results.next())
								{
									System.out.print("No record, try again\n");
									break;
								}
								else {
									// Prints row
									System.out.println(results.getInt("cust_id") + ", " + results.getString("first_name")
									+ " " + results.getString("last_name") + ", " + results.getString("phone")
									+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
	
								// Close up our connections
								results.close();
	
								do {
									// Sub menu for updating customer data after selecting ID
									System.out.println("\nUpdate Customer Menu\n--------------------------------");
									System.out.println("1 - Update First Name");
									System.out.println("2 - Update Last Name");
									System.out.println("3 - Update Phone");
									System.out.println("4 - Update Email");
									System.out.println("5 - Update Address");
									System.out.println("6 - Back");
									System.out.print("Enter your selection: ");
									menu = input.nextInt();
									input.nextLine();
									System.out.println();
									switch (menu) {
	
									case 1:
										try {
											// Update First Name																												
											firstName = firstNameInput(input, firstName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);	
	
											// 2. Create a statement
											sql = "UPDATE customer SET first_name = " + "\"" + firstName + "\"" + "WHERE cust_id = " + "\"" +custId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("First Name updated.");
											printAllFromCustomerTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 2:
										try {
											// Update Last Name
											lastName = lastNameInput(input, lastName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);											
	
											// 2. Create a statement
											sql = "UPDATE customer SET last_name = " + "\"" + lastName + "\"" + "WHERE cust_id = " + "\"" +custId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Last Name updated.");
											printAllFromCustomerTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 3:
										try {
											// Update Mobile Phone Number
											phone = phoneInput(input, phone);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE customer SET phone = " + "\"" + phone + "\"" + "WHERE cust_id = " + "\"" +custId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Mobile updated.");
											printAllFromCustomerTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 4:
										try {
											// Update Email
											email = emailInput(input, email);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE customer SET email = " + "\"" + email + "\"" + "WHERE cust_id = " + "\"" +custId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Email updated.");
											printAllFromCustomerTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 5:
										try {
											// Update Address
											address = addressInput(input, address);	
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE customer SET address = " + "\"" + address + "\"" + "WHERE cust_id = " + "\"" +custId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Address updated.");
											printAllFromCustomerTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 6:
										break;
	
									default:
										System.out.println("Invalid entry. Please try again.");
									}
	
								} while (menu != 6);
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						// Contractor Update Section
						case 2:
							try {
								// Search for Contractor by ID		
								try {
									System.out.print("Enter Contractor ID: ");
									int searchId = input.nextInt();							
									contId = searchId;
									isNum = true;									

								} catch (Exception e) {
									String temp = input.nextLine();
									System.out.println("Please enter a numerical value.\n");
									isNum = false;
									break;
								}
								
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM Contractor WHERE cont_id = " + "\"" +contId+ "\"";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if user input is in database table
								if (!results.next())
								{
									System.out.print("No record, try again.\n");
									break;
								}
								else {
									// Prints row
									System.out.println(results.getInt("cont_id") + ", " + results.getString("first_name")
											+ " " + results.getString("last_name") + ", " + results.getString("phone")
											+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
	
								// Close up our connections
								results.close();
	
								do {
									// Sub menu for updating contractor data after selecting ID
									System.out.println("\nUpdate Contractor Menu\n--------------------------------");
									System.out.println("1 - Update First Name");
									System.out.println("2 - Update Last Name");
									System.out.println("3 - Update Phone");
									System.out.println("4 - Update Email");
									System.out.println("5 - Update Address");
									System.out.println("6 - Back");
									System.out.print("Enter your selection: ");
									menu = input.nextInt();
									input.nextLine();
									System.out.println();
									switch (menu) {
	
									case 1:
										try {
											// Update First Name
											firstName = firstNameInput(input, firstName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE contractor SET first_name = " + "\"" + firstName + "\"" + "WHERE cont_id = " + "\"" +contId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("First Name updated.");
											printAllFromContractorTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 2:
										try {
											// Update Last Name
											lastName = lastNameInput(input, lastName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE contractor SET last_name = " + "\"" + lastName + "\"" + "WHERE cont_id = " + "\"" +contId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Last Name updated.");
											printAllFromContractorTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 3:
										try {
											// Update Mobile Phone Number
											phone = phoneInput(input, phone);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE contractor SET phone = " + "\"" + phone + "\"" + "WHERE cont_id = " + "\"" +contId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Mobile updated.");
											printAllFromContractorTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 4:
										try {
											// Update Email
											email = emailInput(input, email);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE contractor SET email = " + "\"" + email + "\"" + "WHERE cont_id = " + "\"" +contId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Email updated.");
											printAllFromContractorTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 5:
										try {
											// Update Address
											address = addressInput(input, address);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE contractor SET address = " + "\"" + address + "\"" + "WHERE cont_id = " + "\"" +contId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Address updated.");
											printAllFromContractorTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 6:
										break;
	
									default:
										System.out.println("Invalid entry. Please try again.");
									}
	
								} while (menu != 6);
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 3:
							// Architect Update Section
							try {
								// Search for Architect by ID
								try {
									System.out.print("Enter Architect ID: ");
									int searchId = input.nextInt();							
									archId = searchId;
									isNum = true;									

								} catch (Exception e) {
									String temp = input.nextLine();
									System.out.println("Please enter a numerical value.\n");
									isNum = false;
									break;
								}
							
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM architect WHERE arch_id = " + "\"" +archId+ "\"";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if user input is in database table
								if (!results.next())
								{
									System.out.print("No record, try again\n");
									break;
								}
								else {
									// Prints row
									System.out.println(results.getInt("arch_id") + ", " + results.getString("first_name")
											+ " " + results.getString("last_name") + ", " + results.getString("phone")
											+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
								
								// Close up our connections
								results.close();
	
								do {
									// Sub menu for updating architect data after selecting ID
									System.out.println("\nUpdate Architect Menu\n--------------------------------");
									System.out.println("1 - Update First Name");
									System.out.println("2 - Update Last Name");
									System.out.println("3 - Update Phone");
									System.out.println("4 - Update Email");
									System.out.println("5 - Update Address");
									System.out.println("6 - Back");
									System.out.print("Enter your selection: ");
									menu = input.nextInt();
									input.nextLine();
									System.out.println();
									switch (menu) {
	
									case 1:
										try {
											// Update First Name
											firstName = firstNameInput(input, firstName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE architect SET first_name = " + "\"" + firstName + "\"" + "WHERE arch_id = " + "\"" +archId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("First Name updated.");
											printAllFromArchitectTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 2:
										try {
											// Update Last Name
											lastName = lastNameInput(input, lastName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE architect SET last_name = " + "\"" + lastName + "\"" + "WHERE arch_id = " + "\"" +archId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Last Name updated.");
											printAllFromArchitectTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 3:
										try {
											// Update Mobile Phone Number
											phone = phoneInput(input, phone);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE architect SET phone = " + "\"" + phone + "\"" + "WHERE arch_id = " + "\"" +archId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Mobile updated.");
											printAllFromArchitectTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 4:
										try {
											// Update Email
											email = emailInput(input, email);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE architect SET email = " + "\"" + email + "\"" + "WHERE arch_id = " + "\"" +archId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Email updated.");
											printAllFromArchitectTable(statement);
											statement.close();
	
										} catch (Exception exc) {	
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 5:
										try {
											// Update Address
											address = addressInput(input, address);	
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE architect SET address = " + "\"" + address + "\"" + "WHERE arch_id = " + "\"" +archId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Address updated.");
											printAllFromArchitectTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 6:
										break;
	
									default:
										System.out.println("Invalid entry. Please try again.");
									}
	
								} while (menu != 6);
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 4:
							// Structural Engineer Update Section
							try {
								// Search for Structural Engineer by ID
								try {
									System.out.print("Enter Structural Engineer ID: ");
									int searchId = input.nextInt();							
									structEngId = searchId;
									isNum = true;									

								} catch (Exception e) {
									String temp = input.nextLine();
									System.out.println("Please enter a numerical value.\n");
									isNum = false;
									break;
								}
								
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM struct_eng WHERE struct_eng_id = " + "\"" +structEngId+ "\"";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if user input is in database table
								if (!results.next())
								{
									System.out.print("No record, try again\n");
									break;
								}
								else {
									// Prints row
									System.out.println(results.getInt("struct_eng_id") + ", "
											+ results.getString("first_name") + " " + results.getString("last_name") + ", "
											+ results.getString("phone") + ", " + results.getString("email") + ", "
											+ results.getString("address"));
								}
	
								// Close up our connections
								results.close();
	
								do {
									// Sub menu for updating Structural Engineer data after selecting ID
									System.out
											.println("\nUpdate Structural Engineer Menu\n--------------------------------");
									System.out.println("1 - Update First Name");
									System.out.println("2 - Update Last Name");
									System.out.println("3 - Update Phone");
									System.out.println("4 - Update Email");
									System.out.println("5 - Update Address");
									System.out.println("6 - Back");
									System.out.print("Enter your selection: ");
									menu = input.nextInt();
									input.nextLine();
									System.out.println();
									switch (menu) {
	
									case 1:
										try {
											// Update First Name
											firstName = firstNameInput(input, firstName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE struct_eng SET first_name = " + "\"" + firstName + "\"" + "WHERE struct_eng_id = " + "\"" +structEngId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("First Name updated.");
											printAllFromStructEngTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 2:
										try {
											// Update Last Name
											lastName = lastNameInput(input, lastName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE struct_eng SET last_name = " + "\"" + lastName + "\"" + "WHERE struct_eng_id = " + "\"" +structEngId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Last Name updated.");
											printAllFromStructEngTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 3:
										try {
											// Update Mobile Phone Number
											phone = phoneInput(input, phone);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE struct_eng SET phone = " + "\"" + phone + "\"" + "WHERE struct_eng_id = " + "\"" +structEngId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Mobile updated.");
											printAllFromStructEngTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 4:
										try {
											// Update Email
											email = emailInput(input, email);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE struct_eng SET email = " + "\"" + email + "\"" + "WHERE struct_eng_id = " + "\"" +structEngId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Email updated.");
											printAllFromStructEngTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 5:
										try {
											// Update Address
											address = addressInput(input, address);	
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE struct_eng SET address = " + "\"" + address + "\"" + "WHERE struct_eng_id = " + "\"" +structEngId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();										
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Address updated.");
											printAllFromStructEngTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 6:
										break;
	
									default:
										System.out.println("Invalid entry. Please try again.");
									}
								} while (menu != 6);
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 5:
							// Project Manager Update Section
							try {
								// Search for Project Manager by ID
								try {
									System.out.print("Enter Project Manager ID: ");
									int searchId = input.nextInt();							
									projManId = searchId;
									isNum = true;									

								} catch (Exception e) {
									String temp = input.nextLine();
									System.out.println("Please enter a numerical value.\n");
									isNum = false;
									break;
								}
								
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM proj_man WHERE proj_man_id = " + "\"" +projManId+ "\"";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if user input is in database table
								if (!results.next())
								{
									System.out.print("No record, try again\n");
									break;
								}
								else {
									// Prints row
									System.out.println(results.getInt("proj_man_id") + ", "
											+ results.getString("first_name") + " " + results.getString("last_name") + ", "
											+ results.getString("phone") + ", " + results.getString("email") + ", "
											+ results.getString("address"));
								}
	
								// Close up our connections
								results.close();
	
								do {
									// Sub menu for updating Project Manager data after selecting ID
									System.out.println("\nUpdate Project Manager Menu\n--------------------------------");
									System.out.println("1 - Update First Name");
									System.out.println("2 - Update Last Name");
									System.out.println("3 - Update Phone");
									System.out.println("4 - Update Email");
									System.out.println("5 - Update Address");
									System.out.println("6 - Back");
									System.out.print("Enter your selection: ");
									menu = input.nextInt();
									input.nextLine();
									System.out.println();
									switch (menu) {
	
									case 1:
										try {
											// Update First Name
											firstName = firstNameInput(input, firstName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE proj_man SET first_name = " + "\"" + firstName + "\"" + "WHERE proj_man_id = " + "\"" +projManId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();	
	
											// 3. Execute SQL query
											myStmt.execute();									
	
											System.out.println("First Name updated.");
											printAllFromProjManTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 2:
										try {
											// Update Last Name
											lastName = lastNameInput(input, lastName);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE proj_man SET last_name = " + "\"" + lastName + "\"" + "WHERE proj_man_id = " + "\"" +projManId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();	
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Last Name updated.");
											printAllFromProjManTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 3:
										try {
											// Update Mobile Phone Number
											phone = phoneInput(input, phone);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE proj_man SET phone = " + "\"" + phone + "\"" + "WHERE proj_man_id = " + "\"" +projManId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();	
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Mobile updated.");
											printAllFromProjManTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 4:
										try {
											// Update Email
											email = emailInput(input, email);
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE proj_man SET email = " + "\"" + email + "\"" + "WHERE proj_man_id = " + "\"" +projManId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();	
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Email updated.");
											printAllFromProjManTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 5:
										try {
											// Update Address
											address = addressInput(input, address);	
	
											// 1. Get a connection to database
											myConn = DriverManager.getConnection(url, user, password);
	
											// 2. Create a statement
											sql = "UPDATE proj_man SET address = " + "\"" + address + "\"" + "WHERE proj_man_id = " + "\"" +projManId+ "\"";
											myStmt = myConn.prepareStatement(sql);
											Statement statement = myConn.createStatement();	
	
											// 3. Execute SQL query
											myStmt.execute();
	
											System.out.println("Address updated.");
											printAllFromProjManTable(statement);
											statement.close();
	
										} catch (Exception exc) {
											System.out.println("Unable to make database connection. Check login details.");
	
										} finally {
											if (myStmt != null) {
												myStmt.close();
											}
											if (myConn != null) {
												myConn.close();
											}
										}
										break;
	
									case 6:
										break;
	
									default:
										System.out.println("Invalid entry. Please try again.");
									}
								} while (menu != 6);
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 6:
							break;
	
						default:
							System.out.println("Invalid entry. Please try again.");
						}
	
					} while (menu != 6 && isNum);

					break;
	
				case 3:
					// Add a new project													
					try {		
						System.out.println("\n3. Add Project\n--------------------------------");
						// Asks user for the information required to create a new project					
						System.out.print("Project Name: ");
						projName = input.nextLine();
						System.out.print(s);
						
						// 1. Get a connection to database
						myConn = DriverManager.getConnection(url, user, password);
						
						// Checks for duplicate project name in project table
						ResultSet results1;
						String sql = "SELECT proj_name from Project";
						Statement statement = myConn.createStatement();
						results1 = statement.executeQuery(sql);	
						
						// Loops through results
						while (results1.next()) {
							if(results1.getString("proj_name").toLowerCase().equals(projName.toLowerCase())) {
								nameExists = true;
							}
						}
						
						if(nameExists) 
						{
							System.out.println("Project name exists, please try again.\n");
							nameExists = false;
							break;
						}
											
						// Checks that inputs is are not empty and validated 
						structType = structTypeInput(input, structType);					
						structAddress = structAddressInput(input, structAddress);					
						erfNum = erfNumInput(input, erfNum);					
						dueD = dueDateInput(input, dueD);					
						invTotal = invTotalInput(input, invTotal);					
										
						// User input for customer ID
						custId = custIdInput(input, custId);
						
						// Checks for duplicate customer ID in customer table
						ResultSet results2;
						sql = "SELECT cust_id from Customer";
						statement = myConn.createStatement();
						results2 = statement.executeQuery(sql);
						
						// Loops through results
						while (results2.next()) {
							if(results2.getInt("cust_id") == custId) {
								custExists = true;
							}
						}
						
						if(!custExists) 
						{
							System.out.println("Customer ID doesn't exist, please try again.\n");
							custExists = false;
							break;
						}
										
						// User input for contractor ID
						contId = contIdInput(input, contId);
						
						// Checks for duplicate contractor ID in contractor table
						ResultSet results3;
						sql = "SELECT cont_id from Contractor";
						statement = myConn.createStatement();
						results3 = statement.executeQuery(sql);
						
						// Loops through results
						while (results3.next()) {
							if(results3.getInt("cont_id") == contId) {
								contExists = true;
							}
						}
						
						if(!contExists) 
						{
							System.out.println("Contractor ID doesn't exist, try again.\n");
							contExists = false;						
							break;
						}
								
						// User input for architect ID
						archId = archIdInput(input, archId);	
						
						// Checks for duplicate architect ID in architect table
						ResultSet results4;
						sql = "SELECT arch_id from Architect";
						statement = myConn.createStatement();
						results4 = statement.executeQuery(sql);
						
						// Loops through results
						while (results4.next()) {
							if(results4.getInt("arch_id") == archId) {
								archExists = true;
							}
						}
						
						if(!archExists) 
						{
							System.out.println("Architect ID doesn't exist, try again.\n");
							archExists = false;						
							break;
						}
											
						// User input for structural engineer ID
						structEngId = structEngIdInput(input, structEngId);		
						
						// Checks for duplicate structural engineer ID in structural engineer table
						ResultSet results6;
						sql = "SELECT struct_eng_id from struct_eng";
						statement = myConn.createStatement();
						results6 = statement.executeQuery(sql);
						
						// Loops through results
						while (results6.next()) {
							if(results6.getInt("struct_eng_id") == structEngId) {
								structEngExists = true;
							}
						}
						
						if(!structEngExists) 
						{
							System.out.println("Structural Engineer ID doesn't exist, try again.\n");
							structEngExists = false;						
							break;
						}
						
						// User input for project manager ID
						projManId = projManIdInput(input, projManId);
						
						// Checks for duplicate project manager ID in project manager table
						ResultSet results5;
						sql = "SELECT proj_man_id from proj_man";
						statement = myConn.createStatement();
						results5 = statement.executeQuery(sql);
						
						// Loops through results
						while (results5.next()) {
							if(results5.getInt("proj_man_id") == projManId) {
								projManExists = true;
							}
						}
						
						if(!projManExists) 
						{
							System.out.println("Project Manager ID doesn't exist, try again.\n");
							projManExists = false;						
							break;
						}
						
						invBalance = invTotal;
															
						// Counts the rows in table for the invoice number creation			
						String query = "SELECT COUNT(*) AS \"total\" FROM Project";
						statement = myConn.createStatement();
						
						results = statement.executeQuery(query);
						results.next();
						
						// Sets the invoice number same as the proj_id
						counter = results.getInt("total");
						counter++;			
													
						// 2. Create a statement
						sql = "INSERT INTO Project "
								+ " (proj_name, struct_type, struct_add, erf_num, due_date, proj_status, inv_num, inv_total, inv_balance, cust_id, cont_id, arch_id, struct_eng_id, proj_man_id, fin_date)"
								+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
						myStmt = myConn.prepareStatement(sql);
	
						// Set parameter values
						myStmt.setString(1, projName);
						myStmt.setString(2, structType);
						myStmt.setString(3, structAddress);
						myStmt.setString(4, erfNum);
						myStmt.setString(5, dueD);
						myStmt.setString(6, projStatus);
						myStmt.setString(7, Integer.toString(counter));
						myStmt.setDouble(8, invTotal);
						myStmt.setDouble(9, invBalance);
						myStmt.setInt(10, custId);
						myStmt.setInt(11, contId);
						myStmt.setInt(12, archId);
						myStmt.setInt(13, structEngId);
						myStmt.setInt(14, projManId);
						myStmt.setString(15, finDate);
	
						// 3. Execute SQL query
						myStmt.executeUpdate();
											
						if (projName.isEmpty()) {
							// Creates a custom project name if not filled in by user
							sql = ("SELECT struct_type FROM Project WHERE proj_id =" + counter);
							statement = myConn.createStatement();
							ResultSet results7 = statement.executeQuery(sql);
							results7.next();
							String structureType = results7.getString("struct_type");
							
							// Selects the customer last name from the valid customer id and the structure type
							String lastNameQuery = ("SELECT cust.last_name FROM Project INNER JOIN Customer AS cust ON Project.cust_id = cust.cust_id WHERE proj_id =" + counter);
							statement = myConn.createStatement();
							ResultSet results8 = statement.executeQuery(lastNameQuery);
							results8.next();
							String lastName = results8.getString("last_name");
							String projectCustomName = structureType + " " + lastName ;
							projName = "\"" + projectCustomName + "\"";
										
							// Updates the table with custom project name
							sql = "UPDATE Project SET proj_name = " + projName + " WHERE proj_id = " + counter;
							statement = myConn.createStatement();
							statement.executeUpdate(sql);		
							
							// Close up our connections				
							results7.close();
							results8.close();							
						}	
													
						System.out.println("Insert complete.\n");
						System.out.println("Added to Project List\n--------------------------------");
						printAllFromProjectTable(statement);
						System.out.println();
						
						// Close up our connections
						statement.close();
						results.close();
						results1.close();
						results2.close();
						results3.close();		
						results4.close();	
						results5.close();	
						results6.close();					
	
					} catch (Exception exc) {
						System.out.println("Unable to make database connection. Check login details.");
	
					} finally {
						if (myStmt != null) {
							myStmt.close();
						}	
						if (myConn != null) {
							myConn.close();
						}
					}
					break;
	
				case 4:
					// Edit project			
					System.out.println("\n4. Edit Project\n--------------------------------");
					try {
						// Search for Project by ID or project name
						System.out.print("Enter Project ID or Name: ");
						
						String search = input.nextLine();
						int proj_id = 0;
						
						// Formats the currency
						NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
						
						// 1. Get a connection to database
						myConn = DriverManager.getConnection(url, user, password);
	
						// 2. Create a statement	
						// Search by ID or name
						if(search.matches("[0-9]+")){
							// checks if the input is a number which means the project ID was input
							proj_id = Integer.parseInt(search);
							String sql = "SELECT * FROM Project WHERE proj_id =" + proj_id;
							myStmt = myConn.prepareStatement(sql);
							usingName = false;
						}
						else {
							// checks if the input is a string which means the project name was input
							projName = search;
							String sql = "SELECT * FROM Project WHERE proj_name =" + "\"" + projName+ "\"";													
							myStmt = myConn.prepareStatement(sql);
							usingName = true;
						}	
										
						// 3. Execute SQL query
						results = myStmt.executeQuery();			
						
						// Checks if user input is in database table
						if (!results.next())
						{
							System.out.print("No record, try again\n\n");
							break;
						}
						else {
							// Prints rows
							System.out.println("ID: " + results.getInt("proj_id") + ", Name: "
							+ results.getString("proj_name") + ", Struct Type: " + results.getString("struct_type")
							+ ", Struct Add: " + results.getString("struct_add") + ", ERF: "
							+ results.getString("erf_num") + ", Due Date: " + results.getString("due_date")
							+ ", Project Status: " + results.getString("proj_status") + ", Inv Num: "
							+ results.getString("inv_num") + ", Invoice Total: " + results.getInt("inv_total")
							+ ", Invoice Balance: " + results.getInt("inv_balance") + ", Customer ID: "
							+ results.getInt("cust_id") + ", Contractor ID: " + results.getInt("cont_id")
							+ ", Architect ID: " + results.getInt("arch_id") + ", Struct Eng ID: "
							+ results.getInt("struct_eng_id") + ", Proj Man ID: " + results.getInt("proj_man_id")
							+ ", Finalised Date: " + results.getString("fin_date"));
						}
				
							// Close up our connections
							results.close();				
		
						do {
							if(!finalised) {
								System.out.println("\nUpdate Project Menu\n--------------------------------");
								// Sub menu for updating customer data after selecting ID
								System.out.println("1 - Add a Payment");
								System.out.println("2 - Update Due Date");
								System.out.println("3 - Update Project Status");
								System.out.println("4 - Update Project Name");
								System.out.println("5 - Update Structure Type");
								System.out.println("6 - Update Structure Address");
								System.out.println("7 - Update ERF");
								System.out.println("8 - Update Customer");
								System.out.println("9 - Update Contractor");
								System.out.println("10 - Update Architect");
								System.out.println("11 - Update Structural Engineer");
								System.out.println("12 - Update Project Manager");
								System.out.println("13 - Main Menu");
								System.out.print("Enter your selection: ");
								menu = input.nextInt();
								input.nextLine();
								System.out.println();
								switch (menu) {
		
								case 1:
									try {							
										// Add Payment																					
										System.out.println("Add a Payment\n--------------------------------");			
										
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);			
										
										// 2. Create a statement								
										String sql = "SELECT inv_balance FROM project WHERE proj_id = " + proj_id + " OR proj_name LIKE "+ "\"" + projName + "\"";
										Statement statement = myConn.createStatement();
										
										results = statement.executeQuery(sql);
										results.next();
										
										invBalance = results.getDouble("inv_balance");								
										
										System.out.print("Invoice Balance: " + defaultFormat.format(invBalance));								
									
										// Asks user to input a new payment amount	
										double newPayment;
										double payment;																			
										double newInvBalance;
																				
										try {
											System.out.print("\nEnter payment amount: R");
											newPayment = input.nextDouble();
											payment = newPayment;																			
											newInvBalance = invBalance - payment;
											isNum = true;		
											
										} catch (Exception e) {
											String temp = input.nextLine();
											System.out.println("Please enter a numerical value.");
											isNum = false;
											break;
										}
																
										// 3. Execute SQL query
										if (!usingName)
										{
											sql = "UPDATE project SET inv_balance = "+ newInvBalance +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE project SET inv_balance = "+ newInvBalance +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										
										// Prints out new balance
										System.out.println("New Balance: " + defaultFormat.format(newInvBalance));											
										printAllFromProjectTable(statement);
										
										// Close up our connections
										statement.close();
										results.close();						
										
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}		
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 2:
									try {
										// Update Due Date
										System.out.println("Edit Due Date\n--------------------------------");								
										dueD = dueDateInput(input, dueD);
								
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										Statement statement = myConn.createStatement();
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											String sql = "UPDATE Project SET due_date = "+ "\"" + dueD + "\"" +" WHERE proj_id = " + proj_id;											
											statement.executeUpdate(sql);
										}
										else
										{
											String sql = "UPDATE Project SET due_date = "+ "\"" + dueD + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Due Date updated.");
										printAllFromProjectTable(statement);							
										statement.close();										
										
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 3:
									// Project Status Menu
									do {
										System.out.println("Update Status\n--------------------------------");
										System.out.println("1 - Set to Finalised");
										System.out.println("2 - Back");
										System.out.print("Enter your selection: ");
										menu = input.nextInt();
										input.nextLine();
										System.out.println();
										switch (menu) {
		
										case 1:
											try {
												// Sets the currently selected project status to "Finalized" and adds date
												projStatus = "Finalised";									
		
												// Gets todays date for time stamp on finalized project
												DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
												LocalDate localDate = LocalDate.now();
												String today = dtf.format(localDate);
		
												// 1. Get a connection to database
												myConn = DriverManager.getConnection(url, user, password);
												Statement statement = myConn.createStatement();
		
												// 2. Create a statement
												if (!usingName)
												{
													String sql = "SELECT inv_balance FROM project WHERE proj_id = " + proj_id;
													statement = myConn.createStatement();
													ResultSet results5 = statement.executeQuery(sql);
													
													if (!results5.next())
													{
														System.out.print("No data");
													}
													else {
														invBalance = results5.getDouble("inv_balance");
													}
												}
												else
												{
													String sql = "SELECT inv_balance FROM project WHERE proj_name LIKE "+ "\"" + projName + "\"";
													statement = myConn.createStatement();
													ResultSet results6 = statement.executeQuery(sql);

													if (!results6.next())
													{
														System.out.print("No data");
													}
													else {
														invBalance = results6.getDouble("inv_balance");
													}											
												}
																													
												// 3. Execute SQL query
												if (!usingName)
												{
													String sql = "UPDATE project SET proj_status = "+ "\"" +  projStatus + "\"" + ", fin_date = " + "\"" + today + "\"" + " WHERE proj_id = " + proj_id;
													statement = myConn.createStatement();
													statement.executeUpdate(sql);
												}
												else
												{
													String sql = "UPDATE project SET proj_status = "+ "\"" +  projStatus + "\"" + ", fin_date = " + "\"" + today + "\"" + " WHERE proj_name = " + "\""  +projName + "\"" ;
													statement = myConn.createStatement();
													statement.executeUpdate(sql);
												}
																														
												// Generates invoice if invBalance > 0
												if (invBalance > 0) {												
													if (!usingName)
													{
														String sql = "SELECT proj.inv_num, proj.fin_date, cust.cust_id, cust.first_name, cust.last_name, cust.phone, cust.address, cust.email, proj.inv_balance, proj.inv_total FROM project as proj INNER JOIN Customer as cust ON proj.cust_id = cust.cust_id WHERE proj_id = " + "\"" + proj_id + "\"";
														statement = myConn.createStatement();
														ResultSet rs = statement.executeQuery(sql);
														rs.next();
														System.out.println("Project Finalised. Invoice Generated.\n");
														
														// Prints out invoice
														System.out.println("Invoice: INV" + rs.getInt("inv_num") 
														+ "\n--------------------------------"
														+ "\nDate: " + rs.getString("fin_date")
														+ "\nTo: " + rs.getString("first_name") + " " + rs.getString("last_name") 
														+ "\nMobile: " + rs.getString("phone")
														+ "\nEmail: " + rs.getString("email")
														+ "\nAddress: " + rs.getString("address")
														+ "\n--------------------------------"
														+ "\nTotal Owed: R" + rs.getInt("inv_balance") +"\n");													
													}
													else
													{
														String sql = "SELECT proj.inv_num, proj.fin_date, cust.cust_id, cust.first_name, cust.last_name, cust.phone, cust.address, cust.email, proj.inv_balance, proj.inv_total FROM project as proj INNER JOIN Customer as cust ON proj.cust_id = cust.cust_id WHERE proj_name = " + "\"" + projName + "\"";
														statement = myConn.createStatement();
														ResultSet rs2 = statement.executeQuery(sql);
														rs2.next();
														System.out.println("Project Finalised. Invoice Generated.\n");
														
														// Prints out invoice
														System.out.println("Invoice: INV" + rs2.getInt("inv_num") 
														+ "\n--------------------------------"
														+ "\nDate: " + rs2.getString("fin_date")
														+ "\nTo: " + rs2.getString("first_name") + " " + rs2.getString("last_name") 
														+ "\nMobile: " + rs2.getString("phone")
														+ "\nEmail: " + rs2.getString("email")
														+ "\nAddress: " + rs2.getString("address")
														+ "\n--------------------------------"
														+ "\nTotal Owed: R" + rs2.getInt("inv_balance") +"\n");	
													}	
													
												// If invBalance < 0 no invoice is generated
												} else if (invBalance <= 0) {
													System.out.println("Invoice Balance: " + defaultFormat.format(invBalance));
													System.out.println("Project Finalised. No invoice needed.\n");
												}
												
												// Goes back to main menu after finalizing a project.
												finalised = true;
												
												// Close up our connections
												statement.close();
												results.close();
		
											} catch (Exception exc) {
												System.out.println("Unable to make database connection. Check login details.");
		
											} finally {
												if (myStmt != null) {
													myStmt.close();
												}
												if (myConn != null) {
													myConn.close();
												}
											}
											break;
											
										case 2:
											break;
		
										default:
											System.out.println("Invalid entry. Please try again.");
										}
										break;
									} while (menu != 2);
									break;
		
								case 4:
									// Update Project Name
									try {
										System.out.println("Edit Project Name\n--------------------------------");	
										System.out.print("New Project Name: ");
										String newProjName = input.nextLine();									
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										Statement statement = myConn.createStatement();
										
										// Checks for duplicate project name
										ResultSet results1;
										String sql = "SELECT proj_name from Project";				
										results1 = statement.executeQuery(sql);
										String temp;
										
										while (results1.next()) {
											temp = results1.getString("proj_name").toLowerCase();
											if(temp.equals(newProjName.toLowerCase())) {
												nameExists = true;
											}
										}
										
										if(nameExists) 
										{
											System.out.println("Project name exists, try again.");
											nameExists = false;
											break;
										}										
		
										// 2. Create a statement and execute
										if (!usingName)
										{
											sql = "UPDATE Project SET proj_name = "+ "\"" + newProjName + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE Project SET proj_name = "+ "\"" + newProjName + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										
										System.out.println("Project Name updated.");
										printAllFromProjectTable(statement);
										statement.close();
										results1.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 5:
									// Update Structure Type
									try {
										System.out.println("Edit Structure Type\n--------------------------------");
										structType = structTypeInput(input, structType);
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										Statement statement = myConn.createStatement();

										// 2. Create a statement and execute
										if (!usingName)
										{
											String sql = "UPDATE Project SET struct_type = "+ "\"" + structType + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											String sql = "UPDATE Project SET struct_type = "+ "\"" + structType + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Structure Type updated.");
										printAllFromProjectTable(statement);
										statement.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 6:
									// Update Structure Address
									try {
										System.out.println("Edit Structure Address\n--------------------------------");
										structAddress = structAddressInput(input, structAddress);	
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										Statement statement = myConn.createStatement();

										// 2. Create a statement and execute
										if (!usingName)
										{
											String sql = "UPDATE Project SET struct_add = "+ "\"" + structAddress + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											String sql = "UPDATE Project SET struct_add = "+ "\"" + structAddress + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Structure Address updated.");
										printAllFromProjectTable(statement);										
										statement.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
									
								case 7:
									// Update ERF
									try {
										System.out.println("Edit ERF Number\n--------------------------------");
										erfNum = erfNumInput(input, erfNum);	
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										Statement statement = myConn.createStatement();
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											String sql = "UPDATE Project SET erf_num = "+ "\"" + erfNum + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											String sql = "UPDATE Project SET erf_num = "+ "\"" + erfNum + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("ERF updated.");
										printAllFromProjectTable(statement);
										statement.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 8:
									// Update Customer ID
									try {			
										System.out.println("Edit Customer ID\n--------------------------------");
										try {
											System.out.print("Enter New ID: ");
											int searchId = input.nextInt();							
											custId = searchId;
											isNum = true;									

										} catch (Exception e) {
											String temp = input.nextLine();
											System.out.println("Please enter a numerical value.");
											isNum = false;
											break;
										}
													
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);		
										
										// Checks for duplicate customer ID
										ResultSet results3;
										String sql = "SELECT cust_id from customer";	
										Statement statement = myConn.createStatement();
										results3 = statement.executeQuery(sql);
										
										while (results3.next()) {
											if(results3.getInt("cust_id")==custId) {
												contactExists = true;
											}
										}
										
										if(!contactExists) 
										{
											System.out.println("Customer ID doesn't exist, try again.");
											contactExists = false;
											break;
										}										
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											sql = "UPDATE Project SET cust_id = "+ "\"" + custId + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE Project SET cust_id = "+ "\"" + custId + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Customer ID updated.");
										printAllFromProjectTable(statement);
										statement.close();
										results3.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}		
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 9:
									// Update Contractor ID
									try {
										System.out.println("Edit Contractor ID\n--------------------------------");
										try {
											System.out.print("Enter New ID: ");
											int searchId = input.nextInt();							
											contId = searchId;
											isNum = true;									

										} catch (Exception e) {
											String temp = input.nextLine();
											System.out.println("Please enter a numerical value.");
											isNum = false;
											break;
										}
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										
										// Checks for contractor ID
										ResultSet results;
										String sql = "SELECT cont_id from contractor";
										Statement statement = myConn.createStatement();
										results = statement.executeQuery(sql);
										
										while (results.next()) {
											if(results.getInt("cont_id") == contId) {
												contactExists = true;
											}
										}
										
										if(!contactExists) 
										{
											System.out.println("Contractor ID doesn't exist, try again.");
											contactExists = false;
											break;
										}	
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											sql = "UPDATE Project SET cont_id = "+ "\"" + contId + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE Project SET cont_id = "+ "\"" + contId + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Contractor ID updated.");
										printAllFromProjectTable(statement);
										statement.close();
										results.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}		
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 10:
									// Update Architect ID
									try {
										System.out.println("Edit Architect ID\n--------------------------------");
										try {
											System.out.print("Enter New ID: ");
											int searchId = input.nextInt();							
											archId = searchId;
											isNum = true;									

										} catch (Exception e) {
											String temp = input.nextLine();
											System.out.println("Please enter a numerical value.");
											isNum = false;
											break;
										}
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										
										// Checks for Architect ID in table
										ResultSet results;
										String sql = "SELECT arch_id from architect";
										Statement statement = myConn.createStatement();
										results = statement.executeQuery(sql);
										
										while (results.next()) {
											if(results.getInt("arch_id") == archId) {
												contactExists = true;
											}
										}
										
										if(!contactExists) 
										{
											System.out.println("Architect ID doesn't exist, try again.");
											contactExists = false;
											break;
										}	
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											sql = "UPDATE Project SET arch_id = "+ "\"" + archId + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE Project SET arch_id = "+ "\"" + archId + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Architect ID updated.");
										printAllFromProjectTable(statement);
										statement.close();
										results.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}		
										if (myConn != null) {
											myConn.close();
										}
									}
									break;									
		
								case 11:
									// Update Structural Engineer ID		
									try {
										System.out.println("Edit Structural Engineer ID\n--------------------------------");
										try {
											System.out.print("Enter New ID: ");
											int searchId = input.nextInt();							
											structEngId = searchId;
											isNum = true;									

										} catch (Exception e) {
											String temp = input.nextLine();
											System.out.println("Please enter a numerical value.");
											isNum = false;
											break;
										}
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										
										// Checks for Structural Engineer ID
										ResultSet results;
										String sql = "SELECT struct_eng_id from struct_eng";
										Statement statement = myConn.createStatement();
										results = statement.executeQuery(sql);
										
										while (results.next()) {
											if(results.getInt("struct_eng_id") == structEngId) {
												contactExists = true;
											}
										}
										
										if(!contactExists) 
										{
											System.out.println("Structural Engineer ID doesn't exist, try again.");
											contactExists = false;
											break;
										}	
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											sql = "UPDATE Project SET struct_eng_id = "+ "\"" + structEngId + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE Project SET struct_eng_id = "+ "\"" + structEngId + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Structural Engineer ID updated.");
										printAllFromProjectTable(statement);
										statement.close();
										results.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}		
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 12:
									// Update Project Manager ID
									try {
										System.out.println("Edit Project Manager ID\n--------------------------------");
										try {
											System.out.print("Enter New ID: ");
											int searchId = input.nextInt();							
											projManId = searchId;
											isNum = true;									

										} catch (Exception e) {
											String temp = input.nextLine();
											System.out.println("Please enter a numerical value.");
											isNum = false;
											break;
										}
		
										// 1. Get a connection to database
										myConn = DriverManager.getConnection(url, user, password);
										
										// Checks for Project Manager ID
										ResultSet results;
										String sql = "SELECT proj_man_id from proj_man";
										Statement statement = myConn.createStatement();
										results = statement.executeQuery(sql);
										
										while (results.next()) {
											if(results.getInt("proj_man_id") == projManId) {
												contactExists = true;
											}
										}
										
										if(!contactExists) 
										{
											System.out.println("Project Manager ID doesn't exist, try again.");
											contactExists = false;
											break;
										}	
										
										// 2. Create a statement and execute
										if (!usingName)
										{
											sql = "UPDATE Project SET proj_man_id = "+ "\"" + projManId + "\"" +" WHERE proj_id = " + proj_id;
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
										else
										{
											sql = "UPDATE Project SET proj_man_id = "+ "\"" + projManId + "\"" +" WHERE proj_name LIKE "+ "\"" + projName + "\"";
											statement = myConn.createStatement();
											statement.executeUpdate(sql);
										}
		
										System.out.println("Project Manager ID updated.");
										printAllFromProjectTable(statement);
										statement.close();
										results.close();
		
									} catch (Exception exc) {
										System.out.println("Unable to make database connection. Check login details.");
		
									} finally {
										if (myStmt != null) {
											myStmt.close();
										}		
										if (myConn != null) {
											myConn.close();
										}
									}
									break;
		
								case 13:
									break;
		
								default:
									System.out.println("Invalid entry. Please try again.\n");
								}							
							}
							else {
								break;
							}
	
						} while (menu != 13);
						
						// Close up connections
						results.close();
							
					} catch (Exception exc) {
						System.out.println("Unable to make database connection. Check login details.");
	
					} finally {
						if (myStmt != null) {
							myStmt.close();
						}	
						if (myConn != null) {
							myConn.close();
						}
					}
					break;
	
				case 5:
					// Reports
					do {
						System.out.println("\n5. Reports\n--------------------------------");
						System.out.print("1 - All Projects\n");
						System.out.print("2 - Incomplete Projects\n");
						System.out.print("3 - Finalised Projects\n");
						System.out.print("4 - Overdue Projects\n");
						System.out.print("5 - All Customers\n");
						System.out.print("6 - All Contractors\n");
						System.out.print("7 - All Architects\n");
						System.out.print("8 - All Structural Engineers\n");
						System.out.print("9 - All Project Managers\n");
						System.out.print("10 - Main Menu\n");
						System.out.print("Enter your selection: ");
						menu = input.nextInt();
						input.nextLine();
						System.out.println();
	
						switch (menu) {
						case 1:
							try {
								// Displays All Projects
								System.out.println("All Projects\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM project";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								printsAllProjects(results);
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}
								
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 2:
							try {
								// Displays Incomplete Projects
								System.out.println("Incomplete Projects\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM project WHERE proj_status = 'In Progress'";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								printsAllProjects(results);
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}
	
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 3:
							try {
								// Displays Finalized Projects
								System.out.println("Finalised Projects\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM project WHERE proj_status = 'Finalised'";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
	
								// Checks if any data is in database table
								printsAllProjects(results);
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}
	
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 4:
							try {
								// Displays Overdue Projects
								System.out.println("Overdue Projects\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM project where proj_status = 'In Progress' AND CONCAT(substring(due_date,7, 4 ),'-', substring(due_date,4, 2 ), '-', substring(due_date,1, 2 )) < cast(CURRENT_TIMESTAMP () as date);";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
	
								// Checks if any data is in database table
								printsAllProjects(results);
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}
	
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}	
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 5:
							try {
								// Displays All Customers
								System.out.println("All Customers\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM customer";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								while (results.next()) {
									recordCount++;
									System.out.println(results.getInt("cust_id") + ", " + results.getString("first_name")
											+ " " + results.getString("last_name") + ", " + results.getString("phone")
											+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}

								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 6:
							try {
								// Displays All Contractors
								System.out.println("All Contractors\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM contractor";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								while (results.next()) {
									recordCount++;
									System.out.println(results.getInt("cont_id") + ", " + results.getString("first_name")
									+ " " + results.getString("last_name") + ", " + results.getString("phone")
									+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}
	
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 7:
							try {
								// Displays All Architects
								System.out.println("All Architects\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM architect";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								while (results.next()) {
									recordCount++;
									System.out.println(results.getInt("arch_id") + ", " + results.getString("first_name")
									+ " " + results.getString("last_name") + ", " + results.getString("phone")
									+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}						
	
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 8:
							try {
								// Displays All Structural Engineers
								System.out.println("All Structural Engineers\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM struct_eng";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								while (results.next()) {
									recordCount++;
									System.out.println(results.getInt("struct_eng_id") + ", " + results.getString("first_name")
									+ " " + results.getString("last_name") + ", " + results.getString("phone")
									+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}

								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 9:
							try {
								// Displays All Project Managers
								System.out.println("All Project Managers\n--------------------------------");
								ResultSet results;
	
								// 1. Get a connection to database
								myConn = DriverManager.getConnection(url, user, password);
	
								// 2. Create a statement
								String sql = "SELECT * FROM proj_man";
								myStmt = myConn.prepareStatement(sql);
	
								// 3. Execute SQL query
								results = myStmt.executeQuery();
								
								// Checks if any data is in database table
								while (results.next()) {
									recordCount++;
									System.out.println(results.getInt("proj_man_id") + ", " + results.getString("first_name")
									+ " " + results.getString("last_name") + ", " + results.getString("phone")
									+ ", " + results.getString("email") + ", " + results.getString("address"));
								}
								
								if (!(recordCount > 0))
								{
									System.out.print("No records\n");
									recordCount = 0;
									break;
								}
				
								// Close up our connections
								results.close();
	
							} catch (Exception exc) {
								System.out.println("Unable to make database connection. Check login details.");
	
							} finally {
								if (myStmt != null) {
									myStmt.close();
								}
								if (myConn != null) {
									myConn.close();
								}
							}
							break;
	
						case 10:
							break;
	
						default:
							System.out.println("Invalid entry. Please try again.\n");
						}
	
					} while (menu != 10);
					break;
	
				case 6:
					// Exits the program
					System.out.println("Exiting...");
					quit = true;
					break;
	
				default:
					System.out.println("Invalid entry. Please try again.\n");
				}
			
			} catch (Exception e) {
				System.out.println("Invalid entry. Please try again.\n");
				input.next();
			}

		} while (!quit);		

		if (input != null) {
			input.close();
		}		

	}

	/**
	 * Validates the input for Structural Engineer id
	 * @param input
	 * @param structEngId : user input for Structural Engineer id
	 * @return
	 */
	private static int structEngIdInput(Scanner input, int structEngId) {
		while (true) {
			try {
				System.out.print("Structural Engineer ID: ");
				structEngId = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a numerical value.");
				String s = input.nextLine();
			}
		}
		return structEngId;
	}

	/**
	 * Validates the input for Project Manager id
	 * @param input
	 * @param projManId : user input for Project Manager id
	 * @return
	 */
	private static int projManIdInput(Scanner input, int projManId) {
		while (true) {
			try {
				System.out.print("Project Manager ID: ");
				projManId = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a numerical value.");
				String s = input.nextLine();
			}
		}
		return projManId;
	}

	/**
	 * Validates the input for Architect id
	 * @param input
	 * @param archId : user input for Architect id
	 * @return
	 */
	private static int archIdInput(Scanner input, int archId) {
		while (true) {
			try {
				System.out.print("Architect ID: ");
				archId = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a numerical value.");
				String s = input.nextLine();
			}
		}
		return archId;
	}

	/**
	 * Validates the input for Contractor id
	 * @param input
	 * @param contId : user input for Contractor id
	 * @return
	 */
	private static int contIdInput(Scanner input, int contId) {
		while (true) {
			try {
				System.out.print("Contractor ID: ");
				contId = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a numerical value.");
				String s = input.nextLine();	
			}
		}
		return contId;
	}

	/**
	 * Validates the input for Customer id
	 * @param input
	 * @param custId : user input for Customer id
	 * @return
	 */
	private static int custIdInput(Scanner input, int custId) {
		while (true) {
			try {
				System.out.print("Customer ID: ");
				custId = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a numerical value.");
				String s = input.nextLine();
			}
		}
		return custId;
	}

	/**
	 * Validates invoice total input
	 * @param input
	 * @param invTotal : user input validating invoice total
	 * @return
	 */
	private static double invTotalInput(Scanner input, double invTotal) {
		while (true) {
			try {
				System.out.print("Invoice Total: R");
				invTotal = input.nextDouble();
				break;
			} catch (Exception e) {
				System.out.println("Please enter a numerical value.");
				String s = input.nextLine();
			}
		}
		return invTotal;
	}

	/**
	 * Validates date input
	 * @param input
	 * @param dueD : user input of the project due date
	 * @return
	 */
	private static String dueDateInput(Scanner input, String dueD) {
		while (true) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			System.out.print("Due Date (dd/mm/yyyy): ");
			dueD = input.nextLine();
			try {
				sdf.parse(dueD);
				break;
			} catch (Exception e) {
				System.out.println(dueD + " is not a valid Date");
			}
		}
		return dueD;
	}

	/**
	 * Validates ERF input
	 * @param input
	 * @param erfNum : user input for ERF
	 * @return
	 */
	private static String erfNumInput(Scanner input, String erfNum) {
		while (true) {
			System.out.print("ERF Number: ");
			erfNum = input.nextLine();
			if (erfNum != null && !erfNum.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return erfNum;
	}

	/**
	 * Validates structure address input
	 * @param input
	 * @param structAddress : user input for the structure address
	 * @return
	 */
	private static String structAddressInput(Scanner input, String structAddress) {
		while (true) {
			System.out.print("Structure Address: ");
			structAddress = input.nextLine();
			if (structAddress != null && !structAddress.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return structAddress;
	}

	/**
	 * Validates structure type input
	 * @param input
	 * @param structType : user input for the structure type
	 * @return
	 */
	private static String structTypeInput(Scanner input, String structType) {
		while (true) {
			System.out.print("Structure Type: ");
			structType = input.nextLine();
			if (structType != null && !structType.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return structType;
	}

	/**
	 * Validates phone input
	 * @param input
	 * @param phone : user input for mobile number
	 * @return
	 */
	private static String phoneInput(Scanner input, String phone) {
		while (true) {
			System.out.print("Mobile Number: ");
			phone = input.nextLine();
			if (isNumber(phone) && (phone.length() == 10)) {
				break;
			} else {
				System.out.println("Please enter 10 digits.");
				String s = input.nextLine();
			}
		}
		return phone;
	}

	/**
	 * Validates address input
	 * @param input
	 * @param address : user input for address
	 * @return
	 */
	private static String addressInput(Scanner input, String address) {
		while (true) {
			System.out.print("Address: ");
			address = input.nextLine();
			if (address != null && !address.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return address;
	}

	/**
	 * Validates email input
	 * @param input
	 * @param email : user input for email
	 * @return
	 */
	private static String emailInput(Scanner input, String email) {
		while (true) {
			System.out.print("Email: ");
			email = input.nextLine();
			if (email != null && !email.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return email;
	}

	/**
	 * Validates last name input
	 * @param input
	 * @param lastName : user input for last name
	 * @return
	 */
	private static String lastNameInput(Scanner input, String lastName) {
		while (true) {
			System.out.print("Last Name: ");
			lastName = input.nextLine();
			if (lastName != null && !lastName.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return lastName;
	}

	/**
	 * Validates first name input
	 * @param input
	 * @param firstName : user input for first name
	 * @return
	 */
	private static String firstNameInput(Scanner input, String firstName) {
		while (true) {
			System.out.print("First Name: ");
			firstName = input.nextLine();
			if (firstName != null && !firstName.isEmpty()) {
				break;
			} else {
				System.out.println("This is a required field.");
			}
		}
		return firstName;
	}

	/**
	 * This method checks to see if the user has input digits
	 * @param userInput : user input to check if digits
	 * @return
	 */
	static boolean isNumber(String userInput) {
		try {
			Long.parseLong(userInput);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	// SQL Statements
	/**
	 * Prints All From Customer Table 
	 * @param state : a statement object that has been connected to the database
	 * @throws SQLException : if thrown show database errors
	 */
	public static void printAllFromCustomerTable(Statement state) throws SQLException {
		ResultSet results = state
				.executeQuery("SELECT cust_id, first_name, last_name, phone, email, address FROM customer");
		while (results.next()) {
			System.out.println(results.getInt("cust_id") + ", " + results.getString("first_name") + " "
					+ results.getString("last_name") + ", " + results.getString("phone") + ", "
					+ results.getString("email") + ", " + results.getString("address"));
		}
	}

	/**
	 * Prints All From Contractor Table
	 * @param state : a statement object that has been connected to the database
	 * @throws SQLException : if thrown show database errors
	 */
	public static void printAllFromContractorTable(Statement state) throws SQLException {
		ResultSet results = state
				.executeQuery("SELECT cont_id, first_name, last_name, phone, email, address FROM contractor");
		while (results.next()) {
			System.out.println(results.getInt("cont_id") + ", " + results.getString("first_name") + " "
					+ results.getString("last_name") + ", " + results.getString("phone") + ", "
					+ results.getString("email") + ", " + results.getString("address"));
		}
	}

	/**
	 * Prints All From Architect Table
	 * @param state : a statement object that has been connected to the database
	 * @throws SQLException : if thrown show database errors
	 */
	public static void printAllFromArchitectTable(Statement state) throws SQLException {
		ResultSet results = state
				.executeQuery("SELECT arch_id, first_name, last_name, phone, email, address FROM architect");
		while (results.next()) {
			System.out.println(results.getInt("arch_id") + ", " + results.getString("first_name") + " "
					+ results.getString("last_name") + ", " + results.getString("phone") + ", "
					+ results.getString("email") + ", " + results.getString("address"));
		}
	}

	/**
	 * Prints All From Project Manager Table
	 * @param state : a statement object that has been connected to the database
	 * @throws SQLException : if thrown show database errors
	 */
	public static void printAllFromProjManTable(Statement state) throws SQLException {
		ResultSet results = state
				.executeQuery("SELECT proj_man_id, first_name, last_name, phone, email, address FROM proj_man");
		while (results.next()) {
			System.out.println(results.getInt("proj_man_id") + ", " + results.getString("first_name") + " "
					+ results.getString("last_name") + ", " + results.getString("phone") + ", "
					+ results.getString("email") + ", " + results.getString("address"));
		}
	}

	/**
	 * Prints All From Structural Engineer Table
	 * @param state : a statement object that has been connected to the database
	 * @throws SQLException : if thrown show database errors
	 */
	public static void printAllFromStructEngTable(Statement state) throws SQLException {
		ResultSet results = state
				.executeQuery("SELECT struct_eng_id, first_name, last_name, phone, email, address FROM struct_eng");
		while (results.next()) {
			System.out.println(results.getInt("struct_eng_id") + ", " + results.getString("first_name") + " "
					+ results.getString("last_name") + ", " + results.getString("phone") + ", "
					+ results.getString("email") + ", " + results.getString("address"));
		}
	}

	/**
	 * Prints All From PROJECT Table
	 * @param state : a statement object that has been connected to the database
	 * @throws SQLException : if thrown show database errors
	 */
	public static void printAllFromProjectTable(Statement state) throws SQLException {
		String sql = "SELECT proj_id, proj_name, struct_type, struct_add, erf_num, due_date, proj_status, inv_num, inv_total, inv_balance, cust_id, cont_id, arch_id, struct_eng_id, proj_man_id, fin_date FROM project";
		ResultSet results = state.executeQuery(sql);
		
		printsAllProjects(results);
	}
	
	/**
	 * Loops through and prints out each project in table
	 * @param results : results containing all project information 
	 * @throws SQLException : if thrown show database errors
	 */
	private static void printsAllProjects(ResultSet results) throws SQLException {
		while (results.next()) {
			recordCount++;
			System.out.println("ID: " + results.getInt("proj_id") + ", Name: "
					+ results.getString("proj_name") + ", Struct Type: "
					+ results.getString("struct_type") + ", Struct Add: "
					+ results.getString("struct_add") + ", ERF: " + results.getString("erf_num")
					+ ", Due Date: " + results.getString("due_date") + ", Project Status: "
					+ results.getString("proj_status") + ", Inv Num: "
					+ results.getString("inv_num") + ", Invoice Total: "
					+ results.getInt("inv_total") + ", Invoice Balance: "
					+ results.getInt("inv_balance") + ", Customer ID: " + results.getInt("cust_id")
					+ ", Contractor ID: " + results.getInt("cont_id") + ", Architect ID: "
					+ results.getInt("arch_id") + ", Struct Eng ID: "
					+ results.getInt("struct_eng_id") + ", Proj Man ID: "
					+ results.getInt("proj_man_id") + ", Finalised Date: "
					+ results.getString("fin_date"));
		}
	}
}