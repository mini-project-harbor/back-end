package com.enigma.pascal.api.repositories;

import com.enigma.pascal.api.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepo extends JpaRepository<StatusModel,Integer> {
    List<StatusModel> findAllByIdBetween(int start, int end);
}
