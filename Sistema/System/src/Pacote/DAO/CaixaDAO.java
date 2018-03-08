package Pacote.DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Benicio
 */
import Pacote.VO.Caixa;
import Pacote.DAO.ConexaoDAO;
import java.sql.*;
import java.util.*;

public class CaixaDAO {

    public static ResultSet rsCaixa;
    public static Statement stCaixa;

    public static Caixa consulta(String tmpCodigo) throws Exception {
        try {
            ConexaoDAO.abreConexao();
            stCaixa = ConexaoDAO.connSistema.createStatement();
            String SqlBusca = "SELECT *FROM products  WHERE productID='" + tmpCodigo + "'";
            rsCaixa = stCaixa.executeQuery(SqlBusca);
            if (rsCaixa.next()) {
                Caixa caixa = new Caixa();
                caixa.setCodigo(rsCaixa.getString("productID"));
                caixa.setProduto(rsCaixa.getString("ProductName"));
                //caixa.setQuantidade(rsCaixa.getInt(0));
                caixa.setQuantidadeEmEstoque(rsCaixa.getString("QuantityPerUnit"));
                caixa.setValor(rsCaixa.getFloat("UnitPrice"));
                return caixa;
            } else {
                return null;
            }
        } catch (Exception erro) {
            String msg = "erro no Select" + erro.getMessage();
            throw new Exception(msg);
        }

    }

    public static List<Caixa> ConsultaCaixa() throws Exception {
        List<Caixa> tmpCaixa = new ArrayList<>();
        try {
            ConexaoDAO.abreConexao();
           
            stCaixa = ConexaoDAO.connSistema.createStatement();
            String sqlBusca = "SELECT * FROM Products";
            rsCaixa=stCaixa.executeQuery(sqlBusca);
            while(rsCaixa.next()){
                Caixa caixa=new Caixa();
                caixa.setCodigo(rsCaixa.getString("ProductId"));
                caixa.setProduto(rsCaixa.getString("ProductName"));
                caixa.setValor(rsCaixa.getFloat("UnitPrice"));
                caixa.setQuantidade(rsCaixa.getInt("UnitsInStock"));
                caixa.setQuantidadeEmEstoque(rsCaixa.getString("QuantityPerUnit"));
                tmpCaixa.add(caixa);
                
                
            }
            ConexaoDAO.fechaConexao();
            return tmpCaixa;
        } catch (Exception erro) {
            String msg="erro na lista select"+erro.getMessage();
            throw new Exception(msg);
        }
    }
    public static void AlterarProdutos(){
        try {
            ConexaoDAO.abreConexao();
            
        } catch (Exception e) {
        }
    }
}
