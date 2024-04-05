package com.example.Projet3_CAR.akka;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class Mapper extends UntypedActor  {
	
	private ArrayList<ActorRef> reducers = new ArrayList<ActorRef>(); 
	
	public Mapper(ArrayList<ActorRef> reducers) {
		this.reducers=reducers;
	}

	public void onReceive(Object message) {
		System.out.println(reducers.size());
		if(message instanceof GreetingMessage m) {
			System.out.println("Message de "+ m.line())	;	
			String[] list = m.line().split(" ");
	    	System.out.println(list.length);
	    	//ArrayList<String> list1 = new ArrayList<String>();
	    	//ArrayList<String> list2 = new ArrayList<String>();
	    	for (int i=0; i<list.length; i++) {
	    		String mot = list[i];
	    		//System.out.println(mot);
	    		//System.out.println(mot.compareTo("m"));
	    		if(mot.compareTo("m")<0) {
	    			this.reducers.get(0).tell( new GreetingMessage(mot), ActorRef.noSender());
	    			//list1.add(mot);
	    		}
	    		else {
	    			this.reducers.get(1).tell( new GreetingMessage(mot), ActorRef.noSender());
	    			//list2.add(mot);
	    		}
	    		//System.out.println(list1);
	    	}
		}
	    	
			
	}
	
	//public void addReducer(ActorRef r) {
	//	reducers.add(r);
	//}
	
	//public void addReducers(ArrayList<ActorRef> reducers2) {
	//	for (ActorRef r: reducers2) {
	//		reducers.add(r);
	//	}
	//}
	
	public ArrayList<ActorRef> getReducer(){
		return reducers;
	}
	
	
}