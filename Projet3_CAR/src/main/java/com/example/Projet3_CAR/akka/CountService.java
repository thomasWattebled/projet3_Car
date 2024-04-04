package com.example.Projet3_CAR.akka;

import java.io.File;
import java.io.IOException;

public interface CountService {
	
	public void readfile(File file) throws IOException;

	public void init();

}
