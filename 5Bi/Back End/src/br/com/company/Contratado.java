package br.com.company;

public class Contratado extends Funcionario {

    private double valorHoras;
    private int horas;

    public Contratado(String nome, String sobrenome, String numeroConta) {
        super(nome, sobrenome, numeroConta);
        this.valorHoras = valorHoras;
        this.horas = horas;


    }


    @Override
    public double calcularSalario() {
        return valorHoras*horas;
    }

    @Override
    public void imprimirRecibo(double quantia) {
        System.out.println("imprimir recibo " + quantia);
    }



}
