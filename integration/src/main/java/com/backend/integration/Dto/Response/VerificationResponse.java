package com.backend.integration.Dto.Response;

public class VerificationResponse {
    private String course_title;

    private String serial_no;

    private String firstName;

    private String lastName;

    public VerificationResponse(
        String serial_no,
        String course_title,
        String firstName,
        String lastName
    ) {
        this.serial_no = serial_no;
        this.course_title = course_title;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getCourse_title() {
        return this.course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getSerial_no() {
        return this.serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
