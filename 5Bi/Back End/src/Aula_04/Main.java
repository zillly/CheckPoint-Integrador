package Aula_04;

public class Main {
    public static void main(String[] args) {
        ArvoreFactory arvoreFactory = new ArvoreFactory();

        for ( int i = 1 ;i <= 100 ; i ++){
            arvoreFactory.getArvores(200,400,"verde");
            System.out.println(arvoreFactory.getArvores(200,400,"verde").toString());
        }
        for ( int i = 1 ;i <= 100 ; i ++){
            arvoreFactory.getArvores(500,300,"vermelha");
            System.out.println(arvoreFactory.getArvores(500,300,"vermelha").toString());
        }
        for ( int i = 1 ;i <= 100 ; i ++){
            arvoreFactory.getArvores(100,200,"azul");
            System.out.println(arvoreFactory.getArvores(100,200,"azul").toString());
        }




    }

}
