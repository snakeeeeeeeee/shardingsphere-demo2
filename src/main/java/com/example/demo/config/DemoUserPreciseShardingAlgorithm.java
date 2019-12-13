package com.example.demo.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @version 1.0 created by zhangyu_fh on 2019/12/13 10:40
 */
public class DemoUserPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {


    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        return availableTargetNames.stream().filter(name ->
                name.endsWith(String.valueOf(shardingValue.getValue().hashCode() % 2))).findFirst().get();
    }
}
