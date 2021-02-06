package upp.backend.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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


import upp.backend.dto.MembershipDTO;
import upp.backend.model.Membership;
import upp.backend.model.User;
import upp.backend.service.MembershipService;
import upp.backend.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/membership")
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
		double price = (double) 12;
		if (user == null) {
			message.put("message", "Something went wrong");
			message.put("status", "fail");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		
		Membership membership = membershipService.findByUser(user);
		if (membership != null) {
			if (membership.isActive()) {
				message.put("message", "Membership active");
				message.put("status", "error");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
			//add usernmae and add procesInstance Id
			String url2 = "http://localhost:8085/make/payment?sum=" + price+"&username="+user.getUsername()+"&processInstanceId="+membership.getProcessInstanceId();
			
			message.put("message", "Membership payment");
			message.put("status", "success");
			message.put("data", url2);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
		//Membership membership = new Membership();
		//Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -14); //2 weeks
		
		//Date result = cal.getTime();
		
		//Long id = 202L;
		//membership.setId(id);
		//membership.setName("payment membership");
		//membership.setUser(user);
		//membership.setPrice(price);
		//membership.setPayedUntil(result);
		
		//membershipService.save(membership);
		
		message.put("message", "payment created");
		message.put("status", "success");
		//redirect headers here
		//HttpHeaders headersRedirect = new HttpHeaders();
		//CHANGE HERE TO PAYPAL FRONT

		//headersRedirect.add("Location", url);
		//headersRedirect.add("Access-Control-Allow-Origin", "*");
		//return new ResponseEntity<byte[]>(null, headersRedirect, HttpStatus.FOUND);
	
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/getMembership/{username}")
    public ResponseEntity<?> getMembership(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
		Membership membership = membershipService.findByUser(user);
		HashMap<String, String> message = new HashMap<String, String>();
		if (membership == null) {
			//za sada ovde posle ce ici prilikom registracije kor
			Membership membership2 = new Membership();
			membership = membership2;
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
			membership.setActive(false);
			
			System.out.println("Membership" + membership);
			membershipService.save(membership);
		}
		
		MembershipDTO membershipDTO = membershipService.convertToDTO(membership);
		
		
		return new ResponseEntity<>(membershipDTO, HttpStatus.OK);
    }

}
