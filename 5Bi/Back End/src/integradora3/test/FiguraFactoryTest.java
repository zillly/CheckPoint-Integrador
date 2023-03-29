package integradora3.test;

import integradora3.model.Quadrado;
import integradora3.model.Triangulo;
import integradora3.service.FiguraFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FiguraFactoryTest {

    @Test
    void getTriangulo() {
        Triangulo triangulo = FiguraFactory.obterTriangulo("vermelho");
        triangulo.setTamanho(2);

        Triangulo triangulo1 = FiguraFactory.obterTriangulo("vermelho");
        triangulo1.setTamanho(1);

        Assertions.assertEquals(triangulo.getCor(), "vermelho");
        Assertions.assertEquals(triangulo1.getTamanho(), 1);
        Assertions.assertEquals(FiguraFactory.trianguloHashMap.size() == 1, true);
    }
    @Test
    void getQuadrado(){
        Quadrado quadrado = FiguraFactory.obterQuadrado(4);
        quadrado.setCor("Azul");

        Quadrado quadrado1 = FiguraFactory.obterQuadrado(4);
        quadrado.setCor("Verde");


        Assertions.assertEquals(quadrado.getTamanho(), 4);
        Assertions.assertEquals(quadrado1.getCor(), "Verde");
        Assertions.assertEquals(FiguraFactory.quadradoHashMap.size() == 1, true);


    }

}
