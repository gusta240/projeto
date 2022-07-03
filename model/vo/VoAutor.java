package model.vo;

public class VoAutor implements I_VO {
    // atributos
    private String nome;
    private String ano;
    private String titulo;

    
    // construtor
    public VoAutor(String nome, String ano, String titulo) {
        this.nome = nome;
        this.ano = ano;
        this.titulo = titulo;
    }

    // construtor
    public VoAutor() {
        this.nome = "";
        this.ano = "";
        this.titulo = "";
    }

    // getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "VoAutor{" + " nome_autor=" + nome + '}' + " VoEdicao{" + " ano_edicao=" + ano + '}' + "VoLivro{" + "titulo_livro=" + titulo + '}';
    }
}
