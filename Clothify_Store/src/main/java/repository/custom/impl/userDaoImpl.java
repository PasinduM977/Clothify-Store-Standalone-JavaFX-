package repository.custom.impl;

import dto.product;
import dto.supplier;
import dto.user;
import entity.productEntity;
import entity.supplierEntity;
import entity.userEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.userDao;
import util.hibernateUtil;

import java.util.List;

public class userDaoImpl implements userDao {
    @Override
    public boolean add(userEntity entity) {
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
        Query<Integer> query = session.createQuery("SELECT e.userID FROM userEntity e");
        List<Integer> resultList = query.getResultList();
        ObservableList<Integer> userIDList = FXCollections.observableArrayList();
        resultList.forEach(id->{
            userIDList.add(id);
        });
        session.getTransaction().commit();
        session.close();

        return userIDList;
    }

    @Override
    public ObservableList<userEntity> getAll() {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();

        Query<userEntity> query = session.createQuery("from userEntity", userEntity.class);
        List<userEntity> resultList = query.getResultList();
        ObservableList<userEntity> userEntityObservableList = FXCollections.observableArrayList();

        resultList.forEach(entity->{
            userEntityObservableList.add(entity);
        });

        session.getTransaction().commit();
        session.close();
        return userEntityObservableList;
    }

    @Override
    public boolean update(supplier supplier, int id) {
        return false;
    }

    @Override
    public boolean update(product supplier, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        userEntity entity = session.get(userEntity.class, id);
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(user user, int id) {
        Session session = hibernateUtil.getSession();
        session.getTransaction().begin();
        try{
            userEntity entity = session.get(userEntity.class, id);
            entity.setUserName(user.getUserName());
            entity.setUserEmail(user.getUserName());
            entity.setPassword(user.getPassword());

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
    public String getPW(String text) {

        try
        {
            Session session = hibernateUtil.getSession();
            session.getTransaction().begin();
            ///////////////////////
            Query<userEntity> query = session.createQuery("FROM userEntity e WHERE e.userEmail  = :value", userEntity.class);
            query.setParameter("value", text);
            userEntity entity = query.uniqueResult();
            String pw=entity.getPassword();
            ////////////////
            session.getTransaction().commit();
            session.close();

            return pw;
        }

        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Something Wrong!");

            // Show the alert
            alert.showAndWait();
            return null;
        }

    }
}
