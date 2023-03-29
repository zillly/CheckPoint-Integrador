package com.dh.test.dh.service;

import com.dh.config.ConfiguracaoJDBC;
import com.dh.dao.impl.MedicamentoDao;
import com.dh.model.Medicamento;
import com.dh.service.MedicamentoService;




public class MedicamentoServiceTest {
    public MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDao( new ConfiguracaoJDBC()));

    //@Test
    void deveSalvarMedicamento(){

        Medicamento medicamento = new Medicamento("Ibuprofeno","Ache",300,15.0);
        medicamentoService.salvarMedicamento(medicamento);

    }

}
