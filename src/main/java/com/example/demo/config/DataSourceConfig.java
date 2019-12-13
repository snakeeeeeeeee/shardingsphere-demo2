package com.example.demo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @version 1.0 created by zhangyu_fh on 2019/12/13 14:30
 */
@Configuration
public class DataSourceConfig {

    private static final String MAPPER_LOCATION = "classpath*:mapper/**/*Mapper.xml";

//    @Bean("shardingDataSource")
//    @ConditionalOnMissingBean(name = "shardingDataSource")
//    public DataSource getShardingDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        Collection<TableRuleConfiguration> tableRuleConfigs = shardingRuleConfig.getTableRuleConfigs();
//
//        //user分表配置
//        TableRuleConfiguration userTableRuleConfig = new TableRuleConfiguration("user", "ds0.user_${0..1}");
//        userTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", getUserPreciseShardingAlgorithm()));
//
//        //playlist 分表策略
//        TableRuleConfiguration playlistTableRuleConfig = new TableRuleConfiguration("playlist", "ds0.playlist_${0..1}");
//        playlistTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("playlist_id", getPlaylistPreciseShardingAlgorithm()));
//
//        //分片规则
//        tableRuleConfigs.add(playlistTableRuleConfig);
//        tableRuleConfigs.add(userTableRuleConfig);
//
//        //默认分组
//        shardingRuleConfig.getBindingTableGroups().add("playlist");
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("playlist_id", getPlaylistPreciseShardingAlgorithm()));
//
//        Properties properties = new Properties();
//        properties.setProperty("sql.show", "true");
//        return ShardingDataSourceFactory.createDataSource(getDataSourceMap(), shardingRuleConfig, properties);
//    }

//    @Bean
//    public DemoPlaylistPreciseShardingAlgorithm getPlaylistPreciseShardingAlgorithm() {
//        return new DemoPlaylistPreciseShardingAlgorithm();
//    }
//
//    @Bean
//    public DemoUserPreciseShardingAlgorithm getUserPreciseShardingAlgorithm() {
//        return new DemoUserPreciseShardingAlgorithm();
//    }
//
//    @Bean
//    public KeyGeneratorConfiguration getPlaylistIdKeyGeneratorConfiguration() {
//        return new KeyGeneratorConfiguration("SNOWFLAKE", "playlist_id");
//    }

//    Map<String, DataSource> getDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>();
//        result.put("ds0", getDs0DataSource());
//        return result;
//    }

//    @Bean(name = "ds0DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.ds0")
//    @ConditionalOnMissingBean(name = "ds0DataSource")
//    @Primary
//    public DataSource getDs0DataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        List<Filter> filters = new ArrayList<>();
//        //filters.add(wallFilter);
//        datasource.setProxyFilters(filters);
//        return datasource;
//    }


    @Bean(name = "ds0TransactionManager")
    @ConditionalOnBean(name = "shardingDataSource")
    @Primary
    public DataSourceTransactionManager ds0TransactionManager(@Qualifier("shardingDataSource") DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean(name = "ds0SqlSessionFactory")
    @ConditionalOnMissingBean(name = "ds0SqlSessionFactory")
    @Primary
    public SqlSessionFactory ds0SqlSessionFactory(@Qualifier("shardingDataSource") DataSource shardingDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(shardingDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage("com.example.demo.domain");
        return sessionFactory.getObject();
    }
}
