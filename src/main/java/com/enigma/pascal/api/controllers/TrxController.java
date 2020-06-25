package com.enigma.pascal.api.controllers;

import com.enigma.pascal.api.dto.TransactionDto;
import com.enigma.pascal.api.models.PortModel;
import com.enigma.pascal.api.models.ShipModel;
import com.enigma.pascal.api.models.StatusModel;
import com.enigma.pascal.api.models.TrxModel;
import com.enigma.pascal.api.repositories.TrxRepo;
import com.enigma.pascal.api.services.PortService;
import com.enigma.pascal.api.services.ShipService;
import com.enigma.pascal.api.services.StatusService;
import com.enigma.pascal.api.services.TrxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
@RequestMapping("/transactions")
public class TrxController {
    @Autowired
    TrxService trxService;

    @Autowired
    ShipService shipService;

    @Autowired
    PortService portService;

    @Autowired
    StatusService statusService;

    @GetMapping
    public ResponseEntity<Page<TrxModel>> getTransactions(@RequestParam(name = "page") String page, @RequestParam(name = "size") String size) {
        try {
            return new ResponseEntity<>(trxService.getTransactions(Integer.parseInt(page), Integer.parseInt(size)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping
    public ResponseEntity<TrxModel> addTransaction(@RequestBody TransactionDto transactionDto) {
        try {

            return new ResponseEntity<>(trxService.addTransaction(transactionDto), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> getHarbourReport() {
        byte[] report = trxService.doGetReport();
        if (report != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("inline", "harbour.pdf");
            headers.add("reportName", "harbour.pdf");
            return ResponseEntity
                    .ok()
                    .contentLength(report.length)
                    .headers(headers)
                    .body(report);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
