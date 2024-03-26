package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Doctor {
	 private Connection connection;
//     private Scanner scanner;
     
     public Doctor(Connection connection)
     {
    	 this.connection = connection;
//    	 this.scanner = scanner;
    	 
     }
     
    
     
     public void viewDoctors()
     {
    	 String query = "Select * from doctors";
    	 
    	 
    	 Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet set =stmt.executeQuery(query);
			
//			System.out.println("Doctor: "); 
//			System.out.println("+------------+----------------------------+------------+-------------+");
//			System.out.println("|Doctors Id  |Name                        |Specialization            |");
//			System.out.println("+------------+----------------------------+------------+-------------+");
//			
			//System.out.println("\t ID \t NAME \t SURNAME");
			System.out.println("Doctor: "); 
			System.out.println("+------------+----------------------------+------------+-------------+");
			System.out.println("|Doctors Id  |Name                        |Specialization            |");
			while(set.next())
			{
				
				int id = set.getInt(1);
				String name = set.getString(2);
				String specialization = set.getString(3);
				
//				System.out.println(id, name, specialization);
				
				
				
				System.out.println("+------------+----------------------------+------------+-------------+");
				  			
				
				
				System.out.println("\t"+id+" \t "+name+" \t "+specialization);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
    	 
			/*
			 * try { PreparedStatement preparedStatement =
			 * connection.prepareStatement(query);
			 * 
			 * ResultSet resultSet = preparedStatement.executeQuery();
			 * System.out.println("Patients: "); 
			 * System.out.println(
			 * "+------------+----------------------------+------------+-------------+");
			 * System.out.
			 * println("|Patient Id |Name                        |Age         |Gender       |"
			 * ); System.out.println(
			 * "+------------+----------------------------+------------+-------------+");
			 * 
			 * while(resultSet.next()) { //System.out.println("a"); int id =
			 * resultSet.getInt("id"); String name = resultSet.getString("name"); // int age
			 * = resultSet.getInt("age"); String gender =
			 * resultSet.getString("specialization"); System.out.printf("\n", id, name,
			 * gender); System.out.println(
			 * "+------------+----------------------------+------------+-------------+");
			 * 
			 * } } catch(Exception e) { e.printStackTrace(); }
			 */
     }
     
     public boolean getDoctorById(int id)
     {
    	 String query = "SELECT * FROM doctors WHERE id = ?";
    	 
    	 try
    	 {
    		 PreparedStatement preparedStatement = connection.prepareStatement(query);
    		 preparedStatement.setInt(1, id);
    		 ResultSet resultset = (ResultSet) preparedStatement.executeQuery();
    		 
    		 if(resultset.next())
    		 {
    			 return true;
    		 }
    		 else
    		 {
    			 return false;
    		 }
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
		return false;
     }
}
