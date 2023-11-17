package io.github.yanburigo.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.yanburigo.configuration.GreetingConfiguration;
import io.github.yanburigo.modal.Greeting;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private GreetingConfiguration _configuration;
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name",defaultValue = "") String name) {
		if(name.isEmpty()) {
			name = _configuration.getDefaultValue();
		}
		return new Greeting(counter.incrementAndGet(),String.format(template, _configuration.getGreeting(), name));
	}
}
