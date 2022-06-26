package dao;

import exception.LivroAutorException;

public interface IIII_DAO {
    public boolean cadastrar() throws LivroAutorException;
    public boolean excluir() throws LivroAutorException;
    public boolean consultar() throws LivroAutorException;    
}