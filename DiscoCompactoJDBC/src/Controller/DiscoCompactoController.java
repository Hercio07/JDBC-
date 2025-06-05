package Controller;

import DB.Conexao;
import Model.DiscoCompacto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DiscoCompactoController {
    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;

    public void cadastrarDisco(DiscoCompacto disco) {
        conexao = new Conexao().conector();
        String sql = "INSERT INTO discos(codigo, nome_musico, genero_musica, preco, editora, ano_edicao) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, disco.getCodigo());
            pstm.setString(2, disco.getNomeMusico());
            pstm.setString(3, disco.getGeneroMusica());
            pstm.setDouble(4, disco.getPreco());
            pstm.setString(5, disco.getEditora());
            pstm.setInt(6, disco.getAnoEdicao());
            pstm.execute();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no método cadastrarDisco: " + erro.getMessage());
        } finally {
            fecharConexao();
        }
    }

    public ArrayList<DiscoCompacto> listarDiscos() {
    ArrayList<DiscoCompacto> lista = new ArrayList<>();
    conexao = new Conexao().conector();
    String sql = "SELECT * FROM discos ORDER BY data_insercao";
    
    try {
        pstm = conexao.prepareStatement(sql);
        rs = pstm.executeQuery();

        while (rs.next()) {
            DiscoCompacto disco = new DiscoCompacto();
            disco.setCodigo(rs.getInt("codigo"));
            disco.setNomeMusico(rs.getString("nome_musico"));
            disco.setGeneroMusica(rs.getString("genero_musica"));
            disco.setPreco(rs.getDouble("preco"));
            disco.setEditora(rs.getString("editora"));
            disco.setAnoEdicao(rs.getInt("ano_edicao"));
            lista.add(disco);
        }
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro no método listarDiscos: " + erro.getMessage());
    } finally {
        fecharConexao();
    }
    return lista;
}

public ArrayList<DiscoCompacto> listarDiscosOrdenadosPorCodigo() {
    ArrayList<DiscoCompacto> lista = listarDiscos();
    lista.sort(Comparator.comparingInt(DiscoCompacto::getCodigo));
    return lista;
}

    public void atualizarDisco(DiscoCompacto disco) {
        conexao = new Conexao().conector();
        String sql = "UPDATE discos SET nome_musico = ?, genero_musica = ?, preco = ?, editora = ?, ano_edicao = ? WHERE codigo = ?";

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, disco.getNomeMusico());
            pstm.setString(2, disco.getGeneroMusica());
            pstm.setDouble(3, disco.getPreco());
            pstm.setString(4, disco.getEditora());
            pstm.setInt(5, disco.getAnoEdicao());
            pstm.setInt(6, disco.getCodigo());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Disco atualizado com sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar disco: " + erro.getMessage());
        } finally {
            fecharConexao();
        }
    }

    public void removerDisco(int codigo) {
        conexao = new Conexao().conector();
        String sql = "DELETE FROM discos WHERE codigo = ?";

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, codigo);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Disco removido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover disco: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    public DiscoCompacto pesquisarDiscoPorNome(String nomeMusico) {
        DiscoCompacto disco = null;
        conexao = new Conexao().conector();
        String sql = "SELECT * FROM discos WHERE nome_musico LIKE ?";

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, "%" + nomeMusico + "%");
            rs = pstm.executeQuery();

            if (rs.next()) {
                disco = new DiscoCompacto();
                disco.setCodigo(rs.getInt("codigo"));
                disco.setNomeMusico(rs.getString("nome_musico"));
                disco.setGeneroMusica(rs.getString("genero_musica"));
                disco.setPreco(rs.getDouble("preco"));
                disco.setEditora(rs.getString("editora"));
                disco.setAnoEdicao(rs.getInt("ano_edicao"));
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no método pesquisarDiscoPorNome: " + erro.getMessage());
        } finally {
            fecharConexao();
        }

        return disco;
    }
    /*
    public List<DiscoCompacto> listarDiscosOrdenadosPorNome() {
        List<DiscoCompacto> lista = listarDiscos();
        lista.sort(Comparator.comparing(DiscoCompacto::getNomeMusico));
        return lista;
    }

    public List<DiscoCompacto> listarDiscosPorGenero(String genero) {
        List<DiscoCompacto> todosDiscos = listarDiscos();
        List<DiscoCompacto> discosFiltrados = new ArrayList<>();
        
        for (DiscoCompacto disco : todosDiscos) {
            if (disco.getGeneroMusica().equalsIgnoreCase(genero)) {
                discosFiltrados.add(disco);
            }
        }
        return discosFiltrados;
    }

*/

    private void fecharConexao() {
        try {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}