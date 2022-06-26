package dao;

import exception.AutorException;

public interface I_DAO {
    public boolean cadastrar() throws AutorException;
    public boolean excluir() throws AutorException;
    public boolean consultar() throws AutorException;
}