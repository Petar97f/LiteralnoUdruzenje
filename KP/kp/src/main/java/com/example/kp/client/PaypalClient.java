package com.example.kp.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "paypal")
public interface PaypalClient {
	 @PostMapping(value = "/make/payment")
	    public Map<String, Object> makePayment(@RequestParam("sum") String sum);
	 @PostMapping(value = "/complete/payment")
	    public Map<String, Object> completePayment(HttpServletRequest request);
}
