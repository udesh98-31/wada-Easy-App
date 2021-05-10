package com.example.wadaeasy;

public class ServiceListItems {

    String service,name,area1,area2,uid;

    public ServiceListItems() {
    }

    public ServiceListItems(String service, String name, String area1, String area2, String uid) {
        this.service = service;
        this.name = name ;
        this.area1 = area1;
        this.area2 = area2;
        this.uid = uid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
