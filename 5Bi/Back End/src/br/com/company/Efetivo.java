package br.com.company;

public class Efetivo extends Funcionario {
    public Efetivo(String nome, String sobrenome, String numeroConta) {
        super(nome, sobrenome, numeroConta);
    }

    @Override
    public double calcularSalario() {
        return 0;
    }

    @Override
    public void imprimirRecibo(double quantia) {

    }

}
