package View;

import Model.DiscoCompacto;
import dao.DiscoCompactoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.awt.*;

public class ViewDiscoCompacto extends JFrame {

    private JTextField txtCodigo, txtNomeMusico, txtPreco, txtAno;
    private JComboBox<String> comboGenero, comboEditora;
    private JRadioButton rbEdicao1, rbEdicao2, rbEdicao3, rbEdicao4, rbEdicao5;
    private ButtonGroup grupoEdicao;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private java.util.List<DiscoCompacto> listaDiscos = new ArrayList<>();

    public ViewDiscoCompacto() {
        setTitle("Gestão de Discos Compactos");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Painel de entrada de dados
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(null);
        painelEntrada.setBounds(10, 10, 430, 300);
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Dados do Disco"));
        add(painelEntrada);

        // Componentes de entrada
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 30, 100, 25);
        painelEntrada.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 30, 150, 25);
        painelEntrada.add(txtCodigo);

        JLabel lblNomeMusico = new JLabel("Nome do Músico:");
        lblNomeMusico.setBounds(20, 70, 100, 25);
        painelEntrada.add(lblNomeMusico);

        txtNomeMusico = new JTextField();
        txtNomeMusico.setBounds(130, 70, 150, 25);
        painelEntrada.add(txtNomeMusico);

        JLabel lblGenero = new JLabel("Género Musical:");
        lblGenero.setBounds(20, 110, 100, 25);
        painelEntrada.add(lblGenero);

        comboGenero = new JComboBox<>(new String[]{"Rock", "Pop", "Jazz", "Clássica", "Rap"});
        comboGenero.setBounds(130, 110, 150, 25);
        painelEntrada.add(comboGenero);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(20, 150, 100, 25);
        painelEntrada.add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(130, 150, 150, 25);
        painelEntrada.add(txtPreco);

        JLabel lblEditora = new JLabel("Editora:");
        lblEditora.setBounds(20, 190, 100, 25);
        painelEntrada.add(lblEditora);

        comboEditora = new JComboBox<>(new String[]{"Sony", "Universal", "Warner", "EMI"});
        comboEditora.setBounds(130, 190, 150, 25);
        painelEntrada.add(comboEditora);

        JLabel lblAno = new JLabel("Ano de Edição:");
        lblAno.setBounds(20, 230, 100, 25);
        painelEntrada.add(lblAno);

        txtAno = new JTextField();
        txtAno.setBounds(130, 230, 150, 25);
        painelEntrada.add(txtAno);

        JLabel lblEdicao = new JLabel("Edição:");
        lblEdicao.setBounds(20, 270, 100, 25);
        painelEntrada.add(lblEdicao);

        // Botões de rádio para edição
        JPanel painelEdicao = new JPanel(new GridLayout(1, 5, 5, 5));
        painelEdicao.setBounds(130, 270, 250, 25);

        rbEdicao1 = new JRadioButton("1ª");
        rbEdicao2 = new JRadioButton("2ª");
        rbEdicao3 = new JRadioButton("3ª");
        rbEdicao4 = new JRadioButton("4ª");
        rbEdicao5 = new JRadioButton("5ª");

        grupoEdicao = new ButtonGroup();
        grupoEdicao.add(rbEdicao1);
        grupoEdicao.add(rbEdicao2);
        grupoEdicao.add(rbEdicao3);
        grupoEdicao.add(rbEdicao4);
        grupoEdicao.add(rbEdicao5);

        painelEdicao.add(rbEdicao1);
        painelEdicao.add(rbEdicao2);
        painelEdicao.add(rbEdicao3);
        painelEdicao.add(rbEdicao4);
        painelEdicao.add(rbEdicao5);

        painelEntrada.add(painelEdicao);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(3, 2, 5, 5));
        painelBotoes.setBounds(10, 320, 430, 120);
        add(painelBotoes);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnListar = new JButton("Lista Não Ordenada");
        JButton btnListaOrdenda = new JButton("Lista ordenada");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnListaOrdenda);

        // Tabela de discos
        modeloTabela = new DefaultTableModel(new Object[]{"Código", "Nome Músico", "Género", "Preço", "Editora", "Ano", "Edição", "Tempo"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(450, 20, 420, 400);
        add(scroll);

        // Listeners
        btnAdicionar.addActionListener(e -> adicionarDisco());
        btnEditar.addActionListener(e -> editarDisco());
        btnRemover.addActionListener(e -> removerDisco());
        btnLimpar.addActionListener(e -> limparCampos());
        btnListar.addActionListener(e -> carregarTabela());
        btnListaOrdenda.addActionListener(e -> ordenarLista());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    DiscoCompacto disco = listaDiscos.get(linha);
                    txtCodigo.setText(String.valueOf(disco.getCodigo()));
                    txtNomeMusico.setText(disco.getNomeMusico());
                    comboGenero.setSelectedItem(disco.getGeneroMusica());
                    txtPreco.setText(String.valueOf(disco.getPreco()));
                    comboEditora.setSelectedItem(disco.getEditora());
                    txtAno.setText(String.valueOf(disco.getAnoEdicao()));

                    // Selecionar o radio button correto
                    switch (disco.getEdicao()) {
                        case 1:
                            rbEdicao1.setSelected(true);
                            break;
                        case 2:
                            rbEdicao2.setSelected(true);
                            break;
                        case 3:
                            rbEdicao3.setSelected(true);
                            break;
                        case 4:
                            rbEdicao4.setSelected(true);
                            break;
                        case 5:
                            rbEdicao5.setSelected(true);
                            break;
                    }
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent evt) {
                carregarTabela();
            }
        });

        setVisible(true);
    }

    private int getEdicaoSelecionada() {
        if (rbEdicao1.isSelected()) {
            return 1;
        }
        if (rbEdicao2.isSelected()) {
            return 2;
        }
        if (rbEdicao3.isSelected()) {
            return 3;
        }
        if (rbEdicao4.isSelected()) {
            return 4;
        }
        if (rbEdicao5.isSelected()) {
            return 5;
        }
        return 1; // Default para primeira edição
    }

    private void adicionarDisco() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nome_musico = txtNomeMusico.getText();
            String genero_musica = comboGenero.getSelectedItem().toString();
            double preco = Double.parseDouble(txtPreco.getText());
            String editora = comboEditora.getSelectedItem().toString();
            int ano_edicao = Integer.parseInt(txtAno.getText());
            int edicao = getEdicaoSelecionada();

            DiscoCompacto disco = new DiscoCompacto(codigo, nome_musico, genero_musica,
                    preco, editora, ano_edicao, edicao);
            new DiscoCompactoDAO().criar(disco);

            carregarTabela();
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
                String nome_musico = txtNomeMusico.getText();
                String genero_musica = comboGenero.getSelectedItem().toString();
                double preco = Double.parseDouble(txtPreco.getText());
                String editora = comboEditora.getSelectedItem().toString();
                int ano_edicao = Integer.parseInt(txtAno.getText());
                int edicao = getEdicaoSelecionada();

                DiscoCompacto disco = new DiscoCompacto(codigo, nome_musico, genero_musica,
                        preco, editora, ano_edicao, edicao);
                new DiscoCompactoDAO().AtualizarDisco(disco);

                JOptionPane.showMessageDialog(this, "Disco atualizado com sucesso!");
                carregarTabela();
                limparCampos();
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar disco: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um disco para editar.");
        }
    }

    private void removerDisco() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            try {
                int codigo = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
                new DiscoCompactoDAO().delete(codigo);
                carregarTabela();
                limparCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione uma linha para remover.");
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNomeMusico.setText("");
        comboGenero.setSelectedIndex(0);
        txtPreco.setText("");
        comboEditora.setSelectedIndex(0);
        txtAno.setText("");
        grupoEdicao.clearSelection();
        rbEdicao1.setSelected(true); // Seleciona a primeira edição por padrão
    }

 private void carregarTabela() {
    listaDiscos = new DiscoCompactoDAO().listarDiscos();
    modeloTabela.setRowCount(0);
    for (DiscoCompacto disco : listaDiscos) {
        modeloTabela.addRow(new Object[]{
            disco.getCodigo(), 
            disco.getNomeMusico(), 
            disco.getGeneroMusica(),
            disco.getPreco(), 
            disco.getEditora(), 
            disco.getAnoEdicao(),
            disco.getEdicao() + "ª edição",
            disco.tempoExistencia()
        });
    }
}

private void ordenarLista() {
    // Cria uma cópia da lista para ordenar sem alterar a original
    ArrayList<DiscoCompacto> listaOrdenada = new ArrayList<>(listaDiscos);
    Collections.sort(listaOrdenada);
    
    modeloTabela.setRowCount(0);
    for (DiscoCompacto disco : listaOrdenada) {
        modeloTabela.addRow(new Object[]{
            disco.getCodigo(), 
            disco.getNomeMusico(), 
            disco.getGeneroMusica(),
            disco.getPreco(), 
            disco.getEditora(), 
            disco.getAnoEdicao(),
            disco.getEdicao() + "ª edição",
            disco.tempoExistencia()
        });
    }
}
}
