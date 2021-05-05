package com.example.wadaeasy;

public class Client {

  private String Name;
  private String Location;
  private  String Category;
  private String Date;
  private Integer Phone;
  private String ServiceType;
    private String provider_no;

    public Client() {
    }

    public String getProvider_no() {
        return provider_no;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getPhone() {
        return Phone;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setProvider_no(String trim) {
    }
}
