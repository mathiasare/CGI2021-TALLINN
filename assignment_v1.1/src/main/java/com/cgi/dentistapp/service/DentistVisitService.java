package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    //Crud repository instance
    DentistVisitRepository repo;

    //Add DVE to database
    public boolean addVisit(String dentistName, Date visitTime) {

        if(repo.findByDentistNameAndDate(dentistName,visitTime).isEmpty()){
            repo.save(new DentistVisitEntity(dentistName,visitTime));
            return true;
        }
        return false;
    }

    //Find all DVE-s from database
    public List<DentistVisitEntity> findAllVisits(){
        List<DentistVisitEntity> res = new ArrayList<>();
        Iterable<DentistVisitEntity>  it = repo.findAllByOrderByDateAsc(); //Ordered by Date for more convenient results
        it.forEach(res::add);
        return res;
    }

    //Delete DVE by id
    public void deleteById(long id){
        repo.delete(id);
    }


    //Find DVE by ID and change its attribute values
    public boolean changeById(long id,String dentistName,Date visitTime){

        if(repo.findByDentistNameAndDate(dentistName,visitTime).isEmpty()){
            DentistVisitEntity dve =   repo.findOne(id);
            dve.setDentistName(dentistName);
            dve.setDate(visitTime);
            repo.save(dve);
            return true;
        }
        return false;

    }
}
