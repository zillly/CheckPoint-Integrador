package Mesa01_1;

public class Circulo extends Figura {

    private double raio;

    public Circulo (double raio) {

        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }
}
