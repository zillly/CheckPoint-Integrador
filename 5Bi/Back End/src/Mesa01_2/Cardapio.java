package Mesa01_2;

public abstract class Cardapio {

    private double preco;

    public Cardapio(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }



    public abstract void montar();
    public abstract double calcularPrecoVenda();
}
