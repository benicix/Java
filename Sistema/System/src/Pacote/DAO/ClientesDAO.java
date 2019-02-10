package Pacote.DAO;





import Pacote.VO.Clientes;
//import Pacote.DAO.ConexaoDAO;
import java.sql.*;
import java.util.*;

public class ClientesDAO {
    // utilizado para armazenar resconultado de uma consulta do banco de dados
    public static ResultSet rsClientes;
    // responsavel pela execução de comandos SQL no banco de dados
    public static Statement stClientes;
    
    // retorna apenas um Cliente
    public static Clientes consultaCliente(String tmpBusca,int tmpTipo)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stClientes = ConexaoDAO.connSistema.createStatement();
            String sqlBusca = null;
            if (tmpTipo == 1){
                sqlBusca = "select * from Customers where customerID = '" 
                           + tmpBusca + "'";
            }else if(tmpTipo == 2){
                sqlBusca = "select * from Customer where companyName = '%"
                           + tmpBusca + "%'";
            }
            // guarda o resultado de stClientes da variavel sqlBusca na variavel
            rsClientes = stClientes.executeQuery(sqlBusca);
            if (rsClientes.next()){//se houver registros
                Clientes tmpCliente = new Clientes();
                // definir códigos nos respectivos campos
                tmpCliente.setCodigo(rsClientes.getString("CustomerID"));
                tmpCliente.setNomeEmpresa(rsClientes.getString("CompanyName"));
                tmpCliente.setNomeRepresentante(rsClientes.getString("ContactName"));
                tmpCliente.setCargo(rsClientes.getString("ContactTitle"));
                tmpCliente.setEndereco(rsClientes.getString("Address"));
                tmpCliente.setCidade(rsClientes.getString("City"));
                tmpCliente.setEstado(rsClientes.getString("Region"));
                tmpCliente.setPais(rsClientes.getString("Country"));
                tmpCliente.setCep(rsClientes.getString("PostalCode"));
                tmpCliente.setTelefone(rsClientes.getString("Phone"));
                tmpCliente.setEmail(rsClientes.getString("Fax"));
                tmpCliente.setStatus(rsClientes.getInt("Status"));
                return tmpCliente;
                
            }else{//se não houver resultados
                return null;
            }
        } catch (Exception erro) {
            String msg = "Falha na consulta.Verifique a sintaxe, campos e tabelas da instrução select.\nERRO ORIGINAL"+erro.getMessage();
            throw new Exception(msg);
        }
    }//fechando consultar cliente
    public static void cadastraCliente(Clientes tmpClientes)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            stClientes = ConexaoDAO.connSistema.createStatement();
            String sqlCadastra = "insert into customers(";
            sqlCadastra += "customerID, companyName, contactName,";
            sqlCadastra += "contactTitle, address, city, region, postalCode, ";
            sqlCadastra += "country, phone, fax, status)values(";
            
            sqlCadastra += "'"+ tmpClientes.getCodigo()+ "',";
            sqlCadastra += "'"+ tmpClientes.getNomeEmpresa()+ "',";
            sqlCadastra += "'"+ tmpClientes.getNomeRepresentante()+ "',";
            sqlCadastra += "'"+ tmpClientes.getCargo()+ "',";
            sqlCadastra += "'"+ tmpClientes.getEndereco()+ "',";
            sqlCadastra += "'"+ tmpClientes.getCidade()+ "',";
            sqlCadastra += "'"+ tmpClientes.getEstado()+ "',";
            sqlCadastra += "'"+ tmpClientes.getCep()+ "',";
            sqlCadastra += "'"+ tmpClientes.getPais()+ "',";
            sqlCadastra += "'"+ tmpClientes.getTelefone()+ "',";
            sqlCadastra += "'"+ tmpClientes.getEmail()+ "',1)";
            
            
            stClientes.executeUpdate(sqlCadastra);
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            String msg = "Falha no cadastro. Verifique a sintaxe, campos e tabelas da instrução insert.\nERRO ORIGINAL"+erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    public static void AlteraCliente(String tmpCodigo, Clientes tmpCliente)throws Exception{
        try {
            
        } catch (Exception erro) {
            String msg="Falha ao alterar registro do cliente. Verifique a sintaxe\nERRO ORIGINAL"+erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    public static void excluiCliente(String tmpCodigo)throws Exception{
        try {
            
        } catch (Exception erro) {
            String msg="Erro ao excluir cliente\nERRO ORIGINAL" + erro.getMessage();
            throw new Exception(msg);
        }
        
    }
    //List<> pois retorna o vetor cliente
    public static List<Clientes> listaClientes()throws Exception{
        try {
            List<Clientes> vetorClientes = new ArrayList<Clientes>();
            ConexaoDAO.abreConexao();
            stClientes = ConexaoDAO.connSistema.createStatement();
            String sqlClientes = "select * from customers";
            rsClientes = stClientes.executeQuery(sqlClientes);
            while(rsClientes.next()){
                Clientes clienteAtual = new Clientes();
                // preenchendo objeto Clientes com os dados do banco
                clienteAtual.setCodigo(rsClientes.getString("CustomerID"));
                clienteAtual.setNomeEmpresa(rsClientes.getString("CompanyName"));
                clienteAtual.setCidade(rsClientes.getString("City"));
                clienteAtual.setEmail(rsClientes.getString("Fax"));
                // adicionando objeto Clientes no vetor
                vetorClientes.add(clienteAtual); 
            }
            ConexaoDAO.fechaConexao();
            return vetorClientes;
        } catch (Exception erro) {
            String msg="Erro ao exibir lista de clientes\nERRO ORIGINAL" + erro.getMessage();
            throw new Exception(msg);
        }       
    }
    public static void alteraStatus(String tmpCodigo, int tmpStatus)throws Exception{
        try {
            ConexaoDAO.abreConexao();
            
            int novoStatus;
            if(tmpStatus==0){
                novoStatus=1;
            }else{
                novoStatus=0;
            }
            String sqlStatus= "Update customers set status = "+ novoStatus+" where customerId like'" +tmpCodigo+"'";
            stClientes=ConexaoDAO.connSistema.createStatement();
            stClientes.executeUpdate(sqlStatus);
            ConexaoDAO.fechaConexao();
            
        } catch (Exception erro) {
            throw new Exception("Falha na alteraçao\nErro Original:" + erro.getMessage());
        }
    }
    public static List<Clientes> consultaNome(String tmpNome) throws Exception{
        List<Clientes> lista = new ArrayList<Clientes>();
        try {
            String sqlLista="SELECT * FROM Customers WHERE CompanyName LIKE '%"+tmpNome+"%'";
            ConexaoDAO.abreConexao();
            stClientes= ConexaoDAO.connSistema.createStatement();          
            rsClientes = stClientes.executeQuery(sqlLista);
            while(rsClientes.next()){
                Clientes clientes = new Clientes();
                // preenchendo objeto Clientes com os dados do banco
                clientes.setCodigo(rsClientes.getString("CustomerID"));
                clientes.setNomeEmpresa(rsClientes.getString("CompanyName"));
                clientes.setCidade(rsClientes.getString("City"));
                clientes.setEmail(rsClientes.getString("Fax"));
                // adicionando objeto Clientes no vetor
                lista.add(clientes); 
            }
            ConexaoDAO.fechaConexao();
            return lista;
        } catch (Exception erro) {
            throw new Exception("falha na consulta original. \n Erro Original"+erro.getMessage());
        }
        
        }
    }
   

