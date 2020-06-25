package com.enigma.pascal.api.services;

import com.enigma.pascal.api.models.ShipModel;
import com.enigma.pascal.api.repositories.ShipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {
    @Autowired
    ShipRepo shipRepo;

    public Page<ShipModel> getShips(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        return shipRepo.findAll(pageable);
    }

    public ShipModel getShip(int id) {
        return shipRepo.findById(id).get();
    }

    public ShipModel addShip(ShipModel shipModel) {
        return shipRepo.save(shipModel);
    }

    public String deleteShip(Integer id) {
        if (this.getShip(id) != null) {
            shipRepo.delete(this.getShip(id));
            return "success";
        } else {
            return "error";
        }
    }
}
