package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @version 1.0 created by zhangyu_fh on 2019/12/13 9:53
 */
@Slf4j
public class DemoRangeShardingAlgorithm implements RangeShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        System.out.println(availableTargetNames);
        System.out.println(shardingValue);
        return null;
    }
}
