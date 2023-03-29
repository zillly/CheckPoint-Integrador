package teste.main.service;

import main.dao.impl.HotelDao;
import main.model.Hotel;
import main.service.HotelService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelServiceTest {

    private HotelService hotelService = new HotelService(new HotelDao());

    @Test
     void salvarObjet() {
        Hotel fillial1 = new Hotel("Karma","Alecrim",1523,"Salvador","Bahia", 4);
        hotelService.salvar(fillial1);
        Hotel fillial2 = new Hotel("Karma","Peixote",1525,"Recife","Pernambuco", 5);
        hotelService.salvar(fillial2);
        Hotel fillial3 = new Hotel("Karma","Afonso",1524,"Sao paulo","Sao paulo", 4);
        hotelService.salvar(fillial3);
        Hotel fillial4 = new Hotel("Karma","Alecrim",1528,"Rio de Janeiro","Rio de Janeiro", 3);
        hotelService.salvar(fillial4);
        Hotel fillial5 = new Hotel("Karma","Alecrim",1529,"Salvador","Bahia", 2);
        hotelService.salvar(fillial5);

    }

}
