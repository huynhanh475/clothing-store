package com.clothingstore.entity;

import java.sql.Date;

public class ImportLine extends ActionLine {
    private Date date;
    private Staff performer;

    public ImportLine(int id, int quantity, Product product, Date date, Staff performer) {
        super(id, quantity, product);
        this.date = date;
        this.performer = performer;
    }

    public ImportLine(Date date, Staff performer, int quantity, Product product) {
        super(quantity, product);
        this.date = date;
        this.performer = performer;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Staff getPerformer() {
        return performer;
    }

    public void setPerformer(Staff performer) {
        this.performer = performer;
    }

}