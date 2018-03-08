package Pacote.VO;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 28253995
 */
public class funcionarios {
    public String codigoFuncionario, ultimoNome, primeiroNome, cargoFuncionario, representacao, dataNascimento, dataContratacao,cidade,região,cep,endereço;
    public int status;
    public funcionarios(){
        
    }
    
    public funcionarios(String tmpCargoFuncionario,String tmpultimoNome, String primeiroNome,String tmpcodigoFucnioanrio,String tmprepresentação,String tmpdataNascimento, String tmpdataContratacao,String tmpendereço,String tmpcidade,
    String tmpregião,String tmpcep, int tmpstatus){
        
        this.setCargoFuncionario(tmpCargoFuncionario);
        this.setCep(tmpcep);
        this.setCidade(tmpcidade);
        this.setCodigoFuncionario(tmpcodigoFucnioanrio);
        this.setDataContratacao(tmpdataContratacao);
        this.setDataNascimento(tmpdataNascimento);
        this.setEndereço(tmpendereço);
        this.setPrimeiroNome(primeiroNome);
        this.setRegião(tmpregião);
        this.setRepresentacao(tmprepresentação);
        this.setUltimoNome(tmpultimoNome);
        this.setStatus(tmpstatus);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int tmpstatus) {
        this.status = tmpstatus;
    }






   

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String tmpendereço) {
        endereço = tmpendereço;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String tmpcidade) {
        cidade = tmpcidade;
    }

    public String getRegião() {
        return região;
    }

    public void setRegião(String tmpregião) {
        região = tmpregião;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String tmpcep) {
        cep = tmpcep;
    }
     public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String tmpcodigoFuncionario) {
        codigoFuncionario = tmpcodigoFuncionario;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String tmpultimoNome) {
        ultimoNome = tmpultimoNome;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String tmpprimeiroNome) {
        primeiroNome = tmpprimeiroNome;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String tmpcargoFuncionario) {
        cargoFuncionario = tmpcargoFuncionario;
    }

    public String getRepresentacao() {
        return representacao;
    }

    public void setRepresentacao(String tmprepresentacao) {
        representacao = tmprepresentacao;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String tmpdataNascimento) {
        dataNascimento = tmpdataNascimento;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String tmpdataContratacao) {
        dataContratacao = tmpdataContratacao;
    }
    
}
