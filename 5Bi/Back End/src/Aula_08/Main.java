package Aula_08;

import Aula_08.model.Hoteis;
import Aula_08.model.Voos;
import Aula_08.service.ApiBuscaHoteis;
import Aula_08.service.ApiBuscaVoo;

public class Main {
    public static void main(String[] args) {

        ApiBuscaVoo apiBuscaVoo = new ApiBuscaVoo();
        ApiBuscaHoteis apiBuscaHoteis = new ApiBuscaHoteis();

        Voos voos = new Voos("16/03/2023","18/03/2023","Salvador","Natal");
        Hoteis hoteis = new Hoteis("16/03/2023","18/03/2023","Natal");


        apiBuscaVoo.buscar(hoteis,voos);
        apiBuscaHoteis.buscar(hoteis,voos);




    }

}
