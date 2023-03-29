package Guiada3_1;

import java.time.LocalDate;

public interface IVacina {
    void vacinarPessoa(int rg, LocalDate dataAplicacao, LocalDate dataAgendada,String nomeVacina);
}
