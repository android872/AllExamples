package com.example.networks.allexamples;

/**
 * Created by Networks on 6/17/2016.
 */
public class Course {



    private int courseNumber = 10101;
    private String courseTitle = "Implementing Android First App";
    private String description = "As the first course in the Android Developer Nanodegree, " +
            "Developing Android Apps is the foundation of our advanced Android curriculum. " +
            "This course blends theory and practice to help you build great apps the right way." +
            " In this course, you'll work with instructors step-by-step to build a " +
            "cloud-connected Android app, and learn best practices of mobile development, " +
            "and Android development in particular.";

    private  double credits = 3;

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


}
