package Pacote.View;





import Pacote.VO.produtos;
import Pacote.DAO.ProdutosDAO;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.lang.String;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benicio
 */
public class PrudutosView extends JInternalFrame implements ActionListener {

    produtos dadosProdutos = new produtos();
    public static Container ctnprodutos;
    public static JButton btnincluir, btnexcluir, btnBuscar, btnAleterar;
    public static JLabel lblcampos[];
    public static JTable tblprodutos;
    public static JScrollPane scrprodutos;
    public static DefaultTableModel mdlprodutos;
    public static String strcampos[] = {"Codigo do produto", "nome do produto", "codigo do fornecedor", "Categoria", "Quantidade por unidade", "Preço a unidade", "Quantidade em estoque"};
    public static String strlegenda[] = {"Codigo", "Produto", "preço", "categoria", "Quantidade"};
    public static JTextField txtCampos[];
    public static JTextField txtprodutos;

    public PrudutosView() {
        this.setTitle("aekfjnweovn");
        ctnprodutos = new Container();
        ctnprodutos.setLayout(null);
        this.add(ctnprodutos);
        lblcampos = new JLabel[strcampos.length];
        txtCampos = new JTextField[strcampos.length];
        for (int i = 0; i < strcampos.length; i++) {
            lblcampos[i] = new JLabel(strcampos[i]);
            txtCampos[i] = new JTextField();
            lblcampos[i].setBounds(10, 40 + (50 * i), 150, 20);
            txtCampos[i].setBounds(170, 40 + (i * 50), 300, 20);
            ctnprodutos.add(lblcampos[i]);
            ctnprodutos.add(txtCampos[i]);
        }
        tblprodutos = new JTable();
        scrprodutos = new JScrollPane(tblprodutos);
        mdlprodutos = (DefaultTableModel) tblprodutos.getModel();
        for (int i = 0; i < strlegenda.length; i++) {
            mdlprodutos.addColumn(strlegenda[i]);
        }
        scrprodutos.setBounds(1000, 100, 600, 600);
        ctnprodutos.add(scrprodutos);
        //botões
        btnincluir = new JButton("incluir");
        btnincluir.addActionListener(this);
        btnincluir.setBounds(500, 100, 100, 30);
        ctnprodutos.add(btnincluir);

        btnexcluir = new JButton("excluir");
        btnexcluir.addActionListener(this);
        btnexcluir.setBounds(650, 100, 100, 30);
        ctnprodutos.add(btnexcluir);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        btnBuscar.setBounds(1400, 70, 100, 30);
        ctnprodutos.add(btnBuscar);

        txtprodutos = new JTextField();
        txtprodutos.setBounds(1300, 70, 100, 30);
        ctnprodutos.add(txtprodutos);

        btnAleterar = new JButton("Alterar informações");
        btnAleterar.addActionListener(this);
        btnAleterar.setBounds(650, 300, 200, 30);
        ctnprodutos.add(btnAleterar);

        carregaLista();

        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(), SistemaView.dskJanelas.getHeight());
        this.show();
        this.setResizable(false);
        this.setMaximizable(false);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnincluir) {

            try {
                gravaPodutos();
                ProdutosDAO.cadastraProduto(dadosProdutos);
                carregaLista();
                JOptionPane.showMessageDialog(null, "produto" + dadosProdutos.getNomeProduto() + "cadastrado");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        if (evt.getSource() == btnBuscar) {
            try {
                produtos dadosDoProduto = ProdutosDAO.consultaProduto(txtprodutos.getText());
                if (dadosDoProduto == null) {
                    JOptionPane.showMessageDialog(null, "produto não localizado");
                } else {
                    carregaLista();
                    preencheCampos(dadosDoProduto);
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        if (evt.getSource() == btnAleterar) {
            try {
                //ProdutoViewA camadaView = new ProdutoViewA();
                gravaPodutos();
                ProdutosDAO.Alterar(dadosProdutos);
                carregaLista();
                JOptionPane.showMessageDialog(null, "mudança efetuada com sucesso");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        if(evt.getSource()==btnexcluir){
            try {
                DeletarValor();
                ProdutosDAO.deletaProduto(DeletarValor());
                carregaLista();
                JOptionPane.showMessageDialog(null, "produto excluido com sucesso");
                
                
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
    }

    public void gravaPodutos() {
        dadosProdutos.setIdProduto(txtCampos[0].getText());
        dadosProdutos.setNomeProduto(txtCampos[1].getText());
        dadosProdutos.setIdFornecedor(txtCampos[2].getText());
        dadosProdutos.setCategoriaid(txtCampos[3].getText());
        dadosProdutos.setQuantidadeUnidade(txtCampos[4].getText());
        dadosProdutos.setPreçoUnidade(new Float(txtCampos[5].getText()));
        dadosProdutos.setQuantidadeEstoque(new Integer(txtCampos[6].getText()));
    }

    public static void carregaLista() {
        while (mdlprodutos.getRowCount() > 0) {
            mdlprodutos.removeRow(0);
        }
        try {
            java.util.List<produtos> vetorProdutos = new ArrayList<produtos>();
            vetorProdutos = ProdutosDAO.listaProduto();
            for (produtos produtoAtual : vetorProdutos) {
                String dado[] = new String[5];
                dado[0] = produtoAtual.getIdProduto();
                dado[1] = produtoAtual.getNomeProduto();
                dado[2] = "" + produtoAtual.getPreçoUnidade();
                dado[3] = produtoAtual.getCategoriaid();
                dado[4] = "" + produtoAtual.getQuantidadeEstoque();
                mdlprodutos.addRow(dado);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public void preencheCampos(produtos tmpProdutos) {
        txtCampos[0].setText(tmpProdutos.getIdProduto());
        txtCampos[1].setText(tmpProdutos.getNomeProduto());
        txtCampos[2].setText(tmpProdutos.getIdFornecedor());
        txtCampos[3].setText(tmpProdutos.getCategoriaid());
        txtCampos[4].setText(tmpProdutos.getQuantidadeUnidade());
        txtCampos[5].setText(Float.toString(tmpProdutos.getPreçoUnidade()));
        txtCampos[6].setText(Integer.toString(tmpProdutos.getQuantidadeEstoque()));

    }
    public String DeletarValor(){
        String camp=txtCampos[0].getText();
        
        return camp;
    }

}
