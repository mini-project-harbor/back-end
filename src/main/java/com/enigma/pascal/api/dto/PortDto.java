package com.enigma.pascal.api.dto;

import com.enigma.pascal.api.models.DockModel;

public class PortDto {
    private Integer id;

    private String portName;

    private String portCode;

    private DockModel dock;

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public DockModel getDock() {
        return dock;
    }

    public void setDock(DockModel dock) {
        this.dock = dock;
    }
}
