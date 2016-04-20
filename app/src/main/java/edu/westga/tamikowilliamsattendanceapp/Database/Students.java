package edu.westga.tamikowilliamsattendanceapp.Database;

/**
 * Created by Miko on 4/17/2016.
 */
public class Students {

    private int _id;
    private String _lastName;
    private String _firstName;
    private int _courseID;

    public Students() {

    }


    public Students(int id, String lastName, String firstName, int course) {
        this._id = id;
        this._lastName = lastName;
        this._firstName = firstName;
        this._courseID = course;

    }

    public Students(String lastName, String firstName, int course) {
        this._lastName = lastName;
        this._firstName = firstName;
        this._courseID = course;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public int get_courseID() {
        return _courseID;
    }

    public void set_courseID(int _courseID) {
        this._courseID = _courseID;
    }
}
