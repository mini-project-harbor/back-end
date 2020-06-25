package com.enigma.pascal.api.repositories;

import com.enigma.pascal.api.models.DockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DockRepo extends JpaRepository<DockModel,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM master_dock WHERE status_id = 4 AND port_id = :id")
    List<DockModel> findByStatusAndPortModel(@Param("id") int id);

    @Query(nativeQuery = true,value = "SELECT * FROM master_dock WHERE ship_id = :shipId")
    List<DockModel> findByShip(@Param("shipId") Integer shipId);

    @Query(nativeQuery = true,value = "SELECT * FROM master_dock WHERE port_id = :id")
    List<DockModel> findByPortModel(Integer id);

}