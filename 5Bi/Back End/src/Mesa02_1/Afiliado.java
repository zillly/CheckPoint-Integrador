package Mesa02_1;

public class Afiliado extends  Vendedor{

    public Afiliado(String nome, int venda, int pontosPorVendas) {
        super(nome, venda, pontosPorVendas);
    }
    
    @Override
    public int calcularPontos() {
       return getVenda() + getPontosPorVendas();
    }

   
}
