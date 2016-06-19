package com.example.networks.allexamples;

/**
 * Created by Networks on 6/17/2016.
 */
public class Course {

    private int courseNumber;
    private String courseTitle;
    private String description;
    private double credits;

    public Course(int courseNumber, String courseTitle, String description, double credits) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.description = description;
        this.credits = credits;
    }




    public int getCourseNumber() {
        return courseNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getDescription() {
        return description;
    }

    public double getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return courseTitle;
    }
}
