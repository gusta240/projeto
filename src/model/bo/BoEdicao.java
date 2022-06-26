/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

import dao.DAOEdicao;
import exception.EdicaoException;
import model.vo.VoEdicao;
import model.vo.VoConsulta;


public class BoEdicao implements III_BO {

    // atributos
    private VoEdicao vo_edicao;



    private String erro;

    private DAOEdicao edicao;



    // construtor
    public BoEdicao(VoEdicao vo) {

        this.edicao = new DAOEdicao(vo_edicao);

        this.erro = "";
    }

    /**
     * *
     *
     * @return
     */
    @Override
    public boolean cadastrar() throws EdicaoException {
        // validar os campos
        if (!this.validar()) {
            return false;
        }
        try {
            // cadastrar
            this.getDao().setVo(this.getVo());
            if (!this.getDao().cadastrar()) {
                this.setErro("Houve um erro ao salvar as informações de cidade no banco de dados");
                return false;
            }
        } catch (EdicaoException ex) {
            this.setErro("Houve um erro ao salvar as informações de cidade no banco de dados");
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
    public boolean excluir() throws EdicaoException {
        // testa o valor do código
        if (!this.validarCodigo()) {
            return false;
        }

        try {
            // set vo
            this.getDao().setVo(this.getVo());

            // excluir
            if (!this.getDao().excluir()) {
                this.setErro("Houve um erro ao excluir um cidade do banco de dados");
                return false;
            }
        } catch (EdicaoException ex) {
            this.setErro("Houve um erro ao excluir um cidade do banco de dados\n" + ex.getMessage());
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
    public boolean consultar() throws EdicaoException {
        // testa o valor do código
        if (!this.validarCodigo()) {
            return false;
        }
        // consultar
        try {
            this.getDao().setVo(this.getVo());
            if (this.getDao().consultar()) {
                this.setVo(this.getDao().getVo());
                return true;
            }
        } catch (EdicaoException ex) {
            this.setErro("Houve um erro ao consultar um cidade do banco de dados\n" + ex.getMessage());
            throw ex;
        }

        // erro 
        this.setErro("Codigo de cidade não cadastrado!");

        // return
        return false;
    }

    /**
     * *
     *
     * @return
     */
    public VoConsulta obterLista() throws EdicaoException {
        try {
            return this.getDao().obterLista();
        } catch (EdicaoException ex) {
            this.setErro("Não foi possível obter a lista de cidades cadastradas!");
            throw ex;
        }
    }

    /**
     * *
     *
     * @return
     */
    public int proximoCodigoLivre() throws EdicaoException {
        try {
            return this.getDao().proximoCodigoLivre();
        } catch (EdicaoException ex) {
            this.setErro("Erro ao obter o próximo código do Cidade!");
            throw ex;
        }
    }

    /**
     * *
     *
     * @return
     */
    public boolean validarCodigo() {
        // testa o valor do código
        if (this.getVo().getCodigo() <= 0) {
            this.setErro("O Código do Cidade deve ser informado!");
            return false;
        }

        // return, sem erros
        return true;
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
        // testa o valor do código
        if (!this.validarCodigo()) {
            error = false;
            msg = this.getErro() + "\n";
        }

        // testa o valor do descrição
        if (this.getVo().getNome().isEmpty()) {
            msg += "O nome da Cidade deve ser informada!\n";
            error = false;
        }

        // testa o valor do nome
        if (this.getVo().getNome().length() > 40) {
            msg += "O nome da Cidade deve ter no máximo 40 caracteres!\n";
            error = false;
        }

        // testa o valor da uf
        if ((this.getVo().getUf().length() < 2) || (this.getVo().getUf().length() > 2)) {
            msg += "O nome da UF deve ser informada e ter dois caracteres!\n";
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
    public VoEdicao getVo() {
        return vo_edicao;
    }

    public void setVo(VoEdicao vo) {
        this.vo_edicao = vo;
    }

    public String getErro() {
        return this.erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public DAOEdicao getDao() {
        return edicao;
    }

    public void setDao(DAOEdicao dao) {
        this.edicao = dao;
    }
}
