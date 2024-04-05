package com.example.Projet3_CAR.akka;

import java.util.HashMap;
import java.util.Map;


import akka.actor.UntypedActor;


public class Reducer extends UntypedActor  {
	
	private HashMap<String,Integer> dictionnaire = new HashMap<String,Integer>();

	public void onReceive(Object message) {
		if(message instanceof GreetingMessage m) {
			String mot = m.line();
			System.out.println(mot)	;	
			System.out.println(this.getDictionnaire().containsKey(mot));
			if(this.getDictionnaire().containsKey(mot)) {
				int value = this.getDictionnaire().get(mot);
				value ++;
				this.dictionnaire.put(m.line(), value);
				System.out.println(value);
			}
			else {
				this.dictionnaire.put(mot, 1);
			}
			}
		if (message instanceof RequestMessage rm) {
			System.out.println("message reçu : "+ rm.message());
			String mot = rm.message();
			if(this.getDictionnaire().containsKey(mot)) {		
				int value = this.getDictionnaire().get(mot);
				System.out.println(value);
				getSender().tell(new GreetingMessage(Integer.toString(value)), getSelf());
			}
			getSender().tell(new GreetingMessage(Integer.toString(0)), getSelf());
		}
	}
	
	
	public HashMap<String,Integer> getDictionnaire(){
		return this.dictionnaire;
	}
}