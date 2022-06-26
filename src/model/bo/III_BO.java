package model.bo;

import exception.EdicaoException;
import java.io.Serializable;

/**
 *
 * @author dlnotari
 */
public interface III_BO extends Serializable{
    public boolean cadastrar() throws EdicaoException;
    public boolean excluir() throws EdicaoException;
    public boolean consultar() throws EdicaoException;
    public boolean validar() throws EdicaoException;
}
