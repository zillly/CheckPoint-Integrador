package Mesa02_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendedorTeste {
    Funcionario funcionario1,funcionario2;
    Afiliado afiliado1,afiliado2,afiliado3;
    @BeforeEach
    void vendedorTeste(){
        funcionario1 = new Funcionario("Kaio",5, 15,15);
        funcionario2 = new Funcionario("alex",5, 15,15);
        afiliado1 = new Afiliado("Silva",60 , 300);
        afiliado2 = new Afiliado("joao",6 , 30);
        afiliado3 = new Afiliado("jose",6 , 30);
        funcionario2.addFuncionario(afiliado1);

    }
    @Test
     void testCategoria(){
        funcionario1.mostrarCategoria();
        funcionario2.mostrarCategoria();
        afiliado1.mostrarCategoria();
        afiliado2.mostrarCategoria();
        afiliado3.mostrarCategoria();
    }

}
