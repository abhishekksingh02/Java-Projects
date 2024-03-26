package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.ResultSet;

public class HospitalManagementSystem {
	
      private static final String url = "jdbc:mysql://localhost:3306/hospital";
      private static final String username = "root";
      private static final String password = "";
      
      public static void main(String[] args) {
		
    	  try
    	  {
    		  Class.forName("com.mysql.jdbc.Driver");
    	  }
    	  catch(ClassNotFoundException e)
    	  {
    		  e.printStackTrace();
    	  }
    	  Scanner scanner = new Scanner(System.in);
    	  
    	  try
    	  {
    		  Connection connection = DriverManager.getConnection(url, username, password);
    		  Patient patient = new Patient(connection, scanner);
    		  Doctor doctor = new Doctor(connection);
    		  
    		  while(true)
    		  {
    			  System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
    			  System.out.println("1. Add Patient");
    			  System.out.println("2. View Patients");
    			  System.out.println("3. View Doctors");
    			  System.out.println("4. Book Appointment");
    			  System.out.println("5. Exit");
    			  System.out.println("Enter your choice");
    			  int choice = scanner.nextInt();
    			  
    			  switch(choice)
    			  {
    			     case 1:
    			    	 
    				      // Add Patient
    			    	 patient.addPatient();
    			    	 System.out.println();
    			    	 break;
    			    	 
    			     case 2:
    			    	 
    			    	 // View Patients
    			    	 patient.viewPatient();
    			    	 System.out.println();
    			    	 break;
    			     
    			     case 3:
    			    	 
    			    	 // View Doctors
    			    	 doctor.viewDoctors();
    			    	 System.out.println();
    			    	 break;
    			    	 
    			     case 4: 
    			    	 
    			    	 // Book Appointments
    			    	 bookAppointment(patient, doctor, connection, scanner);
    			    	 System.out.println();
    			    	 break;
    			    	 
    			     case 5:
    			    	 
    			    	 // Exit
    			    	 System.out.println("Thank you for using HOSPITAL MANAGEMENT SYSTEM:)");
    			    	 return;
    			   
    			    
    			    default: 
    			    	System.out.println("Enter valid choice!!!!!!");
    			    	break;
    			    	 
    			  }
    		  }
    	  }
    	  catch(SQLException e)
    	  {
    		  e.printStackTrace();
    	  }
	}
      
      public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner)
      {
    	  System.out.println("Enter Patient Id: ");
    	  int patientId = scanner.nextInt();
    	  System.out.println("Enter Doctor Id: ");
    	  int doctorId = scanner.nextInt();
    	  System.out.println("Enter appointment date (YYYY-MM-DD): ");
    	  String appointmentDate = scanner.next();
    	  
    	  if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId))
    	  {
    		   if(checkDoctorAvailability(doctorId, appointmentDate, connection)) 
    		   {
    			   String appointmentQuery = " INSERT INTO appointments(patient_Id, doctor_Id, appointment_date ) VALUES(?, ?, ?)";
    			   
    			   try
    			   {
    				   PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
    				   preparedStatement.setInt(1, patientId);
    				   preparedStatement.setInt(2, doctorId);
    				   preparedStatement.setString(3, appointmentDate);
    				   
    				   int rowsAffected = preparedStatement.executeUpdate();
    				   if(rowsAffected>0)
    				   {
    					   System.out.println("Appointment Booked!");
    				   }
    				   else
    				   {
    					   System.out.println("Failed to book appointment!");
    				   }
    			
    			   }
    			   catch(SQLException e)
    			   {
    				   e.printStackTrace();
    			   }
    		   }
    		   else
    		   {
    			   System.out.println("Doctor not availaible on this date");
    		   }
    	  }
    	  else
    	  {
    		  System.out.println("Either Doctor or Patient doesn't exist");
    	  }

      }
      
      public static  boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection )
      {
    	  String query = "SELECT COUNT(*) FROM appointments WHERE doctor_Id = ? AND appointment_date = ?";
    	  
    	  try
    	  {
    		  PreparedStatement preparedStatement = connection.prepareStatement(query);
    		  
    		  preparedStatement.setInt(1, doctorId);
    		  preparedStatement.setString(2, appointmentDate);
    		  
    		  ResultSet resultSet = (ResultSet) preparedStatement.executeQuery();
    		  
    		  if(resultSet.next())
    		  {
    			  int count = resultSet.getInt(1);
    			  if(count == 0)
    			  {
    				  return true;
    			  }
    			  else
    			  {
    				  return false;
    			  }
    		  }
    	  }
    	  catch(SQLException e)
    	  {
    		  
    	  }
		return false;
      }
}
