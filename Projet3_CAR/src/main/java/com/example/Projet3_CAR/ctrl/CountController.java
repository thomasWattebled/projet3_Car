package com.example.Projet3_CAR.ctrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/akka")
public class CountController {
	
	
	@GetMapping("/count")
	public String home(Model model) {
		return "count";
	}
	
	@PostMapping("/addFile")
	public String addFile(@RequestParam String file) throws IOException {
		System.out.println(file); 
		//Paths p = Paths.get(/chemin/vers/monFichier.txt);
		File myfile = new File("./file/"+file);
		System.out.println(myfile.getAbsoluteFile());
		//System.out.println(myfile.exists());
		try {
	      FileReader fr = new FileReader(myfile);  
	      BufferedReader br = new BufferedReader(fr);  
	      StringBuffer sb = new StringBuffer();    
	      String line;
	      while((line = br.readLine()) != null)
	      {
	        // ajoute la ligne au buffer
	        sb.append(line);      
	        sb.append("\n");     
	      }
	      fr.close();      System.out.println("Contenu du fichier: ");
	      System.out.println(sb.toString()); }
		catch (FileNotFoundException e ) {
			System.out.println(e.getMessage());
		}
	        
	  
		return "redirect:/akka/count"; 
	}
	
}
