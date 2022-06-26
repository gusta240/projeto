package dao;

import dao.bd.conexao.Conexao;
import exception.AutorException;
import exception.SGBDException;
import model.vo.VoConsulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.vo.VoAutor;
import projetosoftware.ProjetoSoftware;


public class DAOAutor implements I_DAO {

    // atributos
    private VoAutor vo;

    // construtor
    public DAOAutor(VoAutor vo) {
        this.vo = vo;
    }
    
    /**
     * *
     *
     * @return
     * @throws exception.AutorException @throws SQLException
     */
    @Override
    public boolean cadastrar() throws AutorException {
        try {
            // testa se existe
            String sql_autor;
            if (this.existeCodigo()) {
                // regravar
                sql_autor = "update autor set numero_edicao = ?, ano_edicao = ? ";
                
                

                //sql = "SELECT codigo_autor, nome_autor, numero_edicao, ano_edicao, codigo_livro, titulo_livro FROM autor, edicao, livro";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql_autor);

                // atribui valores
                ps.setInt(3, vo.getCodigo());
                ps.setString(1, vo.getNome());
                ps.setString(2, vo.getUf());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            } else {
                // gravar
                sql_autor = "insert into autor (codigo_autor, nome_autor)" + " values (?, ?)";

                // obtem objeto
                PreparedStatement ps = this.getConexao().getBd().getStatement(sql_autor);

                // gerar o código
                vo.setCodigo(this.proximoCodigoLivre());

                // atribui valores            
                ps.setInt(1, vo.getCodigo());
                ps.setString(2, vo.getNome());

                // regrava no bd
                this.getConexao().getBd().executaSQL(ps);
            }
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new AutorException(e.getMessage());
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
    public boolean excluir() throws AutorException {
        try {
            // testa se existe
            String sql;
            if (this.existeCodigo()) {
                // regravar
                sql = "delete from autor where codigo_autor = ?";

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
            throw new AutorException(e.getMessage());
        }

        // return
        return false;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public VoConsulta obterLista() throws AutorException {
        try {
            // consultar o código
            String sql = "select * from autor where codigo_autor > 0 order by nome_autor";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // cria lista de autor
            return new VoConsulta(rs);
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new AutorException(e.getMessage());
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
    public boolean consultar() throws AutorException {
        try {
            // consultar o código
            String sql = "select * from autor where codigo_autor = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                this.vo = new VoAutor(rs.getInt("codigo_autor"), rs.getString("nome_autor"),
                        rs.getString("uf"));
                return true;
            }
        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new AutorException(e.getMessage());
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
    public boolean existeCodigo() throws AutorException {
        try {
            // consultar o código
            String sql = "select * from autor where codigo_autor = " + this.getVo().getCodigo();

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                return true;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new AutorException(e.getMessage());
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
    public int proximoCodigoLivre() throws AutorException {
        try {
            // consultar o código
            String sql = "select max(codigo_autor) from autor";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                int codigo = rs.getInt(1) + 1;
                return codigo;
            }

        } catch (ClassNotFoundException | SQLException | SGBDException e) {
            throw new AutorException(e.getMessage());
        }

        // return, não existe o código
        return 1;
    }

    /**
     * GETTERS & SETTERS
     *
     * @return
     */
    public VoAutor getVo() {
        return vo;
    }

    public void setVo(VoAutor vo) {
        this.vo = vo;
    }

    public Conexao getConexao() {
        return ProjetoSoftware.getConexao();
    }
}
