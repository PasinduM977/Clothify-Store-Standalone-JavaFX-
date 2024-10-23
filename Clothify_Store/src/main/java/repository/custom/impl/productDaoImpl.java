package repository.custom.impl;

import dto.product;
import dto.supplier;
import dto.user;
import entity.productEntity;
import entity.supplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.productDao;
import util.hibernateUtil;

import java.util.List;

public class productDaoImpl implements productDao{

    @Override
    public boolean add(productEntity entity) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public ObservableList<Integer> getIDs() {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();

        //List<Integer> resultList = session.createNativeQuery("select supplierID from supplierEntity").getResultList();
        Query<Integer> query = session.createQuery("SELECT e.productID FROM productEntity e");
        List<Integer> resultList = query.getResultList();
        ObservableList<Integer> productIDList = FXCollections.observableArrayList();
        resultList.forEach(id->{
            productIDList.add(id);
        });
        session.getTransaction().commit();
        session.close();

        return productIDList;
    }

    @Override
    public ObservableList<productEntity> getAll() {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();

        Query<productEntity> query = session.createQuery("from productEntity", productEntity.class);
        List<productEntity> resultList = query.getResultList();
        ObservableList<productEntity> productEntityObservableList = FXCollections.observableArrayList();

        resultList.forEach(entity->{
            productEntityObservableList.add(entity);
        });

        session.getTransaction().commit();
        session.close();
        return productEntityObservableList;
    }

    @Override
    public boolean update(supplier supplier, int id) {
        return false;
    }

    @Override
    public boolean update(product product, int id) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        try{
            productEntity entity = session.get(productEntity.class, id);
            entity.setProductName(product.getProductName());
            entity.setSize(product.getSize());
            entity.setUnitPrice(product.getUnitPrice());
            entity.setCategory(product.getCategory());
            entity.setAvailableQty(product.getAvailableQty());
            entity.setSupplierID(product.getSupplierID());
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }

        finally {
            session.getTransaction().commit();
            session.close();
        }

    }

    @Override
    public boolean delete(int id) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        productEntity entity = session.get(productEntity.class, id);
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(user user, int id) {
        return false;
    }
}
