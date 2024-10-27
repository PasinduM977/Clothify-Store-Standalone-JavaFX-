package repository.custom;

import entity.orderDetailsEntity;
import entity.productEntity;
import javafx.collections.ObservableList;
import repository.crudRepository;

public interface productDao extends crudRepository<productEntity> {
    boolean updatePqty(ObservableList<orderDetailsEntity> orderDetailsObservableList);
}
