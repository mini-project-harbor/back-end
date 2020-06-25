package com.enigma.pascal.api.services;

import com.enigma.pascal.api.models.DockModel;
import com.enigma.pascal.api.repositories.DockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockService {
    @Autowired
    DockRepo dockRepo;

    @Autowired
    StatusService statusService;

    public Page<DockModel> getDocks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dockRepo.findAll(pageable);
    }

    public List<DockModel> getDocksByPort(Integer id){
        return dockRepo.findByPortModel(id);
    }

    public List<DockModel> getDocksAvailable(int id) {
        return dockRepo.findByStatusAndPortModel(id);
    }

    public DockModel getDock(int code) {
        return dockRepo.findById(code).get();
    }

    public DockModel addDock(DockModel dockModel) {
        return dockRepo.save(dockModel);
    }

    public DockModel setStatusDock(int idDock, int idStatus) {
        DockModel dock = this.getDock(idDock);
        if (dock.getStatus() == statusService.getStatus(5) && idStatus == 6) {
            return dock;
        } else {
            dock.setStatus(statusService.getStatus(idStatus));
        }
        return dockRepo.save(dock);
    }

    public String remove(Integer id) {
        DockModel dock = this.getDock(id);
        if (dock.getStatus() == statusService.getStatus(4)) {
            dockRepo.delete(dock);
            return "success";
        }else{
            return "error";
        }
    }

    public List<DockModel> getDockByShip(Integer shipId){
        return dockRepo.findByShip(shipId);
    }
}

