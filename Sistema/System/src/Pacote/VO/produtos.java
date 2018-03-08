package Pacote.VO;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benicio
 */
public class produtos {
    String idProduto,nomeProduto,idFornecedor,categoriaid,quantidadeUnidade;
    float PreçoUnidade;
    int quantidadeEstoque;
    public produtos(){
        
    }
    public produtos(String tmpidproduto,String tmpnomeProduto,String tmpidFornecedor,String tmpCategoriaid,String tmpquantidadeUnidade,float tmppreçoUnidade,int tmpquantidadeEstoque){
        this.setCategoriaid(tmpCategoriaid);
        this.setIdFornecedor(tmpidFornecedor);
        this.setIdProduto(tmpidproduto);
        this.setNomeProduto(tmpnomeProduto);
        this.setPreçoUnidade(tmppreçoUnidade);
        this.setQuantidadeEstoque(tmpquantidadeEstoque);
        this.setQuantidadeUnidade(tmpquantidadeUnidade);
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String tmpidProduto) {
        idProduto = tmpidProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String tmpnomeProduto) {
        nomeProduto = tmpnomeProduto;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String tmpidFornecedor) {
        idFornecedor = tmpidFornecedor;
    }

    public String getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(String tmpcategoriaid) {
        categoriaid = tmpcategoriaid;
    }

    public String getQuantidadeUnidade() {
        return quantidadeUnidade;
    }

    public void setQuantidadeUnidade(String tmpquantidadeUnidade) {
        quantidadeUnidade = tmpquantidadeUnidade;
    }

    public float getPreçoUnidade() {
        return PreçoUnidade;
    }

    public void setPreçoUnidade(float tmpPreçoUnidade) {
        PreçoUnidade = tmpPreçoUnidade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int tmpquantidadeEstoque) {
        quantidadeEstoque = tmpquantidadeEstoque;
    }
    
    
}
