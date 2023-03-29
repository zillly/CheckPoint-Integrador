package Aula_08.service;

import Aula_08.model.Hoteis;
import Aula_08.model.Voos;

public class ApiBuscaVoo implements IBuscarFachada {




    @Override
    public String buscar(Hoteis hoteis, Voos voos) {
        if (hoteis.getCidade().equalsIgnoreCase(voos.getDestino())){
            System.out.println("Voo Disponivel");

        }

        return "Buscas disponiveis";

    }
}
