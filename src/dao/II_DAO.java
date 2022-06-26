package dao;

import exception.LivroException;

public interface II_DAO {
    public boolean cadastrar() throws LivroException;
    public boolean excluir() throws LivroException;
    public boolean consultar() throws LivroException;    
}
