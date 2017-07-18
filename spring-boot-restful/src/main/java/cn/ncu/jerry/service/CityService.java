package cn.ncu.jerry.service;


import cn.ncu.jerry.dao.CityDao;
import cn.ncu.jerry.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市业务逻辑类
 *
 * Created by Jiacheng on 2017/7/18.
 */

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAllCity(){
        return cityDao.findAllCity();
    }

    public City findCityById(Long id) {
        return cityDao.findById(id);
    }

    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    public Long deleteCity(Long id) {
        return cityDao.deleteCity(id);
    }

}
