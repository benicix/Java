package Pacote.DAO;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benicio
 */

import Pacote.VO.produtos;
//import Pacote.DAO.ConexaoDAO;
import java.util.*;
import java.sql.*;

public class ProdutosDAO {

    public static ResultSet rsProdutos;
    public static Statement stProdutos;

    public static produtos consultaProduto(String tmpProduto) throws Exception {
        try {
            ConexaoDAO.abreConexao();
            stProdutos = ConexaoDAO.connSistema.createStatement();
            String sqlBusca = "SELECT *FROM products  WHERE productID='" + tmpProduto + "'";
            rsProdutos = stProdutos.executeQuery(sqlBusca);
            if (rsProdutos.next()) {
                produtos produto = new produtos();
                produto.setIdProduto(rsProdutos.getString("ProductID"));
                produto.setNomeProduto(rsProdutos.getString("Productname"));
                produto.setIdFornecedor(rsProdutos.getString("supplierID"));
                produto.setCategoriaid(rsProdutos.getString("categoryID"));
                produto.setQuantidadeUnidade(rsProdutos.getString("QuantityPerUnit"));
                produto.setPreçoUnidade(rsProdutos.getFloat("UnitPrice"));
                produto.setQuantidadeEstoque(rsProdutos.getInt("UnitsInStock"));
                return produto;

            } else {
                return null;
            }

        } catch (Exception erro) {
            String msg = "select não executado verifique a sintaxe" + erro.getMessage();
            throw new Exception(msg);
        }

    }

    public static void cadastraProduto(produtos incluirProduto) throws Exception {
        try {
            ConexaoDAO.abreConexao();
            stProdutos = ConexaoDAO.connSistema.createStatement();
            String tmpProduto = "INSERT INTO products(";
            tmpProduto += "productID,ProductName,supplierID,CategoryID,QuantityPerUnit,";
            tmpProduto += "UnitPrice,UnitsInStock,discontinued)values(";
            tmpProduto += "'" + incluirProduto.getIdProduto() + "',";
            tmpProduto += "'" + incluirProduto.getNomeProduto() + "',";
            tmpProduto += "'" + incluirProduto.getIdFornecedor() + "',";
            tmpProduto += "'" + incluirProduto.getCategoriaid() + "',";
            tmpProduto += "'" + incluirProduto.getQuantidadeUnidade() + "',";
            tmpProduto += "'" + incluirProduto.getPreçoUnidade() + "',";
            tmpProduto += "'" + incluirProduto.getQuantidadeEstoque() + "',1)";

            stProdutos.executeUpdate(tmpProduto);
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            String msg = "verifique a sintaxe da função update" + erro.getMessage();
            throw new Exception(msg);
        }
    }

    public static List<produtos> listaProduto() throws Exception {
        try {
            List<produtos> vetorProdutos = new ArrayList<produtos>();
            ConexaoDAO.abreConexao();
            stProdutos = ConexaoDAO.connSistema.createStatement();
            String sqlProdutos = "SELECT * FROM products";
            rsProdutos = stProdutos.executeQuery(sqlProdutos);
            while (rsProdutos.next()) {
                produtos produtoAtual =new produtos();
                
                produtoAtual.setIdProduto(rsProdutos.getString("productId"));
                produtoAtual.setNomeProduto(rsProdutos.getString("productName"));
                produtoAtual.setPreçoUnidade(rsProdutos.getFloat("UnitPrice"));
                produtoAtual.setCategoriaid(rsProdutos.getString("CategoryID"));
                produtoAtual.setQuantidadeEstoque(rsProdutos.getInt("UnitsInStock"));
                vetorProdutos.add(produtoAtual);
            }
            ConexaoDAO.fechaConexao();
            return vetorProdutos;
        } catch (Exception erro) {
            String msg="erro na lista de clientes"+erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    public static void Alterar(produtos tmpProdutos)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            //produtos produto=new produtos();
            stProdutos=ConexaoDAO.connSistema.createStatement();
            String Alterar="UPDATE products ";
            //Alterar+="SET productID = "+tmpProdutos.getIdProduto()+",";
            Alterar+="SET productName = '"+tmpProdutos.getNomeProduto()+"',";
            Alterar+=" supplierID = '"+tmpProdutos.getIdFornecedor()+"',";
            Alterar+=" CategoryID="+tmpProdutos.getCategoriaid()+",";
            Alterar+=" QuantityPerUnit = '"+tmpProdutos.getQuantidadeUnidade()+"',";
            Alterar+=" UnitPrice = "+tmpProdutos.getPreçoUnidade()+",";
            Alterar+=" UnitsInStock = "+tmpProdutos.getQuantidadeEstoque()+" ";
            Alterar+="WHERE productid="+tmpProdutos.getIdProduto();
            stProdutos.executeUpdate(Alterar);
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            String msg="erro no update verifique campos "+erro.getMessage();
            throw new Exception(msg);
        }
    }
    public static void deletaProduto(String tmpCodigo)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stProdutos=ConexaoDAO.connSistema.createStatement();
            String Deleta="DELETE FROM products where productid='"+tmpCodigo+"'";
            stProdutos.executeUpdate(Deleta);
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            String msg="Erro no delete "+erro.getMessage();
            throw new Exception(msg);
        }
    }

}
