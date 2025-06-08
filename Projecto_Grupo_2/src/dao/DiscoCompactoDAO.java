package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.DiscoCompacto;
import controller.Conexao;

public class DiscoCompactoDAO {

    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<DiscoCompacto> lista = new ArrayList<>();

    public void criar(DiscoCompacto disco) {
        conexao = new Conexao().conector();
        String sql = "INSERT INTO discos(codigo, nome_musico, genero_musica, preco, editora, ano_edicao, edicao) VALUES(?,?,?,?,?,?,?)";
        try {
            pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, disco.getCodigo());
            pstm.setString(2, disco.getNomeMusico());
            pstm.setString(3, disco.getGeneroMusica());
            pstm.setDouble(4, disco.getPreco());
            pstm.setString(5, disco.getEditora());
            pstm.setInt(6, disco.getAnoEdicao());
            pstm.setInt(7, disco.getEdicao());

            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
        }
    }

public ArrayList<DiscoCompacto> listarDiscos() {
    ArrayList<DiscoCompacto> lista = new ArrayList<>();
    conexao = new Conexao().conector();
    // Removido o ORDER BY RAND() para manter a ordem de inserção
    String sql = "SELECT * FROM discos"; 

    try {
        pstm = conexao.prepareStatement(sql);
        rs = pstm.executeQuery();

        while (rs.next()) {
            DiscoCompacto d1 = new DiscoCompacto();
            d1.setCodigo(rs.getInt("codigo"));
            d1.setNomeMusico(rs.getString("nome_musico"));
            d1.setGeneroMusica(rs.getString("genero_musica"));
            d1.setPreco(rs.getDouble("preco"));
            d1.setEditora(rs.getString("editora"));
            d1.setAnoEdicao(rs.getInt("ano_edicao"));
            d1.setEdicao(rs.getInt("edicao"));

            lista.add(d1);
        }
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "BaseDados no método listar: " + erro.getMessage());
    }

    return lista;
}

    public void AtualizarDisco(DiscoCompacto d) throws SQLException {
        conexao = new Conexao().conector();
        
        try {
            String sql = "UPDATE discos SET nome_musico=?, genero_musica=?, preco=?, editora=?, ano_edicao=?, edicao=? WHERE codigo=?";
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, d.getNomeMusico());
            pstm.setString(2, d.getGeneroMusica());
            pstm.setDouble(3, d.getPreco());
            pstm.setString(4, d.getEditora());
            pstm.setInt(5, d.getAnoEdicao());
            pstm.setInt(6, d.getEdicao());
            pstm.setInt(7, d.getCodigo());

            pstm.executeUpdate();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + erro.getMessage());
        }
    }

    public void delete(int codigo) {
        conexao = new Conexao().conector();
        String sql = "DELETE FROM discos WHERE codigo=?";

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, codigo);

            pstm.execute();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar: " + erro.getMessage());
        }
    }

}