/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacote.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import Pacote.VO.*;

/**
 *
 * @author Lucas
 */
public class VendasView extends JInternalFrame implements ActionListener {
    public static Container ctnVenda;
    public static JButton btnAtualizar;
    public static JScrollPane scrVenda;
    public static JTable tblVenda;
    public static DefaultTableModel mdlVenda;
    public static JTextField txtVenda;
    public static String strVenda[]={"horário","vendas"};
    public static Float temp;
    public static JLabel lblVenda;
    public VendasView(){
        this.setTitle("vendas");
        ctnVenda = new Container();
        ctnVenda.setLayout(null);
        this.add(ctnVenda);
        
        tblVenda=new JTable();
        scrVenda=new JScrollPane(tblVenda);
        mdlVenda=(DefaultTableModel) tblVenda.getModel();
        for (String legenda : strVenda) {
            mdlVenda.addColumn(legenda);
        }//Criando e modifica a tabela de vendas.
        scrVenda.setBounds(50, 30, 500, 520);
        ctnVenda.add(scrVenda);
        
        txtVenda=new JTextField();
        txtVenda.setBounds(450, 550, 100, 40);
        ctnVenda.add(txtVenda);
        lblVenda=new JLabel("valor total");
        lblVenda.setBounds(350, 550, 80, 30);
        ctnVenda.add(lblVenda);
        
        //instanciando botões e aplicando configurações
        btnAtualizar=new JButton("Atualizar");
        btnAtualizar.setBounds(600, 100, 100, 60);
        btnAtualizar.addActionListener(this);
        ctnVenda.add(btnAtualizar);
        
        this.setIconifiable(true);
        this.setSize(SistemaView.dskJanelas.getWidth(), SistemaView.dskJanelas.getHeight());
        this.show();
        this.setMaximizable(false);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource()==btnAtualizar) {
            try {
                insereTabela();
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "erro ao atualizar, chamar administrador"+erro.getMessage());
            }
        }
    }
    
    public static void insereTabela(){
        while (mdlVenda.getRowCount()>0) {
            mdlVenda.removeRow(0);
        }
        java.util.List<String> vetorVendas = new ArrayList<>();
        vetorVendas=CaixaView.carregaLista();
        try {
                            
            for (String vetorVenda : vetorVendas) {
                String dados[]= new String[2];
                dados[0]=pegarhora();
                dados[1]=vetorVenda;
                
                mdlVenda.addRow(dados);
                temp+=Float.parseFloat(vetorVenda);
            }
            
            txtVenda.setText(String.valueOf(temp));
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "nada a mostrar"+erro.getMessage());
        }
        
    }
    public static String pegarhora(){//metodo utilizado parar capturar as horas
        
        GregorianCalendar calendario = new GregorianCalendar();
        int horas = calendario.get(GregorianCalendar.HOUR_OF_DAY);
        int minutos = calendario.get(GregorianCalendar.MINUTE);
        String hora= horas+":"+minutos;
        return hora;
    }


}
