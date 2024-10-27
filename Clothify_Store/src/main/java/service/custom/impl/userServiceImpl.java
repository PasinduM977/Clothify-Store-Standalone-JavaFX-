package service.custom.impl;

import dto.user;
import entity.supplierEntity;
import entity.userEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.productDao;
import repository.custom.supplierDao;
import repository.custom.userDao;
import repository.repositoryFactory;
import service.custom.userService;
import util.daoType;

public class userServiceImpl implements userService {
    @Override
    public boolean addUser(user user) {
        userEntity entity = new ModelMapper().map(user, userEntity.class);
        userDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.user);
        return repo.add(entity);
    }

    @Override
    public ObservableList<Integer> getUserIDs() {
        userDao repo = repositoryFactory.getInstance().getDaoType(daoType.user);
        return repo.getIDs();
    }

    @Override
    public ObservableList<userEntity> getAll() {
        userDao repo = repositoryFactory.getInstance().getDaoType(util.daoType.user);
        return repo.getAll();
    }

    @Override
    public boolean updateUser(user user, int id) {
        userDao repo = repositoryFactory.getInstance().getDaoType(daoType.user);
        return repo.update(user,id);
    }

    @Override
    public boolean deleteUser(int id) {
        userDao repo = repositoryFactory.getInstance().getDaoType(daoType.user);
        return repo.delete(id);
    }

    @Override
    public String getPassword(String text) {
        userDao repo = repositoryFactory.getInstance().getDaoType(daoType.user);
        return repo.getPW(text);
    }
}
