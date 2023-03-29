package Aula20;

import java.io.Serializable;

public class Funcionario {
    //Você deverá manter os dados de uma empresa que tem um CNPJ e Razão Social junto com a lista de seus funcionários em um arquivo. Os funcionários possuem nome, sobrenome, documento de identificação (RG ou CPF) e salário.
    //Sugerimos que utilize o método Main para instanciar uma empresa com 4 funcionários e armazená-los em um arquivo da empresa. Em seguida, recupere do arquivo, a empresa, que também deverá ter seus 4 funcionários.
    //
    //Se você chegou até aqui, parabéns! Se você ainda tiver tempo e quiser  continuar praticando, deixamos para você mais um desafio.

    private String nome;
    private String sobrenome;
    private int rg;
    private double salario;

    public Funcionario(String nome, String sobrenome, int rg, double salario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", rg=" + rg +
                ", salario=" + salario +
                '}';
    }
}
