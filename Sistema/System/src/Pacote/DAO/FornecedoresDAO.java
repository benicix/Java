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

import Pacote.VO.Fornecedores;
//import Pacote.DAO.ConexaoDAO;
import java.sql.*;
import java.util.*;

public class FornecedoresDAO {

    public static ResultSet rsFornecedores;
    public static Statement stFornecedores;

    public static Fornecedores consultaFornecedores(String tmpcodigo) throws Exception {
        try {
            ConexaoDAO.abreConexao();
            stFornecedores = ConexaoDAO.connSistema.createStatement();
            String sqlbusca;
            sqlbusca = "SELECT *FROM suppliers WHERE supplierId='"+tmpcodigo+"'";
            rsFornecedores = stFornecedores.executeQuery(sqlbusca);
            if (rsFornecedores.next()) {
                Fornecedores tmpfornecedores = new Fornecedores();
                tmpfornecedores.setCodigoFornecedores(rsFornecedores.getString("SupplierId"));
                tmpfornecedores.setNomeEmpresa(rsFornecedores.getString("CompanyName"));
                tmpfornecedores.setNome(rsFornecedores.getString("contactName"));
                tmpfornecedores.setRepresentante(rsFornecedores.getString("contactTitle"));
                tmpfornecedores.setEndereço(rsFornecedores.getString("address"));
                tmpfornecedores.setCidade(rsFornecedores.getString("city"));
                tmpfornecedores.setRegiao(rsFornecedores.getString("region"));
                tmpfornecedores.setCep(rsFornecedores.getString("postalCode"));
                tmpfornecedores.setPais(rsFornecedores.getString("country"));
                tmpfornecedores.setTelefone(rsFornecedores.getString("phone"));
                return tmpfornecedores;
            } else {
                return null;
            }
        } catch (Exception erro) {
            String msg = "Erro na consulta verifique a sintaxe" + erro.getMessage();
            throw new Exception(msg);
        }

    }
    public static void incluirFornecedor(Fornecedores dadosFornecedores)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stFornecedores=ConexaoDAO.connSistema.createStatement();
            String insereFornecedor = "INSERT INTO suppliers(";
            insereFornecedor+="supplierID, CompanyName,ContactName,ContactTitle,";
            insereFornecedor+="address,city,region,PostalCode,Country,Phone)values(";
            insereFornecedor+="'"+dadosFornecedores.getCodigoFornecedores()+"',";
            insereFornecedor+="'"+dadosFornecedores.getNomeEmpresa()+"',";
            insereFornecedor+="'"+dadosFornecedores.getNome()+"',";
            insereFornecedor+="'"+dadosFornecedores.getRepresentante()+"',";
            insereFornecedor+="'"+dadosFornecedores.getEndereço()+"',";
            insereFornecedor+="'"+dadosFornecedores.getCidade()+"',";
            insereFornecedor+="'"+dadosFornecedores.getRegiao()+"',";
            insereFornecedor+="'"+dadosFornecedores.getCep()+"',";
            insereFornecedor+="'"+dadosFornecedores.getPais()+"',";
            insereFornecedor+="'"+dadosFornecedores.getTelefone()+"')";
            stFornecedores.executeUpdate(insereFornecedor);
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            String msg = "Não foi possivel cadastrar Fornecedor"+erro.getMessage();
            throw new Exception(msg);
        }
    }
    public static void excluirFornecedor(Fornecedores codigo)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stFornecedores=ConexaoDAO.connSistema.createStatement();
            String strDeleta="DELETE FROM suppliers WHERE supplierId=";
            strDeleta+="'"+codigo.getCodigoFornecedores()+"'";
            stFornecedores.executeUpdate(strDeleta);
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            String msg="verifique a sintaxe dos campos"+erro.getMessage();
            throw new Exception(msg);
            
        }
    }

    public static List<Fornecedores> listaFornecedores() throws Exception {
        try {
            List<Fornecedores> vetorFuncionarios = new ArrayList<Fornecedores>();
            ConexaoDAO.abreConexao();
            stFornecedores = ConexaoDAO.connSistema.createStatement();
            String msg = "SELECT*FROM suppliers";
            rsFornecedores=stFornecedores.executeQuery(msg);
            while(rsFornecedores.next()){
                Fornecedores dados = new Fornecedores();
                dados.setCodigoFornecedores(rsFornecedores.getString("supplierId"));
                dados.setNomeEmpresa(rsFornecedores.getString("CompanyName"));
                dados.setNome(rsFornecedores.getString("contactName"));
                dados.setTelefone(rsFornecedores.getString("phone"));
                vetorFuncionarios.add(dados);
            }
            ConexaoDAO.fechaConexao();
            return vetorFuncionarios;
        } catch (Exception erro) {
            String msg="Ero na hora de vireficar campos"+erro.getMessage();
            throw new Exception(msg);
        }

    }
}
