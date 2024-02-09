package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) { //Model object holds data passed to the view
        model.addAttribute("columns", columnChoices); //Key value pair
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results") //handles request to results url
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;//declares arraylist to store the data
        if (searchTerm.equals("all") || searchTerm.isEmpty()){
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs"); //adds title to the model
        } else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
            model.addAttribute("title", "Jobs With " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        model.addAttribute("columns", columnChoices); //adds column choices to the model
        model.addAttribute("jobs", jobs); //adds jobs to the model

        return "search"; //referring to the TL template file
    }
}

