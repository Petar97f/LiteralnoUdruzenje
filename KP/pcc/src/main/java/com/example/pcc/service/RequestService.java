package com.example.pcc.service;

import com.example.pcc.model.Request;
import com.example.pcc.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public Request findRequestById(Long id){
        return requestRepository.findRequestById(id);
    }
    public Request save(Request request){
        return requestRepository.save(request);
    }
    public List<Request> findall()
    {
        return requestRepository.findAll();
    }
}
