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

import Pacote.VO.funcionarios;
//import Pacote.DAO.ConexaoDAO;
import java.sql.*;
import java.util.*;

public class FuncionariosDAO {

    public static ResultSet rsFuncionario;
    public static Statement stFuncionario;

    public static funcionarios consultaFuncionario(String tmpBusca) throws Exception {
        try {
            ConexaoDAO.abreConexao();
            stFuncionario = ConexaoDAO.connSistema.createStatement();
            String sqlbusca = null;
            sqlbusca = "select *from employees WHERE EmployeeID='"+tmpBusca+"'";
            rsFuncionario = stFuncionario.executeQuery(sqlbusca);
            if(rsFuncionario.next()){
                funcionarios tmpfuncionarios = new funcionarios();
                tmpfuncionarios.setCargoFuncionario(rsFuncionario.getString("titleofcourtesy"));
                tmpfuncionarios.setStatus(rsFuncionario.getInt("status"));
                tmpfuncionarios.setCidade(rsFuncionario.getString("city"));
                tmpfuncionarios.setCodigoFuncionario(rsFuncionario.getString("employeeID"));
                tmpfuncionarios.setDataContratacao(rsFuncionario.getString("hiredate"));
                tmpfuncionarios.setDataNascimento(rsFuncionario.getString("birthdate"));
                tmpfuncionarios.setEndereço(rsFuncionario.getString("address"));
                tmpfuncionarios.setPrimeiroNome(rsFuncionario.getString("firstname"));
                tmpfuncionarios.setRegião(rsFuncionario.getString("region"));
                tmpfuncionarios.setRepresentacao(rsFuncionario.getString("title"));
                tmpfuncionarios.setUltimoNome(rsFuncionario.getString("lastname"));
                return tmpfuncionarios;
                
                
            }else{
                return null;
            }
            
        } catch (Exception erro) {
            String msg = "verifique sintaxe das tabelas"+erro.getMessage();
            throw new Exception(msg);
        }

        
    }
    
    public static void incluirFuncionarios(funcionarios tmpFuncionarios)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stFuncionario=ConexaoDAO.connSistema.createStatement();
            String buscaSql="INSERT INTO employees(";
            buscaSql+="employeeID, lastName,firstName,titleofcourtesy, title,";
            buscaSql+="birthDate,hireDate,city,region,Address,status)values(";
            buscaSql+="'"+tmpFuncionarios.getCodigoFuncionario()+"',";
            buscaSql+="'"+tmpFuncionarios.getUltimoNome()+"',";
            buscaSql+="'"+tmpFuncionarios.getPrimeiroNome()+"',";
            buscaSql+="'"+tmpFuncionarios.getCargoFuncionario()+"',";
            buscaSql+="'"+tmpFuncionarios.getRepresentacao()+"',";
            buscaSql+="'"+tmpFuncionarios.getDataNascimento()+"',";
            buscaSql+="'"+tmpFuncionarios.getDataContratacao()+"',";
            buscaSql+="'"+tmpFuncionarios.getCidade()+"',";
            buscaSql+="'"+tmpFuncionarios.getRegião()+"',";
            //buscaSql+="'"+tmpFuncionarios.getCep()+"',";
            buscaSql+="'"+tmpFuncionarios.getEndereço()+"',1)";
            stFuncionario.executeUpdate(buscaSql);
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            String msg="erro na hora de cadastrar funcionario."+erro.getMessage();
            throw new Exception(msg);
        }
    }
    public static void deletaFuncionario(funcionarios tmpCodigo)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stFuncionario=ConexaoDAO.connSistema.createStatement();
            String deletaFuncionario="DELETE FROM employees WHERE employeeID=";
            deletaFuncionario+="'"+tmpCodigo.getCodigoFuncionario()+"'";
            stFuncionario.executeUpdate(deletaFuncionario);
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            String msg="verifique sintaxe dos campos deleta Funcionario"+erro.getMessage();
            throw new Exception(msg);
        }
    }
    
    
    
    
    
    
    
    
    public static List<funcionarios> listaFuncionarios()throws Exception{
        try {
            List<funcionarios> vetorFuncionarios = new ArrayList<>();
            ConexaoDAO.abreConexao();
            stFuncionario=ConexaoDAO.connSistema.createStatement();
            String msg="select*from employees";
            rsFuncionario=stFuncionario.executeQuery(msg);
            while(rsFuncionario.next()){
                funcionarios funcionarioAtual = new funcionarios();
                
                funcionarioAtual.setCodigoFuncionario(rsFuncionario.getString("employeeID"));
                funcionarioAtual.setPrimeiroNome(rsFuncionario.getString("firstName"));
                funcionarioAtual.setCargoFuncionario(rsFuncionario.getString("titleofcourtesy"));
                funcionarioAtual.setCidade(rsFuncionario.getString("city"));
                vetorFuncionarios.add(funcionarioAtual);
            }
            ConexaoDAO.fechaConexao();
            return vetorFuncionarios;
        } catch (Exception erro) {
            String msg="Erro ao exibir funcionarios\n erro original"+erro.getMessage();
            throw new Exception (msg);
        }
        
    }

}
