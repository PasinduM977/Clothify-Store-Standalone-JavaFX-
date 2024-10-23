package service.custom.impl;

import dto.product;
import entity.productEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.productDao;
import repository.custom.supplierDao;
import repository.repositoryFactory;
import repository.superRepository;
import service.custom.productService;
import util.daoType;

public class productServiceImpl implements productService {
    @Override
    public ObservableList<Integer> getSupplierIDs() {
        supplierDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.supplier);
        return repo.getIDs();
    }

    @Override
    public boolean addProduct(product product) {
        productDao repo = repositoryFactory.getInstance().getDaoType(daoType.product);
        productEntity entity = new ModelMapper().map(product, productEntity.class);
        return repo.add(entity);
    }

    @Override
    public ObservableList<Integer> getProductIDs() {
        productDao repo = repositoryFactory.getInstance().getDaoType(daoType.product);
        return repo.getIDs();
    }

    @Override
    public ObservableList<productEntity> getAll() {
        productDao repo = repositoryFactory.getInstance().getDaoType(daoType.product);
        return repo.getAll();
    }

    @Override
    public boolean updateProduct(product product, int id) {
        productDao repo = repositoryFactory.getInstance().getDaoType(daoType.product);
        return repo.update(product,id);
    }

    @Override
    public boolean deleteProduct(int id) {
        productDao repo = repositoryFactory.getInstance().getDaoType(daoType.product);
        return repo.delete(id);
    }
}
