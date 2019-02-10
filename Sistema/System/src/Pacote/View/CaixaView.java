package Pacote.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lucas Benicio
 */
import Pacote.VO.*;
import Pacote.DAO.CaixaDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

public class CaixaView extends JInternalFrame implements ActionListener {

    Caixa Dados = new Caixa();
    public static int QtdProdutos;

    public static Container ctncaixa, ctnFundo;
    private static JButton btnInformacoes, btnPagar, btnInserir, btnCancela;
    public static String valorVenda[] = {"Código", "produto", "valor", "Quant Por Unid", "Quantidade"};
    public static JTable tblCaixa, tblvendas;
    public static JScrollPane scrvendas, scrCaixa;
    private static DefaultTableModel mdlcaixa, mdlVenda;
    public static JLabel lblValor[], lbldinheiro, lblFundo, lblCompra;
    public static String StrCampos[] = {"Codigo", "Quantidade", "Produtos", "Vendas"};
    public static String strLegendas[] = {"Codigo", "produto", "valor", "Quantidade", "QuantEmEstoque"};
    public static JTextField txtcampos[], txtFianlizar, txtBuscar, txtCompra;
    public static JPanel pnlFundo;
    public static java.util.List<String> strLista = new ArrayList<>();
    public static float ValorCaixa;

