package com.aquintero.soaint.prueba.daofactory;

import com.aquintero.soaint.prueba.dao.LogValueDAO;
import com.aquintero.soaint.prueba.idao.ILogValueDAO;

public class DaoFactory
{

    private static DaoFactory daoFac;

    static {
        daoFac = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return daoFac;
    }

    public ILogValueDAO getILogValueDAO(){
        return new LogValueDAO();
    }

}
