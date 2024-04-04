package com.example.Projet3_CAR.akka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Service
public class CountServiceImpl implements CountService {
	private ActorSystem system ;
	private ActorRef mapper1;
	private ActorRef mapper2;
	private ActorRef mapper3;
	
	public void readfile(File file) throws IOException {
		try {
		      FileReader fr = new FileReader(file);  
		      BufferedReader br = new BufferedReader(fr);  
		      StringBuffer sb = new StringBuffer();    
		      String line;
		      int compteur =0;
		      while((line = br.readLine()) != null){
		    	compteur ++;
		    	System.out.println(compteur);
		       //  ajoute la ligne au buffer
		      	//line = br.readLine();
		      	//System.out.println(line);
		      	if (compteur ==1) {
		    	mapper1.tell( new GreetingMessage(line), ActorRef.noSender());
		      	}
		      	if (compteur ==2) {
			    	mapper2.tell( new GreetingMessage(line), ActorRef.noSender());
			      	}
		      	
		      	if (compteur ==3) {
		      		mapper3.tell( new GreetingMessage(line), ActorRef.noSender());
		      		compteur =0 ;
		      	}
		    	//String[] list = line.split(" ");
		    	//System.out.println(list.length);
		    	//ArrayList<String> list1 = new ArrayList<String>();
		    	//ArrayList<String> list2 = new ArrayList<String>();
		    	//for (int i=0; i<list.length; i++) {
		  		//String mot = list[i];
		    		//System.out.println(mot);
		    		//System.out.println(mot.compareTo("m"));
		    		//if(mot.compareTo("m")<0) {
		    		//	list1.add(mot);
		    		//}
		    		//else {
		    		//	list2.add(mot);
		    		//}
		    	//}
		    	//for (String i : list1 ) {
		    	//	//System.out.println(i);
		    	//}
		    	
		        //sb.append(line);      
		       // sb.append("\n");     
		      }
		      fr.close();     
		     System.out.println("Contenu du fichier: ");
		     System.out.println(sb.toString()); 
		    	}
			catch (FileNotFoundException e ) {
				System.out.println(e.getMessage());
	}
		
	}

	@Override
	public void init() {
		system = ActorSystem.create("MyStestem");
		mapper1 = system.actorOf(Props.create(Mapper.class), "mapper1");
		mapper2 = system.actorOf(Props.create(Mapper.class), "mapper2");
		mapper3 = system.actorOf(Props.create(Mapper.class), "mapper3");
		
	}

}
