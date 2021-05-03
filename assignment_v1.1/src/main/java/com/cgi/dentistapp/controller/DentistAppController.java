package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.helperClasses.SearchFilter;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
//Main controller of the app
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    //Service instance for DB access
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/registrations").setViewName("registrations");
    }



    @GetMapping("/")
    //Form page
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO) {
        return "form";
    }

    //Form submit
    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        if(dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.combineDateAndHours())){
            return "redirect:/results/success";
        }else{
            return "redirect:/results/failure";
        }

    }

    //Page to dislplay successful from results
    @GetMapping("results/success")
    public String showSuccess(Model model){
        model.addAttribute("message","Registration was successful!");
        return "results";
    }
    //Page to dislplay unsuccessful from results
    @GetMapping("results/failure")
    public String showFailure(Model model){
        model.addAttribute("message","Registration failed! The doctor you selected is not available at that time.");
        return "results";
    }

    //Registrations page
    @GetMapping("/registrations")
    public String showRegistrations(SearchFilter<DentistVisitDTO> searchFilter,Model model,DentistVisitDTO dentistVisitDTO){

        List<DentistVisitDTO> registrations = dentistVisitService.findAllVisits().stream().map(x ->new DentistVisitDTO(x.getId(),x.getDentistName(),x.getDate())).collect(Collectors.toList());
        model.addAttribute("registrations",registrations);
        model.addAttribute("searchFilter",searchFilter);
        return "registrations";
    }


    //Search results page
    @PostMapping("/registrations/search/")

    public String searchResults(SearchFilter<DentistVisitDTO> searchFilter, Model model,DentistVisitDTO dentistVisitDTO){

        List<DentistVisitDTO> registrations = dentistVisitService.findAllVisits().stream().filter(searchFilter).map(x ->new DentistVisitDTO(x.getId(),x.getDentistName(),x.getDate())).collect(Collectors.toList());
        model.addAttribute("registrations",registrations);
        return "registrations";
    }


    @PutMapping("/registrations/update/{id}")
    //PUT request for selected registration
    public String changeRegistration(@PathVariable Long id, @Valid DentistVisitDTO dentistVisitDTO,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "registrations";
        }
        if(dentistVisitService.changeById(dentistVisitDTO.getId(),dentistVisitDTO.getDentistName(),dentistVisitDTO.combineDateAndHours())){
            return "redirect:/registrations";
        }else{
            return "redirect:/results/failure";
        }

    }
    //DELETE request for selected registration
    @DeleteMapping("/registrations/delete/{id}")
    public String deleteRegistration(@PathVariable Long id){
        dentistVisitService.deleteById(id);
        return "redirect:/registrations";
    }


}
