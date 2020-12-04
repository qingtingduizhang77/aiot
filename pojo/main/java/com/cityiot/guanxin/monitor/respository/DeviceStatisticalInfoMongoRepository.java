package com.cityiot.guanxin.monitor.respository;

import com.cityiot.guanxin.common.repository.BaseMongoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceStatisticalInfoMongoRepository extends BaseMongoRepository {
    public <T> List<T> countChartForDate(Criteria criteria, String collectionName, Class<?> returnClass) {
        MatchOperation matchOperation = Aggregation.match(criteria);
        ProjectionOperation projectionOperation = Aggregation
                .project("status")
                .andExpression("updateTime").extractMonth().as("upMonth")
                .andExpression("updateTime").extractDayOfMonth().as("upDay");
        GroupOperation groupOperation = Aggregation.group("upMonth","upDay").count().as("statusCount");
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC, "upMonth","upDay");
        Aggregation aggregation = Aggregation.
                newAggregation(matchOperation,
                        projectionOperation,
                        groupOperation,
                        sortOperation
                        );
        return (List<T>)mongoTemplate
                .aggregate(aggregation,collectionName,returnClass)
        .getMappedResults();
    }
}
