/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

/**
 *
 * @author Zippy Speed
 */
public class Faculty {
    public int facultyID;
    public String facultyFname;
    public String facultyLname;
    public String startTime;
    public String endTime;
    public String facultyClassType;

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public String getFacultyFname() {
        return facultyFname;
    }

    public void setFacultyFname(String facultyFname) {
        this.facultyFname = facultyFname;
    }

    public String getFacultyLname() {
        return facultyLname;
    }

    public void setFacultyLname(String facultyLname) {
        this.facultyLname = facultyLname;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFacultyClassType() {
        return facultyClassType;
    }

    public void setFacultyClassType(String facultyClassType) {
        this.facultyClassType = facultyClassType;
    }
    
    
}
