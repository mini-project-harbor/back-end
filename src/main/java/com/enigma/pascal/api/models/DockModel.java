package com.enigma.pascal.api.models;

import javax.persistence.*;

@Entity
@Table(name = "master_dock")
public class DockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dockCode;

    @OneToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;

    @ManyToOne
    @JoinColumn(name = "port_id")
    private PortModel portModel;

    @OneToOne
    @JoinColumn(name = "ship_id")
    private ShipModel ship;

    public void setShip(ShipModel ship) {
        this.ship = ship;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDockCode() {
        return dockCode;
    }

    public void setDockCode(String dockCode) {
        this.dockCode = dockCode;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public void setPortModel(PortModel portModel) {
        this.portModel = portModel;
    }
}
