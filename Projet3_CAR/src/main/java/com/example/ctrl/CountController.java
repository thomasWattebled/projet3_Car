package com.example.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CountController {
	
	
	@GetMapping("/count")
	public String home(Model model) {
		return "count";
	}
}
