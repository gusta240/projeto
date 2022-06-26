package dao;

import exception.EdicaoException;

public interface III_DAO {
    public boolean cadastrar() throws EdicaoException;
    public boolean excluir() throws EdicaoException;
    public boolean consultar() throws EdicaoException;    
}
