package Mesa01_2;

public class Main {
    public static void main(String[] args) {
        Cardapio adulto = new Adulto(30.5);
        Cardapio infantil = new Infantil(10.1,5.0);
        Cardapio vegetariano = new Vegetariano(15.5);

        adulto.calcularPrecoVenda();
        infantil.calcularPrecoVenda();
        vegetariano.calcularPrecoVenda();

        adulto.montar();
        infantil.montar();
        vegetariano.montar();
    }
}
