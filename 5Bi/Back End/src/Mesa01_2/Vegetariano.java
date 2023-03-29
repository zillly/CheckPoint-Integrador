package Mesa01_2;

public class Vegetariano extends Cardapio {


    public Vegetariano(double preco) {
        super(preco);
    }

    @Override
    public void montar() {
        System.out.println("Vem com embalagem especial Valor adicinal " + calcularPrecoVenda());

    }

    @Override
    public double calcularPrecoVenda() {
        return getPreco() * 1.01;
    }
}
