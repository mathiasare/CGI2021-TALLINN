package com.cgi.dentistapp.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

@Entity
@Table(name = "dentist_visit")
//Data object to store visits
//Data object to persist and access data in the H2 database
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String dentistName;

    private Date date;


    public DentistVisitEntity() {
    }

    public DentistVisitEntity (String dentistName, Date date) {
        this.dentistName = dentistName;
        this.date = date;
    }

// * Getters and Setters *

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return dentistName+" "+sdf.format(date);
    }
}



