package com.enigma.pascal.api.services;

import com.enigma.pascal.api.models.StatusModel;
import com.enigma.pascal.api.repositories.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;

    public StatusModel getStatus(int id){
        return statusRepo.findById(id).get();
    }

    public List<StatusModel> getStatus(int start,int end){
        return statusRepo.findAllByIdBetween(start,end);
    }

}
