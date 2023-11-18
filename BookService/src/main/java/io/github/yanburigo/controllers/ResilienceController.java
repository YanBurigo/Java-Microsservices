package io.github.yanburigo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("book-service")
public class ResilienceController {

	private Logger logger = LoggerFactory.getLogger(ResilienceController.class);
	
	@GetMapping("/retry-example")
	@Retry(name = "retry-custom", fallbackMethod = "fallbackMethod")
	public String retryExample() {
		logger.info("Request to retry is received!");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		return response.getBody();
	}
	
	@GetMapping("/circuit-breaker-example")
	@CircuitBreaker(name = "default", fallbackMethod = "fallbackCircuitBreakerMethod")
	public String circuitBreakerExample() {
		logger.info("Request to circuit breaker is received!");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		return response.getBody();
	}
	
	@GetMapping("/rate-limit-example")
	@RateLimiter(name = "default")
	public String rateLimitExample() {
		return "Foo-Bar";
	}
	
	@GetMapping("/bulk-head-example")
	@Bulkhead(name = "default")
	public String bulkHeadExample() {
		return "Foo-Bar";
	}
	
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod!!!";
	}
	
	public String fallbackCircuitBreakerMethod(Exception ex) {
		return "fallbackCircuitBreakerMethod!!!";
	}
}
