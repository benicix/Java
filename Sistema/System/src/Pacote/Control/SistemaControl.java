package Pacote.Control;


import Pacote.View.*;
import Pacote.DAO.ConexaoDAO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;







/**
 *
 * @author 28253995
 */
public class SistemaControl {

   
    public static void main(String[] args) {
        try {
            ConexaoDAO.abreConexao();
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JLabel imagem = new JLabel();
                imagem.setIcon(new ImageIcon(getClass().getResource("/Pacote/imagens/minha.jpg")));
                new LoginView().getContentPane().add(imagem);
            }
        });
        
    }
    
    
      
    
}
