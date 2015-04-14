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
public class Classrooms {
    public int roomSize;
    public boolean roomHasComputers;
    public int roomNumber;
    public boolean roomAvailable;
    public boolean isMajorClass;
    public String creditType;

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public boolean isRoomHasComputers() {
        return roomHasComputers;
    }

    public void setRoomHasComputers(boolean roomHasComputers) {
        this.roomHasComputers = roomHasComputers;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(boolean roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public boolean isIsMajorClass() {
        return isMajorClass;
    }

    public void setIsMajorClass(boolean isMajorClass) {
        this.isMajorClass = isMajorClass;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }
    
    
    
}
