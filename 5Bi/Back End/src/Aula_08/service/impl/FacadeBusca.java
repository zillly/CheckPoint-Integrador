package Aula_08.service.impl;

import Aula_08.model.Hoteis;
import Aula_08.model.Voos;
import Aula_08.service.ApiBuscaHoteis;
import Aula_08.service.ApiBuscaVoo;
import Aula_08.service.IBuscarFachada;

public class FacadeBusca implements IBuscarFachada {

    private ApiBuscaVoo apiBuscaVoo;
    private ApiBuscaHoteis apiBuscaHoteis;

    public FacadeBusca(ApiBuscaVoo apiBuscaVoo, ApiBuscaHoteis apiBuscaHoteis) {
        this.apiBuscaVoo = apiBuscaVoo;
        this.apiBuscaHoteis = apiBuscaHoteis;
    }

    @Override
    public String buscar(Hoteis hoteis, Voos voos) {
        apiBuscaHoteis.buscar(hoteis,voos);
        apiBuscaVoo.buscar(hoteis,voos);
        return "Buscas disponiveis";
    }
}
