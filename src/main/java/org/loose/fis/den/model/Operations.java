package org.loose.fis.den.model;

import org.dizitart.no2.RemoveOptions;
import org.dizitart.no2.objects.Id;

public class Operations extends RemoveOptions {
    @Id
    private String doctorname;
    private String operation;

    public Operations(){
    }
    public Operations(String doctorname, String operation){
        this.doctorname= doctorname;
        this.operation=operation;
    }

    public String getDoctorname(){
        return this.doctorname;
    }
    public String getOperation(){
        return this.operation;
    }

    public String operationstring(){
        return this.operation+this.doctorname;
    }

    public void setDoctorname(String doctorname){
        this.doctorname=doctorname;
    }
    public void setOperation(String operation){
        this.operation=operation;
    }
}