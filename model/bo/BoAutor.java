package model.bo;

import dao.DAOAutor;
import exception.AutorException;

import model.vo.VoAutor;

import model.vo.VoConsulta;


public class BoAutor implements I_BO {

    // atributos
    private VoAutor vo_autor;


    private String erro;

    private DAOAutor autor;



    // construtor
    public BoAutor(VoAutor vo) {

        this.autor = new DAOAutor(vo_autor);

        this.erro = "";
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean cadastrar() throws AutorException {
        // validar os campos
        if (!this.validar()) {
            return false;
        }
        try {
            // cadastrar
            this.getDao().setVo_autor(this.getVo());
            if (!this.getDao().cadastrar()) {
                this.setErro("Houve um erro ao salvar as informações de autor no banco de dados1");
                return false;
            }
        } catch (AutorException ex) {
            this.setErro("Houve um erro ao salvar as informações de autor no banco de dados2");
            throw ex;
        }

        // return, tudo ocorreu normal
        return true;
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean excluir() throws AutorException {
        try {
            // set vo
            this.getDao().setVo_autor(this.getVo());

            // excluir
            if (!this.getDao().excluir()) {
                this.setErro("Houve um erro ao excluir um autor do banco de dados1");
                return false;
            }
        } catch (AutorException ex) {
            this.setErro("Houve um erro ao excluir um autor do banco de dados2\n" + ex.getMessage());
            throw ex;
        }

        // return, tudo ocorreu normal
        return true;
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean consultar() throws AutorException {
        // consultar
        try {
            this.getDao().setVo_autor(this.getVo());
            if (this.getDao().consultar()) {
                this.setVo(this.getDao().getVo_autor());
                return true;
            }
        } catch (AutorException ex) {
            this.setErro("Houve um erro ao consultar um autor do banco de dados\n" + ex.getMessage());
            throw ex;
        }

        // erro 
        this.setErro("Codigo de autor não cadastrado!");

        // return
        return false;
    }

    /**
     * *
     *
     * @return
     * @throws exception.AutorException
     */
    public VoConsulta obterLista() throws AutorException {
        try {
            return this.getDao().obterLista();
        } catch (AutorException ex) {
            this.setErro("Não foi possível obter a lista de autores cadastrados!");
            throw ex;
        }
    }

    /**
     * *
     *
     * @return
     */
    public int proximoCodigoLivre() throws AutorException {
        try {
            return this.getDao().proximoCodigoLivre();
        } catch (AutorException ex) {
            this.setErro("Erro ao obter o próximo código de autores!");
            throw ex;
        }
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean validar() {
        boolean error = true;
        String msg = "";
        // testa o valor do descrição
        if (this.getVo().getNome().isEmpty()) {
            msg += "O nome do Autor deve ser informado!\n";
            error = false;
        }

        // testa o valor do nome
        if (this.getVo().getNome().length() > 40) {
            msg += "O nome do Autor deve ter no máximo 40 caracteres!\n";
            error = false;
        }

        // return....
        this.setErro(msg);
        return error;
    }

    /**
     * *
     *
     * getttes and set
     *
     * @return ters
     */
    public VoAutor getVo() {
        return vo_autor;
    }

    public void setVo(VoAutor vo) {
        this.vo_autor = vo;
    }

    public String getErro() {
        return this.erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public DAOAutor getDao() {
        return autor;
    }

    public void setDao(DAOAutor dao) {
        this.autor = dao;
    }
}
