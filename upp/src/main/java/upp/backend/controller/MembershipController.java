package upp.backend.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import upp.backend.model.Membership;
import upp.backend.model.User;
import upp.backend.service.MembershipService;
import upp.backend.service.UserService;

@CrossOrigin("*")
@RequestMapping("")
@RestController
public class MembershipController {

	@Autowired
	 private UserService userService;
	
	@Autowired
	 private MembershipService membershipService;
	
	@PostMapping(value="/payBill/{username}", produces = "application/json")
	public @ResponseBody ResponseEntity<?> payBill(@PathVariable("username") String username) {
		HashMap<String, String> message = new HashMap<String, String>();
		System.out.println("Here is email: " + username);
		User user = userService.findByUsername(username);
		if (user == null) {
			message.put("message", "Something went wrong");
			message.put("status", "fail");

			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		Membership membership = new Membership();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -14); //2 weeks
		
		Date result = cal.getTime();
		double price = (double) 1200;
		Long id = 202L;
		membership.setId(id);
		membership.setName("payment membership");
		membership.setUser(user);
		membership.setPrice(price);
		membership.setPayedUntil(result);
		
		System.out.println("Membership" + membership);
		membershipService.save(membership);
		
		message.put("message", "payment created");
		message.put("status", "success");
		//redirect headers here
		//HttpHeaders headersRedirect = new HttpHeaders();
		//CHANGE HERE TO PAYPAL FRONT
		//headersRedirect.add("Location", "http://localhost:3000");
		//headersRedirect.add("Access-Control-Allow-Origin", "*");
		//return new ResponseEntity<byte[]>(null, headersRedirect, HttpStatus.FOUND);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
