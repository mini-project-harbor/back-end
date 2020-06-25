package com.enigma.pascal.api.models;

import javax.persistence.*;

@Entity
@Table(name = "master_ship")
public class ShipModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "ship")
    private DockModel dock;

    private String codeShip;

    private String shipName;

    private String captain;

    public ShipModel(Integer id,String codeShip, String shipName, String captain) {
        this.id = id;
        this.codeShip = codeShip;
        this.shipName = shipName;
        this.captain = captain;
    }

    public ShipModel (){}

    public DockModel getDock() {
        return dock;
    }

    public void setDock(DockModel dock) {
        this.dock = dock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeShip() {
        return codeShip;
    }

    public void setCodeShip(String codeShip) {
        this.codeShip = codeShip;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}
