package com.aquintero.soaint.prueba.idao;

import com.aquintero.soaint.prueba.connection.DBConnection;
import com.aquintero.soaint.prueba.dto.LogValuesDto;

import java.util.List;

public interface ILogValueDAO
{

    void insertLogValue(DBConnection conn, String messageText, String type);
    List<LogValuesDto> allLogValues(DBConnection conn);

}
