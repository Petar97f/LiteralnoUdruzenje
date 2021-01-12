package com.example.bitcoin.controller;
import com.example.bitcoin.model.MyWallet;
import com.example.bitcoin.model.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        myWallet.send(requestDTO.getAmount(), requestDTO.getAddress());
        return "Done!";
    }

}
