package Mesa01_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteFigura {
     Circulo circulo1,circulo2,circulo3,circulo4;
     Quadrado quadrado5,quadrado6,quadrado7,quadrado8;

    @BeforeEach
    void perimetro() {
        circulo1 = new Circulo(3.1);
        circulo2 = new Circulo(5.3);
        circulo3 = new Circulo(6.5);
        circulo4 = new Circulo(7.5);
        quadrado5 = new Quadrado(4.4);
        quadrado6 = new Quadrado(4.5);
        quadrado7 = new Quadrado(4.6);
        quadrado8 = new Quadrado(4.7);



    }

    @Test
    void testePerimetro(){
        circulo1.calcularPerimetro();
        circulo2.calcularPerimetro();
        circulo3.calcularPerimetro();
        circulo4.calcularPerimetro();
        quadrado5.calcularPerimetro();
        quadrado6.calcularPerimetro();
        quadrado7.calcularPerimetro();
        quadrado8.calcularPerimetro();


    }


}