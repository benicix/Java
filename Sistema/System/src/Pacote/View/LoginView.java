package Pacote.View;

import Pacote.DAO.UsuariosDAO;
//import Pacotes.VO.*;
//import Pacote.DAO.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 28253995
 */
import Pacote.VO.usuario;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.PasswordField;
import javax.swing.*;

public class LoginView extends JFrame implements ActionListener {

    public static Container ctnLogin;
    public static JTextField txtusuario;
    public static JPasswordField pswSenha;
    public static JLabel lblUsuario, lblSenha;
    public static JButton btnLogin, btnSair;
    public static String strLogin[] = {"usuario", "senha"};

    public LoginView() {
        
        ctnLogin = new Container();
        ctnLogin.setLayout(null);
        this.add(ctnLogin);
        lblUsuario = new JLabel("usuario");
        lblUsuario.setBounds(250, 10, 100, 100);
        ctnLogin.add(lblUsuario);

        lblSenha = new JLabel("senha");
        lblSenha.setBounds(250, 40, 100, 100);
        ctnLogin.add(lblSenha);

        txtusuario = new JTextField();
        txtusuario.setBounds(330, 45, 100, 30);
        ctnLogin.add(txtusuario);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(330, 85, 100, 30);
        ctnLogin.add(pswSenha);

        btnLogin = new JButton("Entrar");
        btnLogin.setForeground(new Color(0, 0, 0));
        btnLogin.setBackground(new Color(100, 100, 100));
        btnLogin.setBounds(170, 150, 100, 35);
        btnLogin.addActionListener(this);
        ctnLogin.add(btnLogin);

        btnSair = new JButton("Sair");
        btnSair.setForeground(new Color(0, 0, 0));
        btnSair.setBackground(new Color(100, 100, 100));
        btnSair.addActionListener(this);
        btnSair.setBounds(300, 150, 100, 35);
        ctnLogin.add(btnSair);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.show();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnLogin) {

            try {
                usuario tmpUsuario = UsuariosDAO.validaUsuario(txtusuario.getText(), pswSenha.getText());
                if (tmpUsuario == null) {
                    JOptionPane.showMessageDialog(null, "Dados incorretos");
                    txtusuario.grabFocus();
                } else {
                    if (tmpUsuario.getstatus() == 1) {
                        JLabel lblFundo = new JLabel();
                        lblFundo.setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/teste4.jpg")));
                        lblFundo.setSize(montarImagem());
                        
                        new SistemaView().getContentPane().add(lblFundo);
                        
                        SistemaView.desbloqueiaMenu(true);
                        this.dispose();
                    } else if (tmpUsuario.getstatus() == 2) {
                        //SistemaView.desbloqueiaMenu(true);
                        SistemaView.btnMenu[1].setEnabled(false);
                        SistemaView.btnMenu[6].setEnabled(false);
                        this.dispose();//metodo usado para sumir com o internalJframe da tela
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }

        }if(evt.getSource()==btnSair){
            dispose();
        }
    }
    public static Dimension montarImagem(){
        Toolkit info = Toolkit.getDefaultToolkit();//Criando objeto para acessar informações do sitema
        Dimension res = info.getScreenSize();//pegando resolução da tela e guardando na variavel 'res'
        return res;
    }

}
