package repository.custom.impl;

import dto.product;
import dto.supplier;
import dto.user;
import entity.supplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.supplierDao;
import util.hibernateUtil;

import java.util.List;

public class supplierDaoImpl implements supplierDao {

    @Override
    public boolean add(supplierEntity entity) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;

    }

    @Override
    public ObservableList<Integer> getIDs() {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();

        //List<Integer> resultList = session.createNativeQuery("select supplierID from supplierEntity").getResultList();
        Query<Integer> query = session.createQuery("SELECT e.supplierID FROM supplierEntity e");
        List<Integer> resultList = query.getResultList();
        ObservableList<Integer> supllierIDList = FXCollections.observableArrayList();
        resultList.forEach(id->{
            supllierIDList.add(id);
        });
        session.getTransaction().commit();
        session.close();

        return supllierIDList;
    }

    @Override
    public ObservableList<supplierEntity> getAll() {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();

        Query<supplierEntity> query = session.createQuery("from supplierEntity", supplierEntity.class);
        List<supplierEntity> resultList = query.getResultList();
        ObservableList<supplierEntity> supplierEntityObservableList = FXCollections.observableArrayList();

        resultList.forEach(entity->{
            supplierEntityObservableList.add(entity);
        });

        session.getTransaction().commit();
        session.close();
        return supplierEntityObservableList;
    }

    @Override
    public boolean update(supplier supplier, int id) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
try{
    supplierEntity entity = session.get(supplierEntity.class, id);
    entity.setSupplierName(supplier.getSupplierName());
    entity.setCompany(supplier.getCompany());
    entity.setSupplierEmail(supplier.getSupplierEmail());
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
    public boolean update(product supplier, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        supplierEntity entity = session.get(supplierEntity.class, id);
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
