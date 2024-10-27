package repository;

import repository.custom.impl.*;
import service.custom.impl.productServiceImpl;
import util.daoType;

public class repositoryFactory {
    private static repositoryFactory instance;
    private repositoryFactory()
    {

    }
    public static repositoryFactory getInstance()
    {
        return instance==null? instance=new repositoryFactory():instance;
    }
    public <T extends superRepository>T getDaoType(daoType type)
    {
        switch (type)
        {
            case supplier:return (T) new supplierDaoImpl();
            case product:return (T) new productDaoImpl();
            case user:return (T) new userDaoImpl();
            case order:return (T) new orderDaoImpl();
            case orderdetails:return (T) new orderDetailsDaoImpl();
        }
        return null;
    }

}
