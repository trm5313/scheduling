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
public class Scheduling {
    public int scheduleYear;
    public String scheduleSemester;
    public String scheduleDay;
    public int scheduleStartTime;
    public int scheduleEndTime;
    public String scheduleOption;
    public Class scheduledClass;

    public int getScheduleYear() {
        return scheduleYear;
    }

    public void setScheduleYear(int scheduleYear) {
        this.scheduleYear = scheduleYear;
    }

    public String getScheduleSemester() {
        return scheduleSemester;
    }

    public void setScheduleSemester(String scheduleSemester) {
        this.scheduleSemester = scheduleSemester;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public int getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(int scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public int getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(int scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getScheduleOption() {
        return scheduleOption;
    }

    public void setScheduleOption(String scheduleOption) {
        this.scheduleOption = scheduleOption;
    }
    
    public void scheduleClass(Class currentClass, int scheduleYear, String scheduleSemester, String scheduleDay, int scheduleStartTime, int scheduleEndTime, String scheduleOption)
    {
        setScheduleYear(this.scheduleYear);
        setScheduleSemester(this.scheduleSemester);
        setScheduleDay(this.scheduleDay);
        setScheduleStartTime(this.scheduleStartTime);
        setScheduleEndTime(this.scheduleEndTime);
        setScheduleOption(this.scheduleOption);
        currentClass = scheduledClass;
    }//scheduleClass *use in GUI*
    
    
}
