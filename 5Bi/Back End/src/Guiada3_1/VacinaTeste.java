package Guiada3_1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VacinaTeste {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void vacinar(){

        LocalDate dataAplicacao = LocalDate.parse("16/03/2023",formatter);
        LocalDate dataAgendada = dataAplicacao;

        Pessoa p1 = new Pessoa("Jorge","Lucas",123456,dataAplicacao,dataAgendada,"Gripe");
       // IVacina v = new Vacina();
       // v.vacinarPessoa(p1.getRg(),p1.getDataAplicacao(),p1.getNomeVacina(),p1.getDataAgendada());

    }
}
