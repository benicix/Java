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
public class Fornecedores {
    public String CodigoFornecedores, NomeEmpresa, Nome, Representante,Endereço,Cidade,Regiao,cep,pais,telefone;
    public Fornecedores(){
        
    }
    public Fornecedores(String tmpCodigoFornecedores, String tmpNomeEmpresa, String tmpNome, String tmpRepresentante,
    String tmpEndereço,String tmpCidade, String tmpregiao, String tmpcep, String tmppais, String tmptelefone){
        
    }

    public String getCodigoFornecedores() {
        return CodigoFornecedores;
    }

    public void setCodigoFornecedores(String tmpCodigoFornecedores) {
        CodigoFornecedores = tmpCodigoFornecedores;
    }

    public String getNomeEmpresa() {
        return NomeEmpresa;
    }

    public void setNomeEmpresa(String tmpNomeEmpresa) {
        NomeEmpresa = tmpNomeEmpresa;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String tmpNome) {
        Nome = tmpNome;
    }

    public String getRepresentante() {
        return Representante;
    }

    public void setRepresentante(String tmpRepresentante) {
        Representante = tmpRepresentante;
    }

    public String getEndereço() {
        return Endereço;
    }

    public void setEndereço(String tmpEndereço) {
        Endereço = tmpEndereço;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String tmpCidade) {
        Cidade = tmpCidade;
    }

    public String getRegiao() {
        return Regiao;
    }

    public void setRegiao(String tmpRegiao) {
        Regiao = tmpRegiao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String tmpcep) {
        cep = tmpcep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String tmppais) {
        pais = tmppais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String tmptelefone) {
        telefone = tmptelefone;
    }
    
}
