package main.service;

import main.dao.IDao;
import main.model.Hotel;

public class HotelService {

    private IDao<Hotel> hotelIDao;

    public HotelService(IDao<Hotel> hotelIDao) {
        this.hotelIDao = hotelIDao;
    }

    public Hotel salvar(Hotel hotel) {
        hotelIDao.salvar(hotel);
        return hotel;
    }

}
