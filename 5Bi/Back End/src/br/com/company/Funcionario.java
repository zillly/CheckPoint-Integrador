package br.com.company;

public abstract class Funcionario {
    private String nome;
    private String sobrenome;
    private String numeroConta;

    public Funcionario(String nome, String sobrenome, String numeroConta) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numeroConta = numeroConta;
    }

    public abstract double calcularSalario();
    public abstract void imprimirRecibo(double quantia);
    public void deposito(double quantia){
        System.out.println("foi depositado " + quantia);
    };
      public void pagamento(){
          double quantia;

      }

}
