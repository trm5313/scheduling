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
    
    protected String strGetIfhasComputers = "select hasComputers from classrooms where RoomNumber = ?";
    
    protected String strGetIfhasScience = "select hasScienceEquipment from classrooms where RoomNumber = ?";
    
    protected String strGetRoom = "select RoomNumber from classrooms where Building=?";
    
    protected String strGetBuilding = "select distinct Building from classrooms;";
    
    protected String strSetCourse = "insert into courses (ScheduleNumber, CourseID, CourseOption, ClassName) values (?,?,?,?);";
    
    protected String strSetSchedule = "insert into schedule (ScheduleNumber, TeacherID, RoomNumber, Day) values (?,?,?,?);";
    
    protected String strSetSection = "insert into section (ScheduleNumber, SectionNumber) values (?,?);";
    
    protected String strGetTeach = "select teachers.TeacherID, teachers.Fname, teachers.Lname, preferences.Course, preferences.StartTime, preferences.EndTime from teachers inner join teacher_pref on teacher_pref.TeacherID = teachers.TeacherID inner join preferences on preferences.Course = teacher_pref.Course where preferences.Course = ?;";
    
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
    
    public ArrayList<Faculty> getFacultyData(String Course)
    {
        Faculty cGet;
        ArrayList<Faculty> cResult;
        try 
        {
            preparedQuery = connectionDatabase.prepareStatement(strGetTeach);
            preparedQuery.setString(1,Course);
            resultQuery = preparedQuery.executeQuery();
            resultQuery.beforeFirst();
            cResult = new ArrayList();
            while(resultQuery.next())
            {
                cGet = new Faculty();
                cGet.setFacultyID(resultQuery.getInt(1));
                cGet.setFacultyFname(resultQuery.getString(2));
                cGet.setFacultyLname(resultQuery.getString(3));
                cGet.setFacultyClassType(resultQuery.getString(4));
                cGet.setStartTime(resultQuery.getString(5));
                cGet.setEndTime(resultQuery.getString(6));
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
    
    
    public void SetCourses (int SchedNum, String ClassName, String Option, String ID)
    {
        try
        {
            preparedQuery = connectionDatabase.prepareStatement(strSetCourse);
            preparedQuery.setInt(1, SchedNum);
            preparedQuery.setString(2, ClassName);
            preparedQuery.setString(3, Option);
            preparedQuery.setString(4, ID);
            preparedQuery.executeUpdate();
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
            preparedQuery.setString(2, ID);
            preparedQuery.setString(3, RoomNum);
            preparedQuery.setString(4, Day);
            preparedQuery.executeUpdate();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database." + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);      
        }      
    }
    
        public void SetSection (int SchedNum, int SecNum)
    {
        try
        {
            preparedQuery = connectionDatabase.prepareStatement(strSetSection);
            preparedQuery.setInt(1, SchedNum);
            preparedQuery.setInt(2, SecNum);
            preparedQuery.executeUpdate();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database." + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);      
        }      
    }
        
    public String[] getRoomData(String Building)
    {
        String[] cGet = new String[20];
        int num=0;
        try 
        {
            preparedQuery = connectionDatabase.prepareStatement(strGetRoom);
            preparedQuery.setString(1,Building);
            resultQuery = preparedQuery.executeQuery();
            resultQuery.beforeFirst();
            while(resultQuery.next())
            {
                cGet[num]=resultQuery.getString(1);
                num++;
            }
            preparedQuery.close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database. Please contact IT. " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return cGet;
    } 
        
    public String[] getBuildingData() {
        String[] cGet = new String[20];
        int num=0;
        try 
        {
            preparedQuery = connectionDatabase.prepareStatement(strGetBuilding);
            resultQuery = preparedQuery.executeQuery();
            resultQuery.beforeFirst();
            while(resultQuery.next())
            {
                cGet[num]=resultQuery.getString(1);
                num++;
            }
            preparedQuery.close();
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database. Please contact IT. " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return cGet;
    }
    
    public boolean getIfhasComputers(String Room)
    {
        boolean value=true;
        try 
        {
            preparedQuery = connectionDatabase.prepareStatement(strGetIfhasComputers);
            preparedQuery.setString(1,Room);
            resultQuery = preparedQuery.executeQuery();
            resultQuery.beforeFirst();
            
            while(resultQuery.next())
            {
             value = resultQuery.getBoolean(1);
            }
        }
                    
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database. Please contact IT. " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return value;
    }
    
        public boolean getIfhasScience(String Room)
    {
        boolean value=true;
        try 
        {
            preparedQuery = connectionDatabase.prepareStatement(strGetIfhasScience);
            preparedQuery.setString(1,Room);
            resultQuery = preparedQuery.executeQuery();
            resultQuery.beforeFirst();
            
            while(resultQuery.next())
            {
             value = resultQuery.getBoolean(1);
            }
        }
                    
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, "Error reading database. Please contact IT. " + ex.getMessage(), ex.getClass().toString(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return value;
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
