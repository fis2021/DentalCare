package org.loose.fis.den.model;

public class Appoint {
    String pacientname;
    String doctorname;
    String operation;
    String dateandtime;

    public Appoint(){
    }
    public Appoint (String pacientname,String doctorname, String operation,String dateandtime){
        this.pacientname= pacientname;
        this.doctorname= doctorname;
        this.operation=operation;
        this.dateandtime=dateandtime;
    }

    public String getPacientname(){
        return this.pacientname;
    }
    public String getDoctorname(){
        return this.doctorname;
    }
    public String getOperation(){
        return this.operation;
    }
    public String getDateandtime(){
        return this.dateandtime;
    }
    public String appointstring(){
        return this.pacientname+this.operation+this.doctorname;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }
    public void setPacientname(String pacientname){
        this.pacientname=pacientname;
    }
    public void setDoctorname(String doctorname){
        this.doctorname=doctorname;
    }
    public void setOperation(String operation){
        this.operation=operation;
    }
}