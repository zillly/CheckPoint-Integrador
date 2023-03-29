package Aula_08.service;

import Aula_08.model.Hoteis;
import Aula_08.model.Voos;

public class ApiBuscaHoteis  implements IBuscarFachada {
    @Override
    public String buscar(Hoteis hoteis, Voos voos) {
        if(hoteis.getDataEntrada().equalsIgnoreCase(voos.getDataPartida()) && hoteis.getDataSaida().equalsIgnoreCase(voos.getDataRetorno()) && hoteis.getCidade().equalsIgnoreCase(voos.getDestino()) ){
            System.out.println("Hotel disponivel");
        }
        return "Buscas disponiveis";
    }
}
