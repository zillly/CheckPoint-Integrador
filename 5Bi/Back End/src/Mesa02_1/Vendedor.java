package Mesa02_1;

public abstract class Vendedor {
     private String nome;
     private int venda = 0;

     private int pontosPorVendas;

    public Vendedor(String nome, int venda, int pontosPorVendas) {
        this.nome = nome;
        this.venda = venda;
        this.pontosPorVendas = pontosPorVendas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    public int getPontosPorVendas() {
        return pontosPorVendas;
    }

    public void setPontosPorVendas() {
        this.pontosPorVendas = pontosPorVendas;
    }

    public void vender (int qtdVenda) {

        venda =  this.venda + qtdVenda;
        System.out.println(this.nome + venda);
    }
    public abstract int calcularPontos();



    public String getNomeCategoria(int pontosTotais) {

        if (pontosTotais < 20) {
            return "Novato";
        } else if (pontosTotais < 31) {
            return "aprendiz";
        } else if (pontosTotais < 41) {
            return  "bom";
        } else {
            return  "mestre";
        }

    }

    public String mostrarCategoria(){
      int calcularPontos = calcularPontos();
      return this.nome + " tem um total de " + calcularPontos + " pontos e categoriza como " + getNomeCategoria(calcularPontos);

    }


}
