package Pacote.View;

import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaView extends JFrame implements ActionListener {
    
    //declaração dos objetos
    public static Container CntPrincipal, CtnTopo, CtnMenu;
    public static JLabel LblBanner;
    public static JButton btnMenu[];
    public static String strModulos[] = {"Clientes", "Funcionarios", "Fornecedores", "Produtos", "Vendas", "Caixa", "Usuários", "Sair"};
    public static JDesktopPane dskJanelas;

    public SistemaView() {
        //this.setTitle("Sistema");
        //JLabel lblFundo = new JLabel(new ImageIcon("img/teste4.jpg"));
        //getContentPane().add(lblFundo);
        
        
        //Construção e configuração
        CntPrincipal = new Container();//Criando Container
        CntPrincipal.setLayout(new BorderLayout());
        CtnTopo = new Container();
        CtnTopo.setLayout(new GridLayout(2, 1));
        
        CtnMenu = new Container();
        CtnMenu.setLayout(new GridLayout(2, 4));
        LblBanner = new JLabel();
        LblBanner.setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/banner10.png")));
        btnMenu = new JButton[8];//define quantidade de botões

        for (int i = 0; i < btnMenu.length; i++) {
            btnMenu[i] = new JButton();
            btnMenu[i].setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/" + i + ".png")));
            LblBanner = new JLabel();
            LblBanner.setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/banner10.png")));
            btnMenu[i].setBackground(Color.WHITE);
            btnMenu[i].setToolTipText(strModulos[i]);
            btnMenu[i].addActionListener(this);
            CtnMenu.add(btnMenu[i]);//Adicionando botões no ctnMenu
        }

        dskJanelas = new JDesktopPane();

        //adicionando elementos no Container
        this.add(CntPrincipal);//adicionando Container principal na janela
        CntPrincipal.add(CtnTopo, BorderLayout.NORTH);//adicionando layout no norte do principal
        
        CtnTopo.add(LblBanner);//adicionando banner na linha do topo
        CtnTopo.add(CtnMenu);
        CntPrincipal.add(dskJanelas, BorderLayout.CENTER);
        //construir login
        //dskJanelas.add(new loginView());
        //desbloqueiaMenu(false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(montarTela());
        this.show();
        this.setResizable(false);

    }

   
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnMenu[0]) {
            //Abrir tela de Clientes
            ClientesView telaClientesView = new ClientesView();
            SistemaView.btnMenu[0].setEnabled(false);
            dskJanelas.add(telaClientesView);
        }
        if (evt.getSource() == btnMenu[1]) {
            FuncionariosView telafuncionarios = new FuncionariosView();
            SistemaView.btnMenu[1].setEnabled(false);
            dskJanelas.add (telafuncionarios);
        }
        if(evt.getSource()==btnMenu[2]){
            FurnecedoresView telaFornecedores = new FurnecedoresView();
            SistemaView.btnMenu[2].setEnabled(false);
            dskJanelas.add (telaFornecedores);
        }
        if(evt.getSource()==btnMenu[3]){
            PrudutosView telaProdutos = new PrudutosView();
            SistemaView.btnMenu[3].setEnabled(false);
            dskJanelas.add(telaProdutos);
        }
        if(evt.getSource()==btnMenu[4]){
            VendasView telaVenda =new VendasView();
            SistemaView.btnMenu[4].setEnabled(false);
            dskJanelas.add(telaVenda);
        }
        if(evt.getSource()==btnMenu[5]){
            CaixaView telaCaixa = new CaixaView();
            JLabel imagem = new JLabel();
            btnMenu[5].setEnabled(false);
            int x = this.getHeight();
            int y = this.getWidth();
            imagem.setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/teste4.jpg")));
            //imagem.setSize(y,x);
            //imagem.setBounds(0, 0, 1000, 1000);
            //imagem.setSize(montarTela());
            dskJanelas.add(telaCaixa);
            
            telaCaixa.getContentPane().add(imagem);
        }
    }

    public static Dimension montarTela() {

        Toolkit info = Toolkit.getDefaultToolkit();//Criando objeto para acessar informações do sitema
        Dimension res = info.getScreenSize();//pegando resolução da tela e guardando na variavel 'res'
        return res;//retornando a resolução
    }//fechando montarTela()

    public static void desbloqueiaMenu(boolean tmpStatus) {
        for (int i = 0; i < btnMenu.length; i++) {
            btnMenu[i].setEnabled(tmpStatus);
        }
    }
}
