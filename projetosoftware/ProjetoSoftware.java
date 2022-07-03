package projetosoftware;

import exception.SGBDException;
import gui.view.GUI_principal;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dao.bd.conexao.Conexao;

public class ProjetoSoftware {

    // atributos de conexão com o Banco de Dados
    public static Conexao conexao;
    private GUI_principal guiCidade;

    // construtor
    public ProjetoSoftware() {
        // criar objeto interface
        guiCidade = new GUI_principal(new JFrame(), true);

        // criar objeto banco de dados
        conexao = new Conexao();
    }

    /**
     * *
     * conectar
     */
    private void conectar() {
        try {
            conexao.conectar();
        } catch (SGBDException | ClassNotFoundException | SQLException ex) {
            // houve erro
            JOptionPane.showMessageDialog(this.getGUI_principal(),
                    "Não foi possível estabelecer uma conexão com o banco de dados!"
                    + "\nO sistema será encerrado",
                    "Conexão com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    /**
     * desconectar
     */
    public void desconectar() {
        try {
            // encerrar conexão
            conexao.desconectar();
            JOptionPane.showConfirmDialog(this.getGUI_principal(),
                    "Conexão com o banco de dados foi encerrada com sucesso",
                    "Conexão com o SGBD", JOptionPane.DEFAULT_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this.getGUI_principal(),
                    "Conexão com o banco de dados não foi encerrada com sucesso",
                    "Conexão com o SGBD", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * chama tela de cidade
     */
    public void mostrarTelaCidade() {
        this.getGUI_principal().getCo().limpar();

        this.getGUI_principal().setVisible(true);
    }

    public static Conexao getConexao() {
        return conexao;
    }

    public GUI_principal getGUI_principal() {
        return guiCidade;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ProjetoSoftware ps = new ProjetoSoftware();

        // conectar
        ps.conectar();

        // mostrar tela cidade
        ps.mostrarTelaCidade();
        
        // desconectar
        ps.desconectar();
    }

}

