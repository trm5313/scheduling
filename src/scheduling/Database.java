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
    
    protected String strSetCourse = "insert into courses (ScheduleNumber, CourseID, CourseOption, ClassName) values (?,?,?,?);";
    
    protected String strSetSchedule = "insert into schedule (ScheduleNumber, TeacherID, RoomNumber, Day) values (?,?,?,?);";
    
    protected String strSetSection = "insert into section (ScheduleNumber, SectionNumber) values (?,?);";
    
    protected String strGetTeach = "select teachers.TeacherID, teachers.Fname, teachers.Lname, preferences.Course, preferences.StartTime, preferences.EndTime from teachers inner join teacher_pref on teacher_pref.TeacherID = teachers.TeacherID inner join preferences on preferences.Course = teacher_pref.Course where preferences.Course = 'IST';";
    
    protected String strGetClassrooms = "select roomSize, roomNumber, hasComputers, hasLabEquipment from classrooms";
    
    protected String strGetTimeslots = "select TID, StartTime, EndTime, Days from timeslot";
    
    protected String wtfisthisshit = "workdammit";
    
    
    
    public Connection connect()
    { 
            try 
            { 
                Class.forName("com.mysql.jdbc.Driver");
                connectionDatabase = DriverManager.getConnection("jdbc:mysql://istdata.bk.psu.edu:3306/trm5313?user=trm5313&password=berks2194");
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
    
    
    public void SetCourses (int SchedNum, int ID, String Option, String ClassName)
    {
        try
        {
            preparedQuery = connectionDatabase.prepareStatement(strSetCourse);
            preparedQuery.setInt(1, SchedNum);
            preparedQuery.setInt(1, ID);
            preparedQuery.setString(3, Option);
            preparedQuery.setString(4, ClassName);
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database." + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);      
        }      
    }
    
    public void SetSchedule (int SchedNum, String ID, String RoomNum, String Day)
    {
        try
        {
            preparedQuery = connectionDatabase.prepareStatement(strSetSchedule);
            preparedQuery.setInt(1, SchedNum);
            preparedQuery.setString(1, ID);
            preparedQuery.setString(3, RoomNum);
            preparedQuery.setString(4, Day);
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database." + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);      
        }      
    }
    
        public void SetSection (int SchedNum, String SecNum)
    {
        try
        {
            preparedQuery = connectionDatabase.prepareStatement(strSetSection);
            preparedQuery.setInt(1, SchedNum);
            preparedQuery.setString(1, SecNum);
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database." + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);      
        }      
    }
    
    public void sendData()
    {
        //might be a way to send all data changed client side to server all at once...not sure.
    }
    
    public void disconnect()
    {
        try 
        {
            connectionDatabase.close();
        } 
        catch (SQLException ex) 
        {
            //handle
        }
    }// disconnect
}
