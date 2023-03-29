package Mesa01_2;

public class Adulto extends Cardapio{
    public Adulto(double preco) {
        super(preco);
    }

    @Override
    public void montar() {
        System.out.println("Valor da oferta "+calcularPrecoVenda());

    }

    @Override
    public double calcularPrecoVenda() {
        return getPreco() ;
    }
}
