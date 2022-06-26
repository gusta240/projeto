package dao;

import dao.bd.conexao.Conexao;
import exception.EdicaoException;
import exception.SGBDException;
import model.vo.VoConsulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.vo.VoEdicao;
import projetosoftware.ProjetoSoftware;

public class DAOEdicao implements III_DAO{

    // atributos
    private VoEdicao vo;

    // construtor
    public DAOEdicao(VoEdicao vo) {
        this.vo = vo;
    }

     /**
     * *
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean cadastrar() throws EdicaoException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "update edicao "
                        + "set numero_edicao = ?, "
                        + "    ano_edicao    = ? ";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // atribui valores
                ps.setInt(3, vo.getCodigo());
                ps.setString(1, vo.getNome());
                ps.setString(2, vo.getUf());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            } else {
                // gravar
                sql = "insert into edicao (numero_edicao, ano_edicao)"
                        + " values (?, ?)";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // gerar o código
                vo.setCodigo(this.proximoCodigoLivre());

                // atribui valores            
                ps.setInt(1, vo.getCodigo());
                ps.setString(2, vo.getNome());
                ps.setString(3, vo.getUf());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            }
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new EdicaoException(e.getMessage());
        }

        // return, tudo certo ao salvar
        return true;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean excluir() throws EdicaoException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "delete from edicao where codigo_livro = ?";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql);

                // atribui valores
                ps.setInt(1, vo.getCodigo());

                // exclui no bd
                this.getConexao().getBd().executaSQL(ps);
                // return, tudo certo ao excluir
                return true;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new EdicaoException(e.getMessage());
        }

        // return
        return false;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public VoConsulta obterLista() throws EdicaoException {
        try {
            // consultar o código
            String sql = "select * from edicao where ano_edicao > 0 order by numero_edicao";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // cria lista de edicao
            return new VoConsulta(rs);
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new EdicaoException(e.getMessage());
        }
    }

    /**
     * *
     *
     * @return @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean consultar() throws EdicaoException {
        try {
            // consultar o código
            String sql = "select * from edicao where ano_edicao = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                this.vo = new VoEdicao(rs.getInt("numero_edicao"), rs.getString("ano_edicao"),
                        rs.getString("uf"));
                return true;
            }
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new EdicaoException(e.getMessage());
        }

        // return, não existe o código
        return false;
    }

    /**
     * *
     *
     * @return @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    public boolean existeCodigo() throws EdicaoException {
        try {
            // consultar o código
            String sql = "select * from edicao where ano_edicao = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                return true;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new EdicaoException(e.getMessage());
        }

        // return, não existe o código
        return false;
    }

    /**
     * *
     *
     * @return @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    public int proximoCodigoLivre() throws EdicaoException {
        try {
            // consultar o código
            String sql = "select max(ano_edicao) from edicao";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                int codigo = rs.getInt(1) + 1;
                return codigo;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new EdicaoException(e.getMessage());
        }

        // return, não existe o código
        return 1;
    }

    /**
     * GETTERS & SETTERS
     *
     * @return
     */
    public VoEdicao getVo() {
        return vo;
    }

    public void setVo(VoEdicao vo) {
        this.vo = vo;
    }

    public Conexao getConexao() {
        return ProjetoSoftware.getConexao();
    }
}
