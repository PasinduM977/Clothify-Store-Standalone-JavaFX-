package service.custom;

import dto.product;
import entity.orderDetailsEntity;
import entity.productEntity;
import javafx.collections.ObservableList;
import service.superService;

public interface productService extends superService {
    ObservableList<Integer> getSupplierIDs();

    boolean addProduct(product product);

    ObservableList<Integer> getProductIDs();

    ObservableList<productEntity> getAll();

    boolean updateProduct(product product, int i);

    boolean deleteProduct(int i);

    boolean updateProductQty(ObservableList<orderDetailsEntity> orderDetailsObservableList);
}
