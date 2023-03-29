package Aula_04;

import java.util.HashMap;
import java.util.Map;

public class ArvoreFactory {

    private static Map<String , Arvores> flyweight = new HashMap<>();

    public Arvores getArvores (int altura , int largura , String cor){

        String id = "id: " + altura + ":" + largura + ":" + cor;

        if (!flyweight.containsKey(id)) {
            Arvores arvores = new Arvores( altura, largura,cor);
            flyweight.put(id, arvores);
            System.out.println("Nova Arvore");
            return flyweight.get(id);
        }
        return flyweight.get(id);

    }


}
