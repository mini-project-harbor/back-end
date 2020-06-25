package com.enigma.pascal.api.controllers;

import com.enigma.pascal.api.models.DockModel;
import com.enigma.pascal.api.services.DockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/docks")
public class DockController {
    @Autowired
    DockService dockService;

    @GetMapping()
    public ResponseEntity<Page<DockModel>> getDocks(@RequestParam("page") int page, @RequestParam("size") int size) {
        try{
            return new ResponseEntity<>(dockService.getDocks(page,size), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDock(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(dockService.remove(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{port_id}")
    public ResponseEntity<List<DockModel>> getDockByPort(@PathVariable("port_id") Integer id){
        try{
            return new ResponseEntity<>(dockService.getDocksByPort(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }



}
