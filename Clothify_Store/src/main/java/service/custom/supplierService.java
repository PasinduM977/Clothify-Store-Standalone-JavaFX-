package service.custom;

import dto.product;
import dto.supplier;
import entity.supplierEntity;
import javafx.collections.ObservableList;
import service.superService;

public interface supplierService extends superService {
    boolean addSupplier(supplier supplier);

    ObservableList<Integer> getSupplierIDs();

    ObservableList<supplierEntity> getAll();

    boolean updateSupplier(supplier supplier, int i);

    boolean deleteSupplier(int i);


}
