package com.example.Projet3_CAR.ctrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Projet3_CAR.akka.CountService;
import com.example.Projet3_CAR.akka.GreetingMessage;
import com.example.Projet3_CAR.akka.Mapper;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


@Controller
@RequestMapping("/akka")
public class CountController {
	
	@Autowired
	private CountService countservice;
	
	
	@GetMapping("/count")
	public String home(Model model) {
		return "count";
	}
	
	@PostMapping("/addFile")
	public String addFile(@RequestParam String file) throws IOException {
		System.out.println(file); 
		File myfile = new File("./file/"+file);
		System.out.println(myfile.getAbsoluteFile());
		countservice.readfile(myfile);
	        
	  
		return "redirect:/akka/count"; 
	}
	
}
