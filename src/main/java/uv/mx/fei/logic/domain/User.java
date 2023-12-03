package uv.mx.fei.logic.domain;

import java.sql.Date;

public class User {
    private int id;
    private int staffNumber;
    private String name;
    private String username;
    private String type;
    private Date joinDate;
    private Date expirationDate;
    private String academicDegree;
    private Date birthDate;
    
    public void setStaffNumber(int staffN){
        staffNumber = staffN;
    }
    
    public int getStaffNumber(){
        return staffNumber;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public String getName(){
        return name;
    }
    
    public void setJoinDate(Date jd){
        joinDate = jd;
    }
    
    public Date getJoinDate(){
        return joinDate;
    }
    
    public void setExpirationDate(Date ed){
        expirationDate = ed;
    }
    
    public Date getExpirationDate(){
        return expirationDate;
    }
    
    public void setAcademicDegree(String ad){
        academicDegree = ad;
    }
    
    public String getAcademicDegree(){
        return academicDegree;
    }
    
    public void setBirthDate(Date bd){
        birthDate = bd;
    }
    
    public Date getBirthDate(){
        return birthDate;
    }
    
    public boolean hasBlankAttributes(){
        return name.isBlank() && academicDegree.isBlank();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String un) {
        username = un;
    }
}