    public CaixaView() {

        JLabel lblFundo = new JLabel();
        lblFundo.setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/teste.jpg")));
        int x = this.getHeight();
        int y = this.getWidth();
        lblFundo.setBounds(0, 0, x, y);
        getContentPane().add(lblFundo);

        ctncaixa = new Container();
        ctncaixa.setLayout(null);
        getContentPane().add(ctncaixa);
        this.getBackground();

        tblCaixa = new JTable();
        scrvendas = new JScrollPane(tblCaixa);
        mdlVenda = (DefaultTableModel) tblCaixa.getModel();
        for (String legenda : StrCampos) {
            mdlVenda.addColumn(legenda);
        }
        scrvendas.setBounds(900, 40, 500, 500);
        ctncaixa.add(scrvendas);

        tblvendas = new JTable();
        scrCaixa = new JScrollPane(tblvendas);
        mdlcaixa = (DefaultTableModel) tblvendas.getModel();
        for (String campo : strLegendas) {
            mdlcaixa.addColumn(campo);

        }
        scrCaixa.setBounds(200, 40, 500, 300);
        ctncaixa.add(scrCaixa);

        lblValor = new JLabel[valorVenda.length];
        txtcampos = new JTextField[valorVenda.length];
        for (int i = 0; i < lblValor.length; i++) {
            lblValor[i] = new JLabel(valorVenda[i]);
            txtcampos[i] = new JTextField();
            lblValor[i].setBounds(40, 400 + (i * 40), 100, 20);
            txtcampos[i].setBounds(160, 400 + (40 * i), 200, 20);
            ctncaixa.add(lblValor[i]);
            ctncaixa.add(txtcampos[i]);
        }
        btnInformacoes = new JButton("buscar");
        btnInformacoes.addActionListener(this);
        btnInformacoes.setBounds(1300, 5, 100, 35);
        ctncaixa.add(btnInformacoes);

        btnInserir = new JButton("Adicionar");
        btnInserir.addActionListener(this);
        btnInserir.setBounds(700, 300, 100, 50);
        ctncaixa.add(btnInserir);

        btnPagar = new JButton("Finalizar");
        btnPagar.addActionListener(this);
        btnPagar.setBounds(1300, 540, 100, 50);
        ctncaixa.add(btnPagar);

        txtFianlizar = new JTextField();
        txtFianlizar.setBounds(900, 540, 100, 40);
        ctncaixa.add(txtFianlizar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(1075, 15, 200, 25);
        ctncaixa.add(txtBuscar);

        lbldinheiro = new JLabel("valor da compra");
        lbldinheiro.setBounds(790, 540, 200, 50);
        ctncaixa.add(lbldinheiro);

        lblCompra = new JLabel("Valor em Dinheiro");
        lblCompra.setBounds(1025, 540, 200, 50);
        ctncaixa.add(lblCompra);

        txtCompra = new JTextField();
        txtCompra.setBounds(1175, 540, 100, 40);
        ctncaixa.add(txtCompra);

        btnCancela = new JButton("Remover");
        btnCancela.addActionListener(this);
        btnCancela.setBounds(700, 200, 100, 50);
        ctncaixa.add(btnCancela);

        tblvendas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String Codigo = tblvendas.getValueAt(tblvendas.getSelectedRow(), 0).toString();
                try {
                    PreencheCampos(CaixaDAO.consulta(Codigo));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }
            }

        });
        tblvendas.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                        || evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
                    String Codigo = tblvendas.getValueAt(tblvendas.getSelectedRow(), 0).toString();
                    try {
                        PreencheCampos(CaixaDAO.consulta(Codigo));
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }
                }

            }
        });
        tblCaixa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String tmpCodigo = tblCaixa.getValueAt(tblCaixa.getSelectedRow(), 0).toString();
                try {
                    PreencheCampos(CaixaDAO.consulta(tmpCodigo));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }
            }

        });
        tblCaixa.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                        || evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
                    String tmpCodigo = tblCaixa.getValueAt(tblCaixa.getSelectedRow(), 0).toString();
                    try {
                        PreencheCampos(CaixaDAO.consulta(tmpCodigo));
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }
                }

            }
        });
        carregar();

        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(), SistemaView.dskJanelas.getHeight());
        this.show();
        this.setMaximizable(false);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnInserir) {
            try {
                Caixa DadosCaixa = CaixaDAO.consulta(txtcampos[0].getText());
                QtdProd();
                CarregarCaixa(DadosCaixa);

                ValorCaixa += DadosCaixa.getValor() * QtdProdutos;
                txtFianlizar.setText(String.valueOf(ValorCaixa));

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "coloque a quantidade" + QtdProdutos);
            }

        }
        if (evt.getSource() == btnInformacoes) {
            try {
                Caixa DadoCaixa = CaixaDAO.consulta(txtBuscar.getText());
                if (DadoCaixa == null) {
                    JOptionPane.showMessageDialog(null, "Produto não localizado");
                } else {
                    PreencheCampos(DadoCaixa);
                }
            } catch (Exception erro) {
            }
        }
        if (evt.getSource() == btnCancela) {
            try {
                Caixa DadosCaixa = CaixaDAO.consulta(txtcampos[0].getText());
                QtdProd();

                RemoverProduto();
                ValorCaixa -= DadosCaixa.getValor() * QtdProdutos;
                txtFianlizar.setText(String.valueOf(ValorCaixa));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());

            }
        }
        if (evt.getSource() == btnPagar) {
            float pagar = new Float(txtCompra.getText());
            java.util.List<Float> vetorVendas = new ArrayList<>();
            
            if (pagar >= ValorCaixa) {
                pagar -= ValorCaixa;
                valorCaixa();
                vetorVendas.add(ValorCaixa);
                carregaLista();

                txtCompra.setText(String.valueOf(pagar));
                txtFianlizar.setText("0");
                limparLista();
            }
            zerarCaixa();
            VendasView.temp = ValorCaixa;
        }
    }

    public static void carregar() {

        while (mdlcaixa.getRowCount() > 0) {
            mdlcaixa.removeRow(0);
        }
        try {
            java.util.List<Caixa> vetorCaixa = new ArrayList<>();
            vetorCaixa = CaixaDAO.ConsultaCaixa();
            for (Caixa caixaAtual : vetorCaixa) {
                String dado[] = new String[5];
                dado[0] = caixaAtual.getCodigo();
                dado[1] = caixaAtual.getProduto();
                dado[2] = "" + caixaAtual.getValor();
                dado[3] = "" + caixaAtual.getQuantidade();
                dado[4] = caixaAtual.getQuantidadeEmEstoque();
                mdlcaixa.addRow(dado);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public static void CarregarCaixa(Caixa Dados) {
        /*while(mdlVenda.getRowCount()>0){
            mdlVenda.removeRow(0);
        }*/
        try {
            String Valores[] = new String[4];
            Valores[0] = "" + Dados.getCodigo();
            Valores[1] = "" + QtdProdutos;
            Valores[2] = Dados.getProduto();
            Valores[3] = "" + Dados.getValor();

            mdlVenda.addRow(Valores);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public void VerificaCodigo() {

        Dados.setCodigo(txtcampos[0].getText());

    }

    public static void PreencheCampos(Caixa tmpCaixa) {
        txtcampos[0].setText(tmpCaixa.getCodigo());
        txtcampos[1].setText(tmpCaixa.getProduto());
        txtcampos[2].setText(Float.toString(tmpCaixa.getValor()));
        txtcampos[3].setText(tmpCaixa.getQuantidadeEmEstoque());
    }

    public void QtdProd() {
        txtcampos[4].setText("1");
        Dados.setQtdProdutos(Integer.parseInt(txtcampos[4].getText()));
        if (Dados.getQtdProdutos()==0) {
            Dados.setQtdProdutos(1);
        }
        QtdProdutos = Dados.getQtdProdutos();
        if (QtdProdutos==0) {
            QtdProdutos=1;
        }

    }

    public static Dimension Tela() {

        Toolkit info = Toolkit.getDefaultToolkit();//Criando objeto para acessar informações do sitema
        Dimension res = info.getScreenSize();//pegando resolução da tela e guardando na variavel 'res'
        return res;//retornando a resolução
    }//fechando montarTela()

    public static void RemoverProduto() {
        if (tblCaixa.getSelectedRow() >= 0) {
            mdlVenda.removeRow(tblCaixa.getSelectedRow());
            tblCaixa.setModel(mdlVenda);
        } else {
            JOptionPane.showMessageDialog(null, "favor selecionar uma linha");
        }
    }

    public static void limparLista() {
        while (mdlVenda.getRowCount() > 0) {
            mdlVenda.removeRow(0);
        }
    }

    public static java.util.List<String> carregaLista() {

        try {
            if (ValorCaixa != 0) {
                strLista.add(String.valueOf(ValorCaixa));
                
            }
            return strLista;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "valor zero");
        }
        return null;

    }

    public static void zerarCaixa() {
        ValorCaixa = 0;
    }

    public static float valorCaixa() {
        return ValorCaixa;
    }

}
