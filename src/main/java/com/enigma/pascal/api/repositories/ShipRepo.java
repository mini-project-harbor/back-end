package com.enigma.pascal.api.repositories;

import com.enigma.pascal.api.models.ShipModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepo extends JpaRepository<ShipModel,Integer> {

}
