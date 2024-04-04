package com.example.Projet3_CAR.akka;

import akka.actor.UntypedActor;

public class Mapper extends UntypedActor  {

	public void onReceive(Object message) {
		if(message instanceof GreetingMessage m) {
			System.out.println("Message de "+ m.line())	;	
			}
	}
}