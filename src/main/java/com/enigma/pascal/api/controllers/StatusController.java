package com.enigma.pascal.api.controllers;

import com.enigma.pascal.api.models.StatusModel;
import com.enigma.pascal.api.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping
    public ResponseEntity<List<StatusModel>> getStatusShip(){
        try {
            return new ResponseEntity<>(statusService.getStatus(1,3), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/dock")
    public ResponseEntity<List<StatusModel>> getStatusDock(){
        try {
            return new ResponseEntity<>(statusService.getStatus(4,6), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
