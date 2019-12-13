package com.example.demo.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @version 1.0 created by zhangyu_fh on 2019/12/13 10:40
 */
public class DemoPlaylistPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        return availableTargetNames.stream().filter(name ->
                name.endsWith(String.valueOf(shardingValue.getValue().hashCode() % 2))).findFirst().get();
    }
}
