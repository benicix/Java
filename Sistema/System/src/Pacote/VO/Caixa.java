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
public class Caixa {
    public String codigo,Produto,QuantidadeEmEstoque;
    public int Quantidade,QtdProdutos;
    public Float Valor;
     public Caixa(){
         
     }
     public Caixa(String tmpcodigo,String tmpProduto,int tmpQuantidade,String tmpQuantidadeEmEstoque,Float Valor,int tmpQtdProdutos){
         this.setCodigo(tmpcodigo);
         this.setProduto(tmpProduto);
         this.setQuantidade(tmpQuantidade);
         this.setQuantidadeEmEstoque(tmpQuantidadeEmEstoque);
         this.setValor(Valor);
         this.setQtdProdutos(tmpQtdProdutos);
     }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String tmpcodigo) {
        codigo = tmpcodigo;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String tmpProduto) {
        Produto = tmpProduto;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int tmpQuantidade) {
        Quantidade = tmpQuantidade;
    }

    public String getQuantidadeEmEstoque() {
        return QuantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(String tmpQuantidadeEmEstoque) {
        QuantidadeEmEstoque = tmpQuantidadeEmEstoque;
    }

    public Float getValor() {
        return Valor;
    }

    public void setValor(Float tmpValor) {
        this.Valor = tmpValor;
    }

    public int getQtdProdutos() {
        return QtdProdutos;
    }

    public void setQtdProdutos(int tmpQtdProdutos) {
        QtdProdutos = tmpQtdProdutos;
    }
    
     
    
}
