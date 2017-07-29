package cn.ncu.jerry.service.impl;

import org.springframework.stereotype.Service;
import cn.ncu.jerry.entity.City;
import cn.ncu.jerry.repository.CityRepository;
import cn.ncu.jerry.service.CityService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 *
 * Created by Jiacheng on 2017/7/25.
 *
 * 分页 function score query 搜索逻辑如下：
 * 先创建分页参数，然后用 FunctionScoreQueryBuilder 定义 Function Score Query，并设置对应字段的权重分值。城市名称 1000 分，description 100 分
 * 然后创建该搜索的 DSL 查询，并打印
 */
@Service
public class CityESServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    CityRepository cityRepository;

    @Override
    public String saveCity(City city) {

        City cityResult = cityRepository.save(city);
        return "Create city "+cityResult.getCityname();
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);

        // Function Score Query
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100));

        // 创建搜索 DSL (Domain Specific Language特定领域语言) 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).build();

        LOGGER.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<City> searchPageResults = cityRepository.search(searchQuery);
        return searchPageResults.getContent();
    }

}

