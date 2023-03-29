package Mesa01_2;

public class Infantil extends Cardapio {
    private double valorPresente;

    public Infantil(double preco, double valorPresente) {
        super(preco);
        this.valorPresente = valorPresente;
    }

    @Override
    public void montar() {
        System.out.println("Vem com brinde Valor adicinal  " + calcularPrecoVenda());
    }

    @Override
    public double calcularPrecoVenda() {
        return getPreco() + this.valorPresente;
    }
}
