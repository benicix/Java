package Pacote.View;




import Pacote.VO.Clientes;
import Pacote.DAO.ClientesDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.table.*;
import java.util.*;
import java.util.ArrayList;

public class ClientesView extends JInternalFrame implements ActionListener {

    Clientes dadosClientes = new Clientes();

    public static Container ctnClientes;
    public static String strLegenda[] = {"código", "nome", "cidade", "E-mail"};
    public static String strCampos[] = {"Código", "Nome da Empresa", "Nome do Representante", "Cargo", "Endereço",
        "Cidade", "Estado", "País", "CEP", "Telefone", "E-mail", "Status"};
    public static JLabel lblCampos[];
    public static JTextField txtCampos[];
    public static JTable tblClientes;
    public static JScrollPane scrClientes;
    public static DefaultTableModel mdlClientes;
    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca, btnRestaurar, btnNovo, btnSalvar, btnStatus;
    public static JComboBox cmbBusca;

    public ClientesView() {
        super("Modulos Clientes");
        ctnClientes = new Container();//instancia do container
        ctnClientes.setLayout(null);//marcando aqui
        this.add(ctnClientes);
        //Construção dos objetos
        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];
        for (int i = 0; i < strCampos.length; i++) {
            //Construção das labels(rótulos)
            lblCampos[i] = new JLabel(strCampos[i]);
            txtCampos[i] = new JTextField();
            lblCampos[i].setBounds(10, 40 + (50 * i), 150, 20);
            txtCampos[i].setBounds(170, 40 + (i * 50), 300, 20);
            ctnClientes.add(lblCampos[i]);
            ctnClientes.add(txtCampos[i]);
            //construção
        }
        tblClientes = new JTable();
        scrClientes = new JScrollPane(tblClientes);

        mdlClientes = (DefaultTableModel) tblClientes.getModel();
        for (String strLegenda1 : strLegenda) {
            mdlClientes.addColumn(strLegenda1);
        }
        scrClientes.setBounds(900, 80, 500, 500);
        ctnClientes.add(scrClientes);
        //botões

        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setBounds(550, 100, 100, 30);
        ctnClientes.add(btnNovo);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(650, 100, 100, 30);
        ctnClientes.add(btnSalvar);

        btnStatus = new JButton("Desativar");
        btnStatus.addActionListener(this);
        btnStatus.setBounds(650, 60, 100, 30);
        ctnClientes.add(btnStatus);

        //------------------------------------------
        btnBusca = new JButton("busca");
        btnBusca.addActionListener(this);
        btnBusca.setBounds(1250, 40, 100, 30);
        ctnClientes.add(btnBusca);

        txtBusca = new JTextField();
        txtBusca.setBounds(1100, 40, 150, 30);
        ctnClientes.add(txtBusca);

        String strBusca[] = {"Por Código", "Por Nome"};
        cmbBusca = new JComboBox(strBusca);
        cmbBusca.setBounds(1250, 10, 100, 30);
        ctnClientes.add(cmbBusca);

        lblBusca = new JLabel("busca do Código");
        lblBusca.setBounds(1000, 40, 200, 30);
        ctnClientes.add(lblBusca);

        btnRestaurar = new JButton("restaurar");
        btnRestaurar.setBounds(1200, 580, 150, 50);
        btnRestaurar.addActionListener(this);
        ctnClientes.add(btnRestaurar);

