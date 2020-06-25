package com.enigma.pascal.api.controllers;

import com.enigma.pascal.api.models.ShipModel;
import com.enigma.pascal.api.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {
    @Autowired
    ShipService shipService;

    @GetMapping
    public ResponseEntity<Page<ShipModel>> getListShip(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        try{
            return new ResponseEntity<>(shipService.getShips(page,size), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping()
    public ResponseEntity<ShipModel> addShip(@RequestBody ShipModel shipModel){
        try{
            return new ResponseEntity<>(shipService.addShip(shipModel), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable() Integer id){
        try{
            return new ResponseEntity<>(shipService.deleteShip(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("error",HttpStatus.UNAUTHORIZED);
        }
    }
}
