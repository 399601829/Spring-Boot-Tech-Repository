package cn.ncu.jerry.repository;

import cn.ncu.jerry.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jiacheng on 2017/7/25.
 *
 * Elasticsearch数据操作层
 * 默认会提供很多实现，比如 CRUD 和搜索相关的实现
 *
 */
@Repository
public interface CityRepository extends ElasticsearchRepository<City,Long> {

}
