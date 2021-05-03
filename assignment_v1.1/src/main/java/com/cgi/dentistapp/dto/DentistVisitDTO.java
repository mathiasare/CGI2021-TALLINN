package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//Data transfer object to handle getting and sending data between UI and Database.
public class DentistVisitDTO {


    //UI data validation
    @Size(min = 1, max = 50)
    String dentistName;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    Date visitTime;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    Date visitHours;

    //Calendar instance to modify dates
    Calendar calendar = Calendar.getInstance();

    //Id to relate to DVE-s in database (needed in PUT and DELETE requests)
    Long id;

    // * Constructors *

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, Date visitTime, Date visitHours) {

        this.dentistName = dentistName;
        this.visitTime = visitTime;
        this.visitHours = visitHours;

    }

    public DentistVisitDTO(Long id, String dentistName, Date date) {
        this.id = id;
        this.dentistName = dentistName;
        calendar.setTime(date);
    }


    // * Methods *


    //Combines visitTime and visitHours to a single date to insert it to the database
    public Date combineDateAndHours() {
        calendar.setTimeInMillis(this.visitTime.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, this.visitHours.getHours());
        calendar.set(Calendar.MINUTE, this.visitHours.getMinutes());
        return calendar.getTime();
    }



    //Formatting of calendar content to display in the UI
    public String getFormattedDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime());
    }

    public String getFormattedTime() {
        return new SimpleDateFormat("HH:mm").format(calendar.getTime());
    }



    // * Getters and setters *
    public Date getVisitHours() {
        return visitHours;
    }

    public void setVisitHours(Date visitHours) {
        this.visitHours = visitHours;
    }


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

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }


}