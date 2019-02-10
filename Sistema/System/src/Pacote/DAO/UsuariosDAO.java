package Pacote.DAO;



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
import java.sql.*;
public class UsuariosDAO {
    public static Statement stUsuario;
    public static ResultSet rsUsuario;
    //public usuariosDao() {}

    public static usuario validaUsuario(String tmpNome, String tmpSenha) throws Exception {
        try {
            
            ConexaoDAO.abreConexao();
            usuario tmpUsuario = new usuario();
            stUsuario=ConexaoDAO.connSistema.createStatement();
            String sqlLogin="SELECT*FROM usuario where email like'"+ tmpNome+"'AND senha like'"+tmpSenha+"'";
            rsUsuario=stUsuario.executeQuery(sqlLogin);/*resultado de execute query nunca volta nulo sempre retorna uma tabela
            uma tabela se não houver valor retorna a tabela vazia
            */
            if(!rsUsuario.next()){
                return null;
            }else{
                //preenchendo tmpusuario.
                tmpUsuario.setEmail(rsUsuario.getString("email"));
                tmpUsuario.setSenha(rsUsuario.getString("senha"));
                tmpUsuario.setstatus(rsUsuario.getInt("permissao"));
                return tmpUsuario;
            }
        } catch (Exception erro) {
            throw new Exception("erro no processo de Login"+erro.getMessage());
        }
        
    }
}
