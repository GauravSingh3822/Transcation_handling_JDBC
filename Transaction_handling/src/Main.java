import  java.sql.*;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Gaurav@123";
        String withdrawquery="update accounts set balance = balance - ? where account_number=?";
        String depositequery="update accounts set balance = balance + ? where account_number=?";

        // Load Driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Succesfully");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        // Establish Connection
        try{
            Connection con=DriverManager.getConnection(url,username,password);
            System.out.println("Connection Established Succefully");
            con.setAutoCommit(false);
            try {
                PreparedStatement withdrawStatement = con.prepareStatement(withdrawquery);
                PreparedStatement depositeStatement = con.prepareStatement(depositequery);
                withdrawStatement.setDouble(1, 500);
                withdrawStatement.setString(2, "account123");
                depositeStatement.setDouble(1, 500);
                depositeStatement.setString(2, "account456");
                withdrawStatement.executeUpdate();
                depositeStatement.executeUpdate();
                con.commit();
                System.out.println("Transaction Sucessfull !!");
            }catch (SQLException e){
                System.out.println(e.getMessage());
                con.rollback();
                System.out.println("Transcation Failed");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }

    }
}