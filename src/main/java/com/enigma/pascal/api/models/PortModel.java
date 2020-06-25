package com.enigma.pascal.api.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "master_port")
public class PortModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String portCode;

    private String portName;

    @OneToMany(mappedBy = "portModel")
    private List<DockModel> dockModels = new ArrayList<>();

    public PortModel() {}
    public PortModel(Integer id,String portName) {
        this.id = id;
        this.portName = portName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public List<DockModel> getDockModels() {
        return dockModels;
    }

    public void setDockModels(List<DockModel> dockModels) {
        this.dockModels = dockModels;
    }
}
