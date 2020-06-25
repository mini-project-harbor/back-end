package com.enigma.pascal.api.repositories;

import com.enigma.pascal.api.models.TrxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface TrxRepo extends JpaRepository<TrxModel, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM master_transaction t WHERE t.port_id = :idPort AND t.ship_id = :idShip")
    List<TrxModel> findByPortAndShip(@Param("idPort") Integer idPort, @Param("idShip") Integer idShip);

    @Query(nativeQuery = true, value = "SELECT * FROM master_transaction t WHERE t.port_id = :idPort AND t.ship_id = :idShip AND t.transaction_date = :date")
    List<TrxModel> findAllByPortAndShipAndTransactionDate(@Param("idPort") Integer idPort, @Param("idShip") Integer idShip, @Param("date") Date date);

}
