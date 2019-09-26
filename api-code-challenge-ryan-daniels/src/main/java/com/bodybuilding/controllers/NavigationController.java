package com.bodybuilding.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodybuilding.cache.SimpleNavigationNodeRepository;
import com.bodybuilding.exception.IdNotFoundException;
import com.bodybuilding.models.NavigationNode;
import com.google.gson.Gson;


@RestController
public class NavigationController {
	
	//method to take in an ID and search navigation.json for matching node. If no ID provided, default to "root"
    @RequestMapping(path = {"/{id}", "/"})
    public NavigationNode getById(@PathVariable(name = "id", required = false) String id) {
    	
    	Gson gson = new Gson();
    	
    	//if no id is provided, default to "root"
    	final String sub = id == null ? "root" : id;
        System.out.println("ID provided as: " + sub);
    	
        //perform the search of navigation.json with the given id
    	SimpleNavigationNodeRepository sNNR = new SimpleNavigationNodeRepository();
    	NavigationNode returnedNode = sNNR.getById(sub);
    	
    	//if object returned is null, throw a 404, resource not found error
    	if(returnedNode == null){
    		throw new IdNotFoundException (sub);
    	}

    	//otherwise return the proper node
        System.out.println("For ID: " + id + ", returning: " + gson.toJson(returnedNode));
    	return returnedNode;
    }

}
