package Pacote.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benicio
 */


import Pacote.VO.Fornecedores;
import Pacote.DAO.FornecedoresDAO;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

public class FurnecedoresView extends JInternalFrame implements ActionListener {

    Fornecedores dadosFornecedores = new Fornecedores();
    public static Container ctnfornecedores;
    public static JTextField txtCampos[];
    public static JLabel lblcampos[];
    public static JButton btnbuscar, btnincluir, btnexxcluir, btndesativar;
    public static JTable tblFornecedores;
    public static JScrollPane scrFornecedores;
    public static DefaultTableModel mdlFornecedores;
    public static String strCampos[] = {"Codigo Fornecedores", " Nome Empresa", "Nome", "Representante", "Endereço", "Cidade", "Regiao", "cep", "pais", "telefone"};
    public static String strindice[] = {"Codigo", "Nome Empresa", "Representante", "Telefone"};
    public static JTextField txtbuscar;

    public FurnecedoresView() {
        super("modulo Fornecedores");

        ctnfornecedores = new Container();
        ctnfornecedores.setLayout(null);
        this.add(ctnfornecedores);
        lblcampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];
        for (int i = 0; i < strCampos.length; i++) {
            lblcampos[i] = new JLabel(strCampos[i]);
            txtCampos[i] = new JTextField();
            lblcampos[i].setBounds(10, 40 + (i * 50), 150, 20);
            txtCampos[i].setBounds(170, 40 + (50 * i), 300, 20);
            ctnfornecedores.add(lblcampos[i]);
            ctnfornecedores.add(txtCampos[i]);

        }

        tblFornecedores = new JTable();
        scrFornecedores = new JScrollPane(tblFornecedores);
        mdlFornecedores = (DefaultTableModel) tblFornecedores.getModel();
        for (int i = 0; i < strindice.length; i++) {
            mdlFornecedores.addColumn(strindice[i]);
        }
        scrFornecedores.setBounds(1100, 80, 500, 500);
        ctnfornecedores.add(scrFornecedores);
        //-----------------------------------------------
        btnbuscar = new JButton("Buscar");
        btnbuscar.addActionListener(this);
        btnbuscar.setBounds(1500, 50, 100, 30);
        ctnfornecedores.add(btnbuscar);

        btnincluir = new JButton("Adicionar Fornecedor");
        btnincluir.addActionListener(this);
        btnincluir.setBounds(600, 200, 200, 30);
        ctnfornecedores.add(btnincluir);

        btnexxcluir = new JButton("Excluir Fornecedor");
        btnexxcluir.addActionListener(this);
        btnexxcluir.setBounds(850, 200, 200, 30);
        ctnfornecedores.add(btnexxcluir);

        btndesativar = new JButton("Desativar Fornecedor");
        btndesativar.setBounds(700, 400, 200, 30);
        //ctnfornecedores.add(btndesativar);

        //----------------------------------------------
        txtbuscar = new JTextField();
        txtbuscar.setBounds(1300, 50, 100, 30);
        ctnfornecedores.add(txtbuscar);
        carregarlista();
        tblFornecedores.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String tmpCodigo;
                tmpCodigo = tblFornecedores.getValueAt(tblFornecedores.getSelectedRow(), 0).toString();
                try {
                    preencheCampos(FornecedoresDAO.consultaFornecedores(tmpCodigo));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }
            }
        }
        );

        tblFornecedores.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP
                        || evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

                    String tmpCodigo;
                    tmpCodigo = tblFornecedores.getValueAt(tblFornecedores.getSelectedRow(), 0).toString();
                    try {
                        preencheCampos(FornecedoresDAO.consultaFornecedores(tmpCodigo));
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }
                }
            }
        }
        );

        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(), SistemaView.dskJanelas.getHeight());
        this.show();
        this.setMaximizable(true);
        this.setResizable(true);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnbuscar) {
            try {
                Fornecedores dadosfornecedores = FornecedoresDAO.consultaFornecedores(txtbuscar.getText());
                if (dadosfornecedores == null) {
                    JOptionPane.showMessageDialog(null, "cliente não encontrado");
                } else {
                    carregarlista();
                    preencheCampos(dadosfornecedores);

                }

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }

        }
        if (evt.getSource() == btnincluir) {
            try {
                insereFornecedor();
                FornecedoresDAO.incluirFornecedor(dadosFornecedores);
                carregarlista();
                JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso" + dadosFornecedores.getNomeEmpresa());

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        if (evt.getSource() == btnexxcluir) {
            try {
                dadosFornecedores = FornecedoresDAO.consultaFornecedores(txtCampos[0].getText());
                FornecedoresDAO.excluirFornecedor(dadosFornecedores);
                carregarlista();
                JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
    }

    public void preencheCampos(Fornecedores dados) {
        txtCampos[0].setText(dados.getCodigoFornecedores());
        txtCampos[1].setText(dados.getNomeEmpresa());
        txtCampos[2].setText(dados.getNome());
        txtCampos[3].setText(dados.getRepresentante());
        txtCampos[4].setText(dados.getEndereço());
        txtCampos[5].setText(dados.getCidade());
        txtCampos[6].setText(dados.getRegiao());
        txtCampos[7].setText(dados.getCep());
        txtCampos[8].setText(dados.getPais());
        txtCampos[9].setText(dados.getTelefone());
    }

    public static void carregarlista() {
        while (mdlFornecedores.getRowCount() > 0) {
            mdlFornecedores.removeRow(0);

        }
        try {
            java.util.List<Fornecedores> vetorFornecedores = new ArrayList<Fornecedores>();
            vetorFornecedores = FornecedoresDAO.listaFornecedores();

            for (Fornecedores FornecedoresAtual : vetorFornecedores) {
                String dado[] = new String[4];
                dado[0] = FornecedoresAtual.getCodigoFornecedores();
                dado[1] = FornecedoresAtual.getNomeEmpresa();
                dado[2] = FornecedoresAtual.getNome();
                dado[3] = FornecedoresAtual.getTelefone();
                mdlFornecedores.addRow(dado);

            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public void insereFornecedor() {
        dadosFornecedores.setCodigoFornecedores(txtCampos[0].getText());
        dadosFornecedores.setNomeEmpresa(txtCampos[1].getText());
        dadosFornecedores.setNome(txtCampos[2].getText());
        dadosFornecedores.setRepresentante(txtCampos[3].getText());
        dadosFornecedores.setEndereço(txtCampos[4].getText());
        dadosFornecedores.setCidade(txtCampos[5].getText());
        dadosFornecedores.setRegiao(txtCampos[6].getText());
        dadosFornecedores.setCep(txtCampos[7].getText());
        dadosFornecedores.setPais(txtCampos[8].getText());
        dadosFornecedores.setTelefone(txtCampos[9].getText());
    }

}
