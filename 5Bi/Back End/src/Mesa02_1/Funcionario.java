package Mesa02_1;

import java.util.ArrayList;

public class Funcionario extends Vendedor {
    private int anosAntiguidade;

    ArrayList<Afiliado> listaAfiliado = new ArrayList<>();

    public Funcionario(String nome, int venda, int pontosPorVendas, int anosAntiguidade) {
        super(nome, venda, pontosPorVendas);
        this.anosAntiguidade = anosAntiguidade;
    }

    public void addFuncionario(Afiliado afiliado){
        listaAfiliado.add(afiliado);
        System.out.printf("o filiado "+ afiliado.getNome() + "se afiliou ao funcionario "+ getNome());

    }


    @Override
    public int calcularPontos() {
        int calcular =  10 * (this.anosAntiguidade*5);
        return calcular;
    }
}
