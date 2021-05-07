package com.example.wadaeasy;

public class Appointment {

    private String name;
    private Integer contact;
    private String email;
    private String provider_no;
    private String location;
    private String Date;
    private String time;
    private String Remark;
    private String ApoNo;
    private String Status;



    public Appointment(String name, Integer contact, String email, String provider_no, String ApoNo, String Status) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.provider_no = provider_no;
        this.ApoNo=ApoNo;
        this.Status=Status;
    }

    public Appointment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider_no() {
        return provider_no;
    }

    public void setProvider_no(String provider_no) {
        this.provider_no = provider_no;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }



    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getApoNo() {
        return ApoNo;
    }

    public void setApoNo(String apoNo) {
        ApoNo = apoNo;
    }


}
