package cn.ncu.jerry.service;

import cn.ncu.jerry.entity.City;
import java.util.List;

/**
 * Created by Jiacheng on 2017/7/24.
 */
public interface CityService {

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    String saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
