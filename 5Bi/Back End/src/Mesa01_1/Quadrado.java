package Mesa01_1;

public class Quadrado extends Figura{
    private double lado;

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public Quadrado(double lado) {

        this.lado = lado;
    }
    @Override
    public double calcularPerimetro(){

        return 4*lado;
        }



}
