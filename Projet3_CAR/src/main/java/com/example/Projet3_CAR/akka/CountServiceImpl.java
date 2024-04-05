package com.example.Projet3_CAR.akka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import scala.concurrent.duration.FiniteDuration;

@Service
public class CountServiceImpl implements CountService {
	private ActorSystem system ;
	private ActorRef mapper1;
	private ActorRef mapper2;
	private ActorRef mapper3;
	private ActorRef reducer2;
	private ActorRef reducer1;
	
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
		      }
		     fr.close();     
		     //System.out.println("Contenu du fichier: ");
		     //System.out.println(sb.toString()); 
		    	}
			catch (FileNotFoundException e ) {
				System.out.println(e.getMessage());
	}
		
	}
	
	

	@Override
	public void init() {
		system = ActorSystem.create("MyStestem");
		reducer1 = system.actorOf(Props.create(Reducer.class), "reducer1");
		reducer2 = system.actorOf(Props.create(Reducer.class), "reducer2");
		ArrayList<ActorRef> reducers = new ArrayList<ActorRef>();
		reducers.add(reducer1);
		reducers.add(reducer2);
		mapper1 = system.actorOf(Props.create(Mapper.class), "mapper1");
		mapper2 = system.actorOf(Props.create(Mapper.class), "mapper2");
		mapper3 = system.actorOf(Props.create(Mapper.class), "mapper3");
		ArrayList<ActorRef> mappers = new ArrayList<ActorRef>();
		mappers.add(mapper1); mappers.add(mapper2); mappers.add(mapper3);
		for(ActorRef m: mappers) {
			m.tell(reducer1, ActorRef.noSender());
			m.tell(reducer2, ActorRef.noSender());
		}
		
	}



	@Override
	public String findMot(String mot) {
		Inbox inbox = Inbox.create(system);
		if(mot.length()<5) {
			inbox.send(reducer1, new RequestMessage(mot));
		}
		else {
			inbox.send(reducer2, new RequestMessage(mot));
		}
		Object reply = null;
		try {
			 reply = inbox.receive(FiniteDuration.create(5, TimeUnit.SECONDS));
		}
		catch(TimeoutException e) {
			
		}
		if(reply instanceof GreetingMessage rm) {
			System.out.println("Response reçu : "+ rm.line());
			return rm.line();
		}
		return null;
	}

}
