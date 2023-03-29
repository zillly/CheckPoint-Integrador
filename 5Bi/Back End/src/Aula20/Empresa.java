package Aula20;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empresa implements Serializable {
    private  int cnpj;
    private String razãoSocial;

    List <Funcionario> funcionarios ;

    public Empresa(int cnpj, String razãoSocial, List<Funcionario> funcionarios) {
        this.cnpj = cnpj;
        this.razãoSocial = razãoSocial;
        this.funcionarios = funcionarios;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazãoSocial() {
        return razãoSocial;
    }

    public void setRazãoSocial(String razãoSocial) {
        this.razãoSocial = razãoSocial;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cnpj=" + cnpj +
                ", razãoSocial='" + razãoSocial + '\'' +
                ", funcionarios=" + funcionarios +
                '}';
    }
}
