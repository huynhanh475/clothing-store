/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothingstore;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class User {
    protected String full_name;
    protected Date birthday;
    protected String phone;
    protected String mail;
    
    public User(){
    }

    public User(String full_name, Date birthday, String phone, String mail) {
        this.full_name = full_name;
        this.birthday = birthday;
        this.phone = phone;
        this.mail = mail;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
