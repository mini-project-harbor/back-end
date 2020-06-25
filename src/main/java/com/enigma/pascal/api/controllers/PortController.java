package com.enigma.pascal.api.controllers;

import com.enigma.pascal.api.dto.PortDto;
import com.enigma.pascal.api.models.DockModel;
import com.enigma.pascal.api.models.PortModel;
import com.enigma.pascal.api.services.DockService;
import com.enigma.pascal.api.services.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ports")
public class PortController {
    @Autowired
    PortService portService;

    @Autowired
    DockService dockService;

    @GetMapping
    public ResponseEntity<Page<PortModel>> getListPort(@RequestParam(name = "page") String page, @RequestParam(name = "size") String size) {
        try {
            return new ResponseEntity<>(portService.getListPort(Integer.parseInt(page), Integer.parseInt(size)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PortModel> getPort(@PathVariable(value = "id") int id) {
//        try {
//            return new ResponseEntity<>(portService.getPortDockVisible(id), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }

    @PostMapping()
    public ResponseEntity<PortModel> addPort(@RequestBody PortDto portDto) {
        try {
            return new ResponseEntity<>(portService.addPort(portDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePort(@PathVariable Integer id) {
        try {
            portService.deletePort(id);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<DockModel> nonAktifDock(@PathVariable Integer id, @PathVariable Boolean status) {
        try {
            if (status) {
                return new ResponseEntity<>(dockService.setStatusDock(id, 4), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(dockService.setStatusDock(id, 6), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
