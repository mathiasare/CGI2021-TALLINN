package com.cgi.dentistapp.helperClasses;

import com.cgi.dentistapp.entity.DentistVisitEntity;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Helper class to filter search results
public class SearchFilter<T> implements Predicate<DentistVisitEntity> {
    
    public String search;




    // * Constructors *

    public SearchFilter() {

    }

    public SearchFilter(String search) {
        this.search = search;
    }

    // * Methods *

    @Override
    //Method splits the search string to keywords and checks if all keywords apply to a given dentistVisitEntity.
    public boolean test(DentistVisitEntity dentistVisitEntity) {

        boolean matches = true;
        String [] keywords = search.split(" ");
        for (String keyword : keywords) {
            Pattern p = Pattern.compile(".*"+keyword+".*");
            Matcher m = p.matcher(dentistVisitEntity.toString());
            if(!m.find()) matches = false;
        }

        return matches;
    }

    // * Getters and Setters *

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}