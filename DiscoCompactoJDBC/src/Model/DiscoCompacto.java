package Model;

import java.time.Year;

public class DiscoCompacto implements Comparable<DiscoCompacto> {

    private int codigo;
    private String nomeMusico;
    private String generoMusica;
    private double preco;
    private String editora;
    private int anoEdicao;

    public DiscoCompacto() {
        
    }

    public DiscoCompacto(int codigo, String nomeMusico, String generoMusica,
            double preco, String editora, int anoEdicao) {
        this.codigo = codigo;
        this.nomeMusico = nomeMusico;
        this.generoMusica = generoMusica;
        this.preco = preco;
        this.editora = editora;
        this.anoEdicao = anoEdicao;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeMusico() {
        return nomeMusico;
    }

    public void setNomeMusico(String nomeMusico) {
        this.nomeMusico = nomeMusico;
    }

    public String getGeneroMusica() {
        return generoMusica;
    }

    public void setGeneroMusica(String generoMusica) {
        this.generoMusica = generoMusica;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoEdicao() {
        return anoEdicao;
    }

    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    public int tempoExistencia() {
        int anoAtual = Year.now().getValue();
        return anoAtual - anoEdicao;
    }

    @Override
    public String toString() {
        return codigo + "-" + nomeMusico + "-" + generoMusica + "-"
                + preco + "-" + editora + "-" + anoEdicao + "-" + tempoExistencia();
    }

    @Override
    public int compareTo(DiscoCompacto outro) {
        return Integer.compare(this.codigo, outro.codigo);
    }
}
