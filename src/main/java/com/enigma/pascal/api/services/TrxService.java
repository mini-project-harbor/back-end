package com.enigma.pascal.api.services;

import com.enigma.pascal.api.dto.ReportDto;
import com.enigma.pascal.api.dto.TransactionDto;
import com.enigma.pascal.api.models.*;
import com.enigma.pascal.api.repositories.TrxRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class TrxService {
    @Autowired
    TrxRepo trxRepo;

    @Autowired
    PortService portService;

    @Autowired
    ShipService shipService;

    @Autowired
    StatusService statusService;

    @Autowired
    DockService dockService;

    public Page<TrxModel> getTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return trxRepo.findAll(pageable);
    }

    private List<ReportDto> getListTransaction() {
        List<ReportDto> reports = new ArrayList<>();
        for (TrxModel trx : trxRepo.findAll()) {
            ReportDto report = new ReportDto();
            report.setCode(trx.getTransactionCode());
            report.setDate(trx.getTransactionDate());
            report.setCaptain(trx.getShip().getCaptain());
            report.setPort(trx.getPort().getPortName());
            report.setLoad(trx.getLoad());
            report.setStatus(trx.getStatus().getName());
            report.setShip(trx.getShip().getShipName());
            reports.add(report);
        }
        return reports;
    }

    public List<TrxModel> getTrxByShipAndPort(Integer idPort, Integer idShip) {
        return trxRepo.findByPortAndShip(idPort, idShip);
    }

    public TrxModel addTransaction(TransactionDto transactionDto) {
        ShipModel ship = shipService.getShip(transactionDto.getShipId());
        PortModel port = portService.getPort(transactionDto.getPortId());
        StatusModel statusShip = statusService.getStatus(transactionDto.getStatusId());

        StatusModel statusDockAvailable = statusService.getStatus(4);
        StatusModel statusDockNotAvailable = statusService.getStatus(5);
        StatusModel statusShipSailing = statusService.getStatus(1);
        StatusModel statusShipDocked = statusService.getStatus(2);
        StatusModel statusShipUnloading = statusService.getStatus(3);

        Date dateNow = new Date(System.currentTimeMillis());
        List<TrxModel> listOldTrx = this.getTrxByShipAndPort(port.getId(), ship.getId());
        List<TrxModel> listOldTrxByDate = trxRepo.findAllByPortAndShipAndTransactionDate(port.getId(), ship.getId(), dateNow);
        DockModel dockAvailable = new DockModel();
        List<DockModel> dockNotAvailable = dockService.getDockByShip(ship.getId());

        if (portService.getPortDockVisible(port.getId()) != null) {
            for (int i = 0; i < port.getDockModels().size(); i++) {
                if (i == 0) {
                    dockAvailable.setId(port.getDockModels().get(i).getId());
                    dockAvailable.setDockCode(port.getDockModels().get(i).getDockCode());
                    dockAvailable.setStatus(statusDockNotAvailable);
                    dockAvailable.setShip(ship);
                    dockAvailable.setPortModel(port);
                }
            }
        }


        TrxModel transaction = new TrxModel();


        if (listOldTrx.size() == 0) {
            transaction.setId(transactionDto.getId());
            transaction.setStatus(statusShip);
            transaction.setTransactionDate(dateNow);
            transaction.setTransactionCode(transactionDto.getTransactionCode());
            transaction.setPort(port);
            transaction.setShip(ship);
            transaction.setLoad(transactionDto.getLoad());
            if (dockNotAvailable.size() == 0 && statusShip != statusShipSailing) {
                dockAvailable.setPortModel(port);
                dockAvailable.setShip(ship);
                dockService.addDock(dockAvailable);
                return trxRepo.save(transaction);
            } else {
                return trxRepo.save(transaction);
            }
        } else {
            if (listOldTrxByDate.size() <= 2) {
                if (dockNotAvailable.size() > 0) {
                    transaction.setStatus(statusShip);
                    transaction.setId(transactionDto.getId());
                    transaction.setTransactionDate(dateNow);
                    transaction.setLoad(transactionDto.getLoad());
                    transaction.setShip(ship);
                    transaction.setTransactionCode(transactionDto.getTransactionCode());
                    if (listOldTrx.get(listOldTrx.size() - 1).getStatus() != statusShip) {
                        if (statusShip == statusShipSailing) {
                            transaction.setPort(listOldTrx.get(listOldTrx.size() - 1).getPort());
                            dockNotAvailable.get(0).setShip(null);
                            dockNotAvailable.get(0).setStatus(statusDockAvailable);
                            dockService.addDock(dockNotAvailable.get(0));
                            return trxRepo.save(transaction);
                        } else {
                            transaction.setPort(listOldTrx.get(listOldTrx.size() - 1).getPort());
                            return trxRepo.save(transaction);
                        }
                    } else {
                        return null;
                    }
                } else {
                    int size = listOldTrx.size() - 1;
                    transaction.setStatus(statusShip);
                    transaction.setId(transactionDto.getId());
                    transaction.setTransactionDate(dateNow);
                    transaction.setTransactionCode(transactionDto.getTransactionCode());
                    transaction.setPort(port);
                    transaction.setLoad(transactionDto.getLoad());
                    transaction.setShip(ship);
                    if (listOldTrx.get(size).getStatus() != statusShip) {
                        dockAvailable.setShip(ship);
                        dockAvailable.setStatus(statusDockNotAvailable);
                        dockService.addDock(dockAvailable);
                        return trxRepo.save(transaction);
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
    }


    public byte[] doGetReport() {
        List<ReportDto> reports = new ArrayList<>();
        for (TrxModel trx : trxRepo.findAll()) {
            ReportDto report = new ReportDto();
            report.setCode(trx.getTransactionCode());
            report.setDate(trx.getTransactionDate());
            report.setCaptain(trx.getShip().getCaptain());
            report.setPort(trx.getPort().getPortName());
            report.setLoad(trx.getLoad());
            report.setStatus(trx.getStatus().getName());
            report.setShip(trx.getShip().getShipName());
            reports.add(report);
        }

        HashMap<String, Object> parameters = new HashMap<>();
        InputStream jrxmlInput = null;
        try {
            jrxmlInput = this.getClass().getClassLoader().getResource("report.jrxml").openStream();
            JasperReport rep = JasperCompileManager.compileReport(jrxmlInput);
            JRDataSource datasource = new JRBeanCollectionDataSource(reports, true);

            JasperPrint print = JasperFillManager.fillReport(rep, parameters, datasource);
            return JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        } catch (JRException e) {
            System.out.println(e);
            return null;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
