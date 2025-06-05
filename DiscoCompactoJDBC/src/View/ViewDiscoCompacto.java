
package View;

import DB.Conexao;
import Controller.DiscoCompactoController;
import Model.DiscoCompacto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;




public class ViewDiscoCompacto extends JFrame {

    private final JTextField txtCodigo, txtNomeMusico, txtGenero, txtPreco, txtEditora, txtAnoEdicao;
    private final JTable tabela;
    private final DefaultTableModel modeloTabela;

    public ViewDiscoCompacto() {
        setTitle("Gestão de Discos Compactos");
        setSize(1000, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Código
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 100, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 20, 150, 25);
        add(txtCodigo);

        // Nome do Músico/Banda
        JLabel lblNomeMusico = new JLabel("Músico/Banda:");
        lblNomeMusico.setBounds(20, 60, 100, 25);
        add(lblNomeMusico);

        txtNomeMusico = new JTextField();
        txtNomeMusico.setBounds(130, 60, 150, 25);
        add(txtNomeMusico);

        // Gênero Musical
        JLabel lblGenero = new JLabel("Gênero Musical:");
        lblGenero.setBounds(20, 100, 100, 25);
        add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setBounds(130, 100, 150, 25);
        add(txtGenero);

        // Preço
        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 140, 100, 25);
        add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(130, 140, 150, 25);
        add(txtPreco);

        // Editora
        JLabel lblEditora = new JLabel("Editora:");
        lblEditora.setBounds(20, 180, 100, 25);
        add(lblEditora);

        txtEditora = new JTextField();
        txtEditora.setBounds(130, 180, 150, 25);
        add(txtEditora);

        // Ano de Edição
        JLabel lblAnoEdicao = new JLabel("Ano de Edição:");
        lblAnoEdicao.setBounds(20, 220, 100, 25);
        add(lblAnoEdicao);

        txtAnoEdicao = new JTextField();
        txtAnoEdicao.setBounds(130, 220, 150, 25);
        add(txtAnoEdicao);

        // Botões
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(310, 20, 120, 30);
        add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(310, 60, 120, 30);
        add(btnEditar);

        JButton btnRemover = new JButton("Remover");
        btnRemover.setBounds(310, 100, 120, 30);
        add(btnRemover);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(310, 140, 120, 30);
        add(btnLimpar);

        JButton btnOrdenar = new JButton("Lista Ordenada");
        btnOrdenar.setBounds(310, 180, 120, 30);
        add(btnOrdenar);

        // Tabela
        modeloTabela = new DefaultTableModel(new Object[]{
                "Código", "Músico/Banda", "Gênero", "Preço", "Editora", "Ano Edição"
        }, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(450, 20, 520, 300);
        add(scroll);

        // Eventos
        btnAdicionar.addActionListener(e -> adicionarDisco());
        btnEditar.addActionListener(e -> editarDisco());
        btnRemover.addActionListener(e -> removerDisco());
        btnLimpar.addActionListener(e -> limparCampos());
        btnOrdenar.addActionListener(e -> ordenarDiscos());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    txtCodigo.setText(String.valueOf(tabela.getValueAt(linha, 0)));
                    txtNomeMusico.setText(String.valueOf(tabela.getValueAt(linha, 1)));
                    txtGenero.setText(String.valueOf(tabela.getValueAt(linha, 2)));
                    txtPreco.setText(String.valueOf(tabela.getValueAt(linha, 3)));
                    txtEditora.setText(String.valueOf(tabela.getValueAt(linha, 4)));
                    txtAnoEdicao.setText(String.valueOf(tabela.getValueAt(linha, 5)));
                }
            }
        });

        setVisible(true);
        carregarDiscos();
    }

  

    private void adicionarDisco() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nomeMusico = txtNomeMusico.getText();
            String genero = txtGenero.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            String editora = txtEditora.getText();
            int anoEdicao = Integer.parseInt(txtAnoEdicao.getText());

            DiscoCompacto disco = new DiscoCompacto(codigo, nomeMusico, genero, preco, editora, anoEdicao);
            DiscoCompactoController controller = new DiscoCompactoController();
            controller.cadastrarDisco(disco);
            carregarDiscos();
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar: " + ex.getMessage());
        }
    }

    private void editarDisco() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                String nomeMusico = txtNomeMusico.getText();
                String genero = txtGenero.getText();
                double preco = Double.parseDouble(txtPreco.getText());
                String editora = txtEditora.getText();
                int anoEdicao = Integer.parseInt(txtAnoEdicao.getText());

                DiscoCompacto disco = new DiscoCompacto(codigo, nomeMusico, genero, preco, editora, anoEdicao);
                DiscoCompactoController controller = new DiscoCompactoController();
                controller.atualizarDisco(disco);
                carregarDiscos();
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
            }
        }
    }

    private void removerDisco() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            try {
                int codigo = (int) modeloTabela.getValueAt(linha, 0);
                DiscoCompactoController controller = new DiscoCompactoController();
                controller.removerDisco(codigo);
                carregarDiscos();
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage());
            }
        }
    }
    
     private void carregarDiscos() {
    try {
        DiscoCompactoController controller = new DiscoCompactoController();
        List<DiscoCompacto> discos = controller.listarDiscos(); // Mantém ordem original
        modeloTabela.setRowCount(0);
        for (DiscoCompacto disco : discos) {
            modeloTabela.addRow(new Object[]{
                disco.getCodigo(),
                disco.getNomeMusico(),
                disco.getGeneroMusica(),
                disco.getPreco(),
                disco.getEditora(),
                disco.getAnoEdicao()
            });
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar discos: " + ex.getMessage());
    }
}

private void ordenarDiscos() {
    try {
        DiscoCompactoController controller = new DiscoCompactoController();
        List<DiscoCompacto> lista = controller.listarDiscosOrdenadosPorCodigo(); // Ordena por código
        
        modeloTabela.setRowCount(0);
        for (DiscoCompacto disco : lista) {
            modeloTabela.addRow(new Object[]{
                disco.getCodigo(),
                disco.getNomeMusico(),
                disco.getGeneroMusica(),
                disco.getPreco(),
                disco.getEditora(),
                disco.getAnoEdicao()
            });
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao ordenar: " + ex.getMessage());
    }
}

    private void limparCampos() {
        txtCodigo.setText("");
        txtNomeMusico.setText("");
        txtGenero.setText("");
        txtPreco.setText("");
        txtEditora.setText("");
        txtAnoEdicao.setText("");
    }
}