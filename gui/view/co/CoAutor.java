package gui.view.co;

import exception.AutorException;
import gui.view.GUI_principal;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.bo.BoAutor;
import model.vo.VoAutor;
import model.vo.VoConsulta;

public class CoAutor implements I_CO {

    // atributos
    private VoAutor vo;
    private GUI_principal gui;
    private BoAutor bo;

    // construtor
    public CoAutor(GUI_principal gui) {
        this.gui = gui;
        this.vo = new VoAutor();
        this.bo = new BoAutor(vo);

    }

    /**
     */
    @Override
    public void cadastrar() {
        //Autor\\
        // create vo
        this.setVo(new VoAutor(this.getGui().gettb_autor().getText(), this.getGui().gettb_ano().getText(), this.getGui().gettb_titulo().getText()));

        // salvar
        this.getBo().setVo(this.getVo());
        try {
            if (this.getBo().cadastrar()) {
                JOptionPane.showMessageDialog(this.getGui(), "autor cadastrado com sucesso",
                        "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro(),
                        "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
            }
        } catch (AutorException ex) {
            // houve erro
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
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
        // pergunta se quer realmente excluir
        int resp = JOptionPane.showConfirmDialog(this.getGui(), "Desejas realmente excluir"
                + " este autor?",
                "Cadastrar autor", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.NO_OPTION) {
            this.limpar();
            return;
        }

        try {
            // excluir
            if (this.getBo().excluir()) {
                JOptionPane.showMessageDialog(this.getGui(), "autor excluído com sucesso",
                        "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro(),
                        "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
            }
        } catch (AutorException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
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
            // obter lista de autores
            VoConsulta lista = this.getBo().obterLista();

            // cria objeto controlador da tela de consulta
            String title = "Consulta lista de autor";
            CoConsulta controllerConsulta = new CoConsulta((JFrame) this.getGui().getParent(),
                    true, lista, title, 1);

            // mostrar consulta
            controllerConsulta.consultar();

            // pega retorno
            if (controllerConsulta.isRetorno()) {
                // pega objeto selecionado
                ArrayList objeto = controllerConsulta.getObjetoConsulta();
                System.out.println(objeto);

                // cria objeto vo
                this.setVo(new VoAutor(objeto.get(0).toString(), objeto.get(1).toString(), objeto.get(2).toString()));
                

                // set vo em bo
                this.getBo().setVo(this.getVo());

                // mostrar campos
                this.setCampos();
            }
        } catch (AutorException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Cadastrar autor", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * *
     *
     */
    @Override
    public void consultar() {
        // testa se o código foi informado
        String aux = this.getGui().gettb_autor().getText();
        if (aux.trim().isEmpty()) {
            // codigo não informado
            limpar();
            return;
        }

        try {
            // pesquisar
            if (this.getBo().consultar()) {
                // set vo
                this.setVo(this.getBo().getVo());

                // atribuir
                this.setCampos();
            }
        } catch (AutorException ex) {
            JOptionPane.showMessageDialog(this.getGui(), this.getBo().getErro() + "\n"
                    + ex.getMessage(),
                    "Consulta o Autor", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * *
     *
     */
    public void limpar() {
        this.getGui().gettb_autor().setText("");
        this.getGui().gettb_titulo().setText("");
        this.getGui().gettb_ano().setText("");
    }

    /**
     * *
     *
     */
    public void setCampos() {
        this.getGui().gettb_autor().setText(this.getVo().getNome());
    }

    // getters and setters
    public VoAutor getVo() {
        return vo;
    }

    public void setVo(VoAutor vo) {
        this.vo = vo;
    }

    public GUI_principal getGui() {
        return gui;
    }

    public void setGui(GUI_principal gui) {
        this.gui = gui;
    }

    public BoAutor getBo() {
        return bo;
    }

    public void setBo(BoAutor bo) {
        this.bo = bo;
    }
}
