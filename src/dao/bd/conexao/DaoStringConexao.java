package dao.bd.conexao;

import model.vo.VoConexao;

public interface DaoStringConexao {

    public String getStringConexao(VoConexao vo);
    public VoConexao getConfiguracaoDefault();
    public VoConexao getConfiguracaoAlternativa();
}
