package repository.custom;

import entity.userEntity;
import repository.crudRepository;

public interface userDao extends crudRepository<userEntity> {
    String getPW(String text);
}
