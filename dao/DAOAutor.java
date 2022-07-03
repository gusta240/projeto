package dao;

import dao.bd.conexao.Conexao;
import exception.AutorException;
import exception.SGBDException;
import model.vo.VoConsulta;
import model.vo.VoAutor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import projetosoftware.ProjetoSoftware;

public class DAOAutor implements I_DAO {

    // atributos
    private VoAutor vo_autor;

    // construtor
    public DAOAutor(VoAutor vo_autor) {
        this.vo_autor = vo_autor;

    }
    
    /**
     * *
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean cadastrar() throws AutorException {
        try {
            // testa se existe
            String sql_autor;
            String sql_edicao;
            String sql_ano;
            
            // gravar
            sql_autor = "insert into autor (nome_autor) values (?)";
            sql_edicao = "insert into edicao (ano_edicao) values (?)";
            sql_ano = "insert into livro (titulo_livro) values (?)";


            // obtem objeto
            PreparedStatement ps_autor = this.getConexao().getBd().getStatement(sql_autor);

            // atribui valores            
            ps_autor.setString(1, vo_autor.getNome());

            // regrava no bd
            this.getConexao().getBd().executaSQL(ps_autor);

            /*******/

            // obtem objeto
            PreparedStatement ps_edicao = this.getConexao().getBd().getStatement(sql_edicao);

            // atribui valores            
            ps_edicao.setString(1, vo_autor.getAno());


            // regrava no bd
            this.getConexao().getBd().executaSQL(ps_edicao);


            /*******/

            // obtem objeto
            PreparedStatement ps_ano = this.getConexao().getBd().getStatement(sql_ano);

            // atribui valores            
            ps_ano.setString(1, vo_autor.getTitulo());


            // regrava no bd
            this.getConexao().getBd().executaSQL(ps_ano);
            
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
            String sql_autor;
            String sql_livro;
            String sql_edicao;
            if (this.existeCodigo()) {
                // regravar
                sql_autor = "delete from autor where nome_autor = ?";
                sql_livro = "delete from livro where titulo_livro = ?";
                sql_edicao = "delete from edicao where ano_edicao = ?";
                

                // obtem objeto
                PreparedStatement ps_autor = this.getConexao().getBd().getStatement(sql_autor);

                // atribui valores            
                ps_autor.setString(1, vo_autor.getNome());

                // exclui no bd
                this.getConexao().getBd().executaSQL(ps_autor);

                // obtem objeto
                PreparedStatement ps_livro = this.getConexao().getBd().getStatement(sql_livro);

                // atribui valores            
                ps_livro.setString(1, vo_autor.getTitulo());

                // exclui no bd
                this.getConexao().getBd().executaSQL(ps_livro);

                // obtem objeto
                PreparedStatement ps_edicao = this.getConexao().getBd().getStatement(sql_edicao);

                // atribui valores            
                ps_edicao.setString(1, vo_autor.getAno());

                // exclui no bd
                this.getConexao().getBd().executaSQL(ps_edicao);
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
            String sql = "select nome_autor, titulo_livro, ano_edicao, numero_edicao from autor, livro, edicao";

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
            String sql = "select nome_autor, titulo_livro, ano_edicao, numero_edicao from autor, livro, edicao";

            // executar sql
            ResultSet rs = this.getConexao().getBd().consulta(sql);

            // testa resultado
            while (rs.next()) {
                this.vo_autor = new VoAutor(rs.getString("nome_autor"), rs.getString("titulo_livro"), rs.getString("ano_edicao"));
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
            String sql = "select codigo_autor, codigo_livro from livrosautor, livro, autor where livrosautor.codigo_autor = autor.codigo_autor AND livrosautor.codigo_livro = ";

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

    public VoAutor getVo_autor() {
        return vo_autor;
    }

    public void setVo_autor(VoAutor vo_autor) {
        this.vo_autor = vo_autor;
    }

    public Conexao getConexao() {
        return ProjetoSoftware.getConexao();
    }
}
