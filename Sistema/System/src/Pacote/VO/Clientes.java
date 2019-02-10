package Pacote.VO;





public class Clientes {
    public String codigo, nomeEmpresa, nomeRepresentante, cargo, endereco,
            cidade, estado, pais, cep, telefone, email;
    public int Status;

    public Clientes() {}
    

    public Clientes(String tmpcodigo, String tmpnomeEmpresa, String tmpnomeRepresentante, String tmpcargo, String tmpendereco, String tmpcidade, String tmpestado, String tmppais, String tmpcep, String tmptelefone, String tmpemail, int tmpStatus) {
        this.setCodigo(tmpcodigo);
        this.setNomeEmpresa(tmpnomeEmpresa);
        this.setNomeRepresentante(tmpnomeRepresentante);
        this.setCargo(tmpcargo);
        this.setEndereco(tmpendereco);
        this.setCidade(tmpcidade);
        this.setEstado(tmpestado);
        this.setPais(tmppais);
        this.setCep(tmpcep);
        this.setTelefone(tmptelefone);
        this.setEmail(tmpemail);
        this.setStatus(tmpStatus);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String tmpcodigo) {
        codigo = tmpcodigo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String tmpnomeEmpresa) {
        nomeEmpresa = tmpnomeEmpresa;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public void setNomeRepresentante(String tmpnomeRepresentante) {
        nomeRepresentante = tmpnomeRepresentante;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String tmpcargo) {
        cargo = tmpcargo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String tmpendereco) {
        endereco = tmpendereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String tmpcidade) {
        cidade = tmpcidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String tmpestado) {
        estado = tmpestado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String tmppais) {
        pais = tmppais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String tmpcep) {
        cep = tmpcep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String tmptelefone) {
        telefone = tmptelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String tmpemail) {
        email = tmpemail;
    }
    public int getStatus(){
        return Status;
    }
    public void setStatus(int tmpStatus){
        Status =tmpStatus;
    }
    
    
}