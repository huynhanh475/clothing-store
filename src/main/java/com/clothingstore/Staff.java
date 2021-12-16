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
public class Staff extends User {
    protected int id;
    protected Date date_started;
    protected int salary;

    public Staff(int id, String full_name, Date birthday, String phone, String mail, Date date_started, int salary) {
        super(full_name, birthday, phone, mail);
        this.id = id;
        this.date_started = date_started;
        this.salary = salary;
    }
    
    public Staff(String full_name, Date birthday, String phone, String mail, Date date_started, int salary) {
        super(full_name, birthday, phone, mail);
        this.date_started = date_started;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_started() {
        return date_started;
    }

    public void setDate_started(Date date_started) {
        this.date_started = date_started;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
