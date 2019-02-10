package Pacote.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 28253995
 */

import Pacote.VO.funcionarios;
import Pacote.DAO.FuncionariosDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.table.*;
import java.util.List;
import java.util.ArrayList;

public class FuncionariosView extends JInternalFrame implements ActionListener {

    funcionarios dadosFuncionarios = new funcionarios();
    public static JButton btnbusca, btnIncluir, btnexcluir;
    public static JLabel lblfuncionario[];
    public static Container ctnfuncionarios;
    public static JTextField txtcampos[];
    public static JTextField txtbusca;
    public static String strlegenda[] = {"codigo", "nome", "cargo", "cidade"};
    public static String strcampos[] = {"codigoFuncionario", "ultimoNome", "primeiroNome", "cargoFuncionario", "representacao", "dataNascimento", "dataContratacao", "cidade", "região", "enredeço", "Status"};
    public static JTable tblFuncionario;
    public static JScrollPane scrFuncionario;
    public static DefaultTableModel mdlFuncionario;

    public FuncionariosView() {
        ctnfuncionarios = new Container();
        ctnfuncionarios.setLayout(null);
        this.add(ctnfuncionarios);
        lblfuncionario = new JLabel[strcampos.length];
        txtcampos = new JTextField[strcampos.length];
        for (int i = 0; i < strcampos.length; i++) {
            lblfuncionario[i] = new JLabel(strcampos[i]);
            txtcampos[i] = new JTextField();
            lblfuncionario[i].setBounds(10, 40 + (50 * i), 150, 20);
            txtcampos[i].setBounds(170, 40 + (i * 50), 300, 20);
            ctnfuncionarios.add(lblfuncionario[i]);
            ctnfuncionarios.add(txtcampos[i]);

        }
        tblFuncionario = new JTable();
        //tblFuncionario.setGridColor(Color.WHITE);
        scrFuncionario = new JScrollPane(tblFuncionario);
        scrFuncionario.setBackground(Color.red);
        mdlFuncionario = (DefaultTableModel) tblFuncionario.getModel();
        for (String strlegenda1 : strlegenda) {
            mdlFuncionario.addColumn(strlegenda1);
        }

        scrFuncionario.setBounds(1200, 80, 500, 500);
        ctnfuncionarios.add(scrFuncionario);

        //instanciando os botões
        btnbusca = new JButton("Buscar");
        btnbusca.addActionListener(this);
        btnbusca.setBounds(1600, 30, 100, 50);
        ctnfuncionarios.add(btnbusca);

        btnIncluir = new JButton("Incluir Funcionario");
        btnIncluir.addActionListener(this);
        btnIncluir.setBounds(500, 250, 200, 50);
        ctnfuncionarios.add(btnIncluir);

        btnexcluir = new JButton("Excluir Funcionario");
        btnexcluir.addActionListener(this);
        btnexcluir.setBounds(800, 250, 200, 50);
        ctnfuncionarios.add(btnexcluir);

        //---------------------------------------
        txtbusca = new JTextField();
        txtbusca.setBounds(1450, 40, 150, 40);
        ctnfuncionarios.add(txtbusca);

        tblFuncionario.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String tmpCodigo;
                tmpCodigo=tblFuncionario.getValueAt(tblFuncionario.getSelectedRow(), 0).toString();
                try {
                    preenchecampos(FuncionariosDAO.consultaFuncionario(tmpCodigo));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }
            }
        }
        );
        tblFuncionario.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_DOWN||evt.getKeyCode()==KeyEvent.VK_UP
                        ||evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_TAB){
                    String tmpcodigo;
                    tmpcodigo=tblFuncionario.getValueAt(tblFuncionario.getSelectedRow(), 0).toString();
                    try {
                        preenchecampos(FuncionariosDAO.consultaFuncionario(tmpcodigo));
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }
                }
            }
        });

        carregarLista();

        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(), SistemaView.dskJanelas.getHeight());
        this.show();
        this.setMaximizable(false);
        this.setResizable(false);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnbusca) {
            try {
                funcionarios dadosfuncionarios = FuncionariosDAO.consultaFuncionario(txtbusca.getText());
                if (dadosfuncionarios == null) {
                    JOptionPane.showMessageDialog(null, "cliente não localizado");
                } else {
                    carregarLista();
                    preenchecampos(dadosfuncionarios);

                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        if (evt.getSource() == btnIncluir) {
            try {
                if (validacampos() == true) {
                    
                    insereFuncionario();
                    FuncionariosDAO.incluirFuncionarios(dadosFuncionarios);
                    carregarLista();
                    JOptionPane.showMessageDialog(null, "funcionario" + dadosFuncionarios.getPrimeiroNome() + "cadastrado");
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
        if (evt.getSource() == btnexcluir) {
            try {
                dadosFuncionarios=FuncionariosDAO.consultaFuncionario(txtcampos[0].getText());
                FuncionariosDAO.deletaFuncionario(dadosFuncionarios);
                carregarLista();
                JOptionPane.showMessageDialog(null, "funcionario excluido com sucesso");

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }

    }

    public static void carregarLista() {
        while (mdlFuncionario.getRowCount() > 0) {
            mdlFuncionario.removeRow(0);

        }
        try {
            java.util.List<funcionarios> vetorfuncionario = new ArrayList<funcionarios>();
            //if(tmptipo==1){
            vetorfuncionario = FuncionariosDAO.listaFuncionarios();
            // }else if(tmptipo==2){
            //vetorfuncionario= funciionariosDAO.consultaFuncionario(txtbusca.getText());
            //}
            for (funcionarios funcionarioAtual : vetorfuncionario) {
                String dado[] = new String[4];
                dado[0] = funcionarioAtual.getCodigoFuncionario();
                dado[1] = funcionarioAtual.getPrimeiroNome();
                dado[2] = funcionarioAtual.getCargoFuncionario();
                dado[3] = funcionarioAtual.getCidade();
                mdlFuncionario.addRow(dado);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public static void preenchecampos(funcionarios tmpfuncionarios) {
        txtcampos[4].setText(tmpfuncionarios.getCargoFuncionario());
        txtcampos[10].setText(tmpfuncionarios.getCep());
        txtcampos[7].setText(tmpfuncionarios.getCidade());
        txtcampos[0].setText(tmpfuncionarios.getCodigoFuncionario());
        txtcampos[6].setText(tmpfuncionarios.getDataContratacao());
        txtcampos[5].setText(tmpfuncionarios.getDataNascimento());
        txtcampos[9].setText(tmpfuncionarios.getEndereço());
        txtcampos[2].setText(tmpfuncionarios.getPrimeiroNome());
        txtcampos[8].setText(tmpfuncionarios.getRegião());
        txtcampos[3].setText(tmpfuncionarios.getRepresentacao());
        txtcampos[1].setText(tmpfuncionarios.getUltimoNome());

    }

    public void insereFuncionario() {
        dadosFuncionarios.setCodigoFuncionario(txtcampos[0].getText());
        dadosFuncionarios.setUltimoNome(txtcampos[1].getText());
        dadosFuncionarios.setPrimeiroNome(txtcampos[2].getText());
        dadosFuncionarios.setCargoFuncionario(txtcampos[3].getText());
        dadosFuncionarios.setRepresentacao(txtcampos[4].getText());
        dadosFuncionarios.setDataNascimento(txtcampos[5].getText());
        dadosFuncionarios.setDataContratacao(txtcampos[6].getText());
        dadosFuncionarios.setCidade(txtcampos[7].getText());
        dadosFuncionarios.setRegião(txtcampos[8].getText());
        dadosFuncionarios.setCep(txtcampos[10].getText());
        dadosFuncionarios.setEndereço(txtcampos[9].getText());
    }

    public boolean validacampos() {
        for (int i = 0; i < txtcampos.length; i++) {
            if (txtcampos[i].getText().trim().compareTo("") == 0) {
                JOptionPane.showMessageDialog(null, "campo em falta" + txtcampos[i]);
                
                txtcampos[i].grabFocus();
                return false;
            }
        }
        return true;
    }

    public void excluiFuncionario(String tmpcodigo)throws Exception{
        try {
             dadosFuncionarios.setCodigoFuncionario(tmpcodigo);
             
             
        } catch (Exception erro) {
        }
       
        
    }

}
