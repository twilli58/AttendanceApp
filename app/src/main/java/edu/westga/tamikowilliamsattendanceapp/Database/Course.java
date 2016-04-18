package edu.westga.tamikowilliamsattendanceapp.Database;

/**
 * Created by Miko on 4/17/2016.
 */
public class Course {

    private int _id;
    private String _name;
    private int _studentNumber;

    public Course() {

    }

    public Course(int id, String name, int studentNumber) {
        this._id = id;
        this._name = name;
        this._studentNumber = studentNumber;


    }

    public Course(String name, int studentNumber) {
        this._name = name;
        this._studentNumber = studentNumber;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_studentNumber() {
        return _studentNumber;
    }

    public void set_studentNumber(int _studentNumber) {
        this._studentNumber = _studentNumber;
    }
}
