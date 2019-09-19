package com.example.catalyst;

public class College {

    private int college_code;
    private String college_name;
    private String college_website;
    private String college_location;
    private String college_cet_score;
    private String image;

    public College(int college_code, String college_name, String college_website, String college_location, String college_course, String image) {
        this.college_code = college_code;
        this.college_name = college_name;
        this.college_website = college_website;
        this.college_location = college_location;
        this.college_cet_score = college_course;
        this.image = image;
    }

    public int getCollege_code() {
        return college_code;
    }

    public String getCollege_name() {
        return college_name;
    }

    public String getCollege_website() {
        return college_website;
    }

    public String getCollege_location() {
        return college_location;
    }

    public String getCollege_cet_score() {
        return college_cet_score;
    }

    public String getImage() {
        return image;
    }
}
