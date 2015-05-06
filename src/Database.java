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
    
    protected String strGetTeach = "select teacherID, Fname, Lname from Faculty";//needs to gather preferences also, too tired for inner join logic
    
    protected String strGetClassrooms = "select roomSize, roomNumber, hasComputers, hasLabEquipment from classrooms";
    
    protected String strGetTimeslots = "select TID, StartTime, EndTime, Days from timeslot";
    
    
    
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
}
