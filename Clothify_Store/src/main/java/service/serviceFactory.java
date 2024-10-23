package service;

import service.custom.impl.productServiceImpl;
import service.custom.impl.supplierServiceImpl;
import service.custom.impl.userServiceImpl;
import util.ServiceType;

public class serviceFactory {
    private static serviceFactory instance;
    private serviceFactory()
    {

    }

    public static serviceFactory getInstance()
    {
        return instance==null ? instance= new serviceFactory():instance;
    }

    public <T extends superService>T getServiceType(ServiceType type)
    {
            switch (type)
            {
                case supplier:return (T) new supplierServiceImpl();
                case product:return (T) new productServiceImpl();
                case user:return (T) new userServiceImpl();
            }
            return null;
    }



}