        //------------------------------------------------
        carregaLista(1);
        tblClientes.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String tmpCodigo;
                tmpCodigo = tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString();
                try {
                    preencheCampos(ClientesDAO.consultaCliente(tmpCodigo, 1));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }
            }
        }
        );

        tblClientes.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP
                        || evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {

                    String tmpCodigo;
                    tmpCodigo = tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString();
                    try {
                        preencheCampos(ClientesDAO.consultaCliente(tmpCodigo, 1));
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
        this.setMaximizable(false);
        this.setResizable(false);
    }

    public static void preencheCampos(Clientes tmpCliente) {
        if (tmpCliente.getStatus() == 0) {
            btnStatus.setText("Ativar");
            for (int i = 0; i < strCampos.length; i++) {

                txtCampos[i].setForeground(Color.red);
            }
        } else {
            btnStatus.setText("Desativar");
            for (int i = 0; i < strCampos.length; i++) {
                txtCampos[i].setForeground(Color.black);
            }
        }
        txtCampos[0].setText(tmpCliente.getCodigo());
        txtCampos[1].setText(tmpCliente.getNomeEmpresa());
        txtCampos[2].setText(tmpCliente.getNomeRepresentante());
        txtCampos[3].setText(tmpCliente.getCargo());
        txtCampos[4].setText(tmpCliente.getEndereco());
        txtCampos[5].setText(tmpCliente.getCidade());
        txtCampos[6].setText(tmpCliente.getEstado());
        txtCampos[7].setText(tmpCliente.getPais());
        txtCampos[8].setText(tmpCliente.getCep());
        txtCampos[9].setText(tmpCliente.getTelefone());
        txtCampos[10].setText(tmpCliente.getEmail());
        //txtCampos[11].setText(tmpCliente.getstatus());

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnBusca) {
            if (cmbBusca.getSelectedIndex() == 0) {//se o primeiro item da lista estiver selecionado
                try {
                    Clientes dadosCliente = ClientesDAO.consultaCliente(txtBusca.getText(), 1);
                    if (dadosCliente == null) {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                    } else {
                        carregaLista(1);
                        preencheCampos(dadosCliente);
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage());
                }
            } else if (cmbBusca.getSelectedIndex() == 1) {//busca por nome
                try {
                    carregaLista(2);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Cliente não localizado");
                }

            }
        } else if (evt.getSource() == btnNovo) {
            limpaCampos();
        } else if (evt.getSource() == btnSalvar) {
            try {
                if (validaCampos() == true) {
                    gravaCliente();
                    ClientesDAO.cadastraCliente(dadosClientes);
                    carregaLista(1);
                    JOptionPane.showMessageDialog(null, "cliente" + dadosClientes.getNomeEmpresa() + "cadastrado");
                }

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        } else if (evt.getSource() == btnStatus) {
            try {

                if (txtCampos[0].getText().trim().compareTo("") == 0) {
                    JOptionPane.showMessageDialog(null, "não há clientes selecionados");
                } else {
                    Clientes dados = ClientesDAO.consultaCliente(txtCampos[0].getText(), 1);
                    String codigo = dados.getCodigo();
                    int status = dados.getStatus();

                    ClientesDAO.alteraStatus(codigo, status);
                    JOptionPane.showMessageDialog(null, "status alterado");
                    dados = ClientesDAO.consultaCliente(txtCampos[0].getText(), 1);
                    preencheCampos(dados);
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());

            }
        } else if (evt.getSource() == btnRestaurar) {
            carregaLista(1);

        }
    }

    // nao usamos throw Exception pois eh ela mesma que mostra o Exception
    public static void carregaLista(int tmpTipo) {
        while (mdlClientes.getRowCount() > 0) {
            mdlClientes.removeRow(0); // limpa a lista
        }

        try {
            java.util.List<Clientes> vetorClientes = new ArrayList<Clientes>();
            if (tmpTipo == 1) {
                vetorClientes = ClientesDAO.listaClientes();
            } else if (tmpTipo == 2) {
                vetorClientes = ClientesDAO.consultaNome(txtBusca.getText());
            }
            // para cada cliente existente no vetorCLientes faca
            for (Clientes clienteAtual : vetorClientes) {
                String dados[] = new String[4];
                dados[0] = clienteAtual.getCodigo();
                dados[1] = clienteAtual.getNomeEmpresa();
                dados[2] = clienteAtual.getCidade();
                dados[3] = clienteAtual.getEmail();
                mdlClientes.addRow(dados);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }

    }

    public void limpaCampos() {
        for (int i = 0; i < strCampos.length; i++) {
            txtCampos[i].setText(null);
        }
    }

    public void gravaCliente() {
        dadosClientes.setCodigo(txtCampos[0].getText());
        dadosClientes.setNomeEmpresa(txtCampos[1].getText());
        dadosClientes.setNomeRepresentante(txtCampos[2].getText());
        dadosClientes.setCargo(txtCampos[3].getText());
        dadosClientes.setEndereco(txtCampos[4].getText());
        dadosClientes.setCidade(txtCampos[5].getText());
        dadosClientes.setEstado(txtCampos[6].getText());
        dadosClientes.setCep(txtCampos[7].getText());
        dadosClientes.setPais(txtCampos[8].getText());
        dadosClientes.setTelefone(txtCampos[9].getText());
        dadosClientes.setEmail(txtCampos[10].getText());

    }

    public static boolean validaCampos() {
        for (int i = 0; i < txtCampos.length; i++) {
            if (txtCampos[i].getText().trim().compareTo("") == 0) {
                JOptionPane.showMessageDialog(null, "preencha o campo" + strCampos[i]);
                txtCampos[i].grabFocus();//move o cursor para a caixa de texte
                return false;
            }
        }
        return true;
    }

}
