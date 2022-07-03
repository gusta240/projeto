package model.bo;

import exception.AutorException;
import java.io.Serializable;

/**
 *
 * @author dlnotari
 */
public interface I_BO extends Serializable{
    public boolean cadastrar() throws AutorException;
    public boolean excluir() throws AutorException;
    public boolean consultar() throws AutorException;
    public boolean validar() throws AutorException;
}
