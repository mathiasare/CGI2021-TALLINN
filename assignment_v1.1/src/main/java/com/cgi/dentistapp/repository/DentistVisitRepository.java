package com.cgi.dentistapp.repository;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
//CRUD repository to handle dentist_visit table queries
public interface DentistVisitRepository extends CrudRepository<DentistVisitEntity,Long> {

    //Defined and auto generated methods
    List<DentistVisitEntity> findAllByOrderByDateAsc();
    List<DentistVisitEntity> findByDentistNameAndDate(String dentistName, Date date);
 }
