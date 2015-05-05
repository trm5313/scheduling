/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Zippy Speed
 */
public class Database {
    protected Connection connectionDatabase;
    protected PreparedStatement preparedQuery;
    protected ResultSet resultQuery;
    
    protected String strGetTeach = "select teacherID, Fname, Lname from Faculty";//needs to gather preferences also, too tired for inner join logic
    
    protected String strGetClassrooms = "select roomSize, roomNumber, hasComputers, hasLabEquipment from classrooms";
    
    protected String strGetTimeslots = "select TID, StartTime, EndTime, Days from timeslot";
    
    
    
    public Connection connect()
    { 
            try 
            { 
                Class.forName("com.mysql.jdbc.Driver");
                connectionDatabase = DriverManager.getConnection("jdbc:mysql://istdata.bk.psu.edu:3306/trm5313?user=dwk5369&password=berks2194");
                return connectionDatabase;
            } 
            catch (ClassNotFoundException|SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Error reading database." + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
                return null;
            } 

    }// connect
    
    public ArrayList<Faculty> getFacultyData()
    {
        Faculty cGet;
        ArrayList<Faculty> cResult;
        try 
        {
            preparedQuery = connectionDatabase.prepareStatement(strGetTeach);
            resultQuery = preparedQuery.executeQuery();
            resultQuery.beforeFirst();
            cResult = new ArrayList();
            while(resultQuery.next())
            {
                cGet = new Faculty();
                cGet.setFacultyID(resultQuery.getInt(1));
                cGet.setFacultyFname(resultQuery.getString(2));
                cGet.setFacultyLname(resultQuery.getString(3));
                cGet.setStartTime(resultQuery.getString(4));
                cGet.setEndTime(resultQuery.getString(5));
                cGet.setFacultyClassType(resultQuery.getString(6));
                cResult.add(cGet);
            }
            preparedQuery.close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database. Please contact IT. " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return cResult;
    }
    
    public ArrayList<Classrooms> getClassroomData()
    {
        ArrayList<Classrooms> classroomsList;
        return classroomsList; //query for all classrooms? I think all classrooms can be for any class
    }
    
    public ArrayList<Classes> getClassData()
    {
        ArrayList<Classes> classList;
        return classList; //query for all of selected option classes
    }
    
    public void sendData()
    {
        //might be a way to send all data changed client side to server all at once...not sure.
    }
}
