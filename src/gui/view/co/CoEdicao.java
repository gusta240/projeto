package gui.view.co;

import exception.EdicaoException;
import gui.view.GUI_principal;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.bo.BoEdicao;
import model.vo.VoEdicao;
import model.vo.VoConsulta;
import projetosoftware.Conversao;

public class CoEdicao implements I_CO {

    // atributos
    private VoEdicao vo;
    
    private GUI_principal gui;

    private BoEdicao bo;

    // construtor
    public CoEdicao(GUI_principal gui) {
        this.gui = gui;
        this.vo = new VoEdicao();
        this.bo = new BoEdicao(vo);

    }

    /**
     */
    @Override
    public void cadastrar() {
        // pega valores
        int codigo = Conversao.converteStringToInteiro(this.getGui().getjTextField1().getText());

        //Autor\\
        // create vo
        this.setVo(new VoEdicao(codigo,
                this.getGui().getjTextField2().getText(),
                this.getGui().getjTextField3().getText()));

        // salvar
        this.getBo().setVo(this.getVo());
        try {
            if (this.getBo().cadastrar()) {
                JOptionPane.showMessageDialog(this.getGui(), "Cidade cadastrada com sucesso",
                        "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro(),
                        "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
            }
        } catch (EdicaoException ex) {
            // houve erro
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
        }

        // limpar tela
        this.limpar();
    }

    /**
     * *
     *
     */
    @Override
    public void excluir() {
        // pega valores
        int codigo = Conversao.converteStringToInteiro(this.getGui().getjTextField1().getText());

        // pergunta se quer realmente excluir
        int resp = JOptionPane.showConfirmDialog(this.getGui(), "Desejas realmente excluir"
                + " esta cidade?",
                "Cadastrar Cidade", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.NO_OPTION) {
            this.limpar();
            return;
        }

        // create vo
        this.setVo(new VoEdicao(codigo));

        try {
            // excluir
            if (this.getBo().excluir()) {
                JOptionPane.showMessageDialog(this.getGui(), "Cidade excluído com sucesso",
                        "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro(),
                        "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
            }
        } catch (EdicaoException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
        }

        // limpar tela
        this.limpar();
    }

    /**
     * *
     *
     */
    public void obterLista() {
        try {
            // obter lista de cidades
            VoConsulta lista = this.getBo().obterLista();

            // cria objeto controlador da tela de consulta
            String title = "Consulta lista de cidades";
            CoConsulta controllerConsulta = new CoConsulta((JFrame) this.getGui().getParent(),
                    true, lista, title, 1);

            // mostrar consulta
            controllerConsulta.consultar();

            // pega retorno
            if (controllerConsulta.isRetorno()) {
                // pega objeto selecionado
                ArrayList objeto = controllerConsulta.getObjetoConsulta();

                // cria objeto vo
                this.setVo(new VoEdicao(Integer.parseInt(objeto.get(0).toString()),
                        objeto.get(1).toString(),
                        objeto.get(2).toString()));

                // set vo em bo
                this.getBo().setVo(this.getVo());

                // mostrar campos
                this.setCampos();
            }
        } catch (EdicaoException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * *
     *
     */
    @Override
    public void consultar() {
        // testa se o código foi informado
        String aux = this.getGui().getjTextField1().getText();
        if (aux.trim().isEmpty()) {
            // codigo não informado
            limpar();
            return;
        }

        // pega código
        int codigo = Integer.parseInt(aux);

        // create vo
        this.setVo(new VoEdicao(codigo));

        // validar codigo
        this.getBo().setVo(this.getVo());
        if (!this.getBo().validarCodigo()) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro(),
                    "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
            this.limpar();
            return;
        }

        try {
            // pesquisar
            if (this.getBo().consultar()) {
                // set vo
                this.setVo(this.getBo().getVo());

                // atribuir
                this.setCampos();
            } else {
                // request focus
                this.getGui().getjTextField2().requestFocus();
            }
        } catch (EdicaoException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * *
     *
     */
    public void limpar() {
        // pega o próximo código livre
        int codigo;
        try {
            codigo = this.getBo().proximoCodigoLivre();
            this.getGui().getjTextField1().setText(String.valueOf(codigo));
            this.getGui().getjTextField2().setText("");
            this.getGui().getjTextField3().setText("");
        } catch (EdicaoException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar Cidade", JOptionPane.ERROR_MESSAGE);
        }

        // request focus
        this.getGui().getjTextField1().requestFocus();
    }

    /**
     * *
     *
     */
    public void setCampos() {
        this.getGui().getjTextField1().setText(String.valueOf(this.getVo().getCodigo()));
        this.getGui().getjTextField2().setText(this.getVo().getNome());
        this.getGui().getjTextField3().setText(this.getVo().getUf());
        // request focus
        this.getGui().getjTextField2().requestFocus();
    }

    // getters and setters
    public VoEdicao getVo() {
        return vo;
    }

    public void setVo(VoEdicao vo) {
        this.vo = vo;
    }

    public GUI_principal getGui() {
        return gui;
    }

    public void setGui(GUI_principal gui) {
        this.gui = gui;
    }

    public BoEdicao getBo() {
        return bo;
    }

    public void setBo(BoEdicao bo) {
        this.bo = bo;
    }
}
