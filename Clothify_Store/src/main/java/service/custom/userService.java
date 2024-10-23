package service.custom;

import dto.user;
import entity.userEntity;
import javafx.collections.ObservableList;
import service.superService;

public interface userService extends superService {
    boolean addUser(user user);

    ObservableList<Integer> getUserIDs();

    ObservableList<userEntity> getAll();

    boolean updateUser(user user, int i);

    boolean deleteUser(int i);
}
