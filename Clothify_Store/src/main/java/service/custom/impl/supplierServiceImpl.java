package service.custom.impl;

import dto.product;
import dto.supplier;
import entity.supplierEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.supplierDao;
import repository.repositoryFactory;
import repository.superRepository;
import service.custom.supplierService;
import util.daoType;

public class supplierServiceImpl implements supplierService {
    @Override
    public boolean addSupplier(supplier sup) {
        supplierEntity entity = new ModelMapper().map(sup, supplierEntity.class);
        supplierDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.supplier);
        return repo.add(entity);


    }

    @Override
    public ObservableList<Integer> getSupplierIDs() {

        supplierDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.supplier);
        return repo.getIDs();
    }

    @Override
    public ObservableList<supplierEntity> getAll() {
        supplierDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.supplier);
        return repo.getAll();
    }

    @Override
    public boolean updateSupplier(supplier supplier, int id) {
        supplierDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.supplier);
        return repo.update(supplier,id);
    }

    @Override
    public boolean deleteSupplier(int id) {
        supplierDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.supplier);
        return repo.delete(id);
    }


}
