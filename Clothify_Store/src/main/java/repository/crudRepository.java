package repository;

import dto.product;
import dto.supplier;
import dto.user;
import entity.supplierEntity;
import javafx.collections.ObservableList;

public interface crudRepository <T> extends superRepository{

    boolean add(T entity);

    ObservableList<Integer> getIDs();

    ObservableList<T> getAll();

    boolean update(supplier supplier, int id);
    boolean update(product supplier, int id);

    boolean delete(int id);

    boolean update(user user, int id);

}
