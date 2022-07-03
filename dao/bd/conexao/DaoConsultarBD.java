package dao.bd.conexao;

import exception.SGBDException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoConsultarBD {

    DaoConectarBD bd;
    Connection conexao;

    public DaoConsultarBD(DaoConectarBD bd) {
        this.bd = bd;
    }
    
    public PreparedStatement getStatement(String sql) throws SQLException, SGBDException, ClassNotFoundException {
        PreparedStatement comando;
        conexao = bd.getConexao();

        // prepara consulta
        comando = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // retorna
        return comando;
    }
    

    public void executaSQL(PreparedStatement ps) throws SQLException, SGBDException, ClassNotFoundException {
        // executa consulta
        ps.executeUpdate();
        
        // commit
        this.executaSQL("commit");
    }
    
    /**
     * este método executa somente select
     * @param sql
     * @return
     * @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */
    public ResultSet consulta(String sql) throws SQLException, SGBDException, ClassNotFoundException {
        PreparedStatement comando = null;
        ResultSet consulta = null;

        conexao = bd.getConexao();

        // prepara consulta
        comando = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // executa consulta
        consulta = comando.executeQuery();

        // retorna resultado consulta
        return consulta;

    }
    
    /**
     * 
     * @param comando
     * @return
     * @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException 
     */
    public ResultSet consulta(PreparedStatement comando) throws SQLException, SGBDException, ClassNotFoundException {
        ResultSet consulta;

        conexao = bd.getConexao();

        // executa consulta
        consulta = comando.executeQuery();

        // retorna resultado consulta
        return consulta;

    }    
    
    /**
     * este método executa comandos sql que não sejam select
     * @param sql
     * @throws SQLException
     * @throws SGBDException
     * @throws ClassNotFoundException
     */

    public void executaSQL(String sql) throws SQLException, SGBDException, ClassNotFoundException {
        PreparedStatement comando = null;

        conexao = bd.getConexao();

        // prepara consulta
        comando = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // executa comando sql
        comando.executeUpdate();
    }
}
