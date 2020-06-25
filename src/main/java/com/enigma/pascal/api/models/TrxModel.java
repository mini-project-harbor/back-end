package com.enigma.pascal.api.models;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "master_transaction")
public class TrxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date transactionDate;

    private String transactionCode;

    @Column(name = "`load`")
    private Integer load;

    @ManyToOne(targetEntity = PortModel.class)
    @JoinColumn(name = "port_id")
    private PortModel port;

    @OneToOne(targetEntity = StatusModel.class)
    @JoinColumn(name="status_id")
    private StatusModel status;

    @ManyToOne(targetEntity = ShipModel.class)
    @JoinColumn(name = "ship_id")
    private ShipModel ship;

    public TrxModel(){}

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate= transactionDate;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public PortModel getPort() {
        return port;
    }

    public void setPort(PortModel port) {
        this.port = port;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public ShipModel getShip() {
        return ship;
    }

    public void setShip(ShipModel ship) {
        this.ship = ship;
    }

}
