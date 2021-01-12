package com.example.bitcoin.controller;
import com.example.bitcoin.model.MyWallet;
import com.example.bitcoin.model.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class HomeController {

    @Autowired
    private MyWallet myWallet;

    @RequestMapping
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/send")
    public String send(@RequestBody RequestDTO requestDTO) {
        System.out.println(requestDTO.toString());
        myWallet.send(requestDTO.getAmount(), requestDTO.getAddress());
        return "Done!";
    }

}
