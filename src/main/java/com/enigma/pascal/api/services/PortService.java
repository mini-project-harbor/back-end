package com.enigma.pascal.api.services;

import com.enigma.pascal.api.dto.PortDto;
import com.enigma.pascal.api.models.DockModel;
import com.enigma.pascal.api.models.PortModel;
import com.enigma.pascal.api.repositories.PortRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortService {
    @Autowired
    PortRepo portRepo;

    @Autowired
    DockService dockService;

    @Autowired
    StatusService statusService;

    public Page<PortModel> getListPort(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return portRepo.findAll(pageable);
    }


    public PortModel getPort(int id) {
        return portRepo.findById(id).get();
    }

    public PortModel getPortDockVisible(int id) {
        PortModel port = this.getPort(id);
        List<DockModel> dockVisible = new ArrayList<>();
        for (DockModel dock : port.getDockModels()) {
            if (dock.getStatus() == statusService.getStatus(4)) {
                dockVisible.add(dock);
            }
        }
        port.setDockModels(dockVisible);
        return port;
    }

    public PortModel addPort(PortDto portDto) {

        PortModel newPort = new PortModel();
        newPort.setId(portDto.getId());
        newPort.setPortCode(portDto.getPortCode());
        newPort.setPortName(portDto.getPortName());
        PortModel port = portRepo.save(newPort);

        DockModel newDock = portDto.getDock();
        newDock.setDockCode(portDto.getDock().getDockCode());
        newDock.setStatus(statusService.getStatus(4));
        newDock.setPortModel(port);

        dockService.addDock(newDock);
        return port;
    }

    public String deletePort(int id) {
        if (this.getPort(id) != null) {
            portRepo.delete(this.getPort(id));
            return "success";
        } else {
            return "error";
        }
    }
}
