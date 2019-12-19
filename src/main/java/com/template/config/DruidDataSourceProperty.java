//配置数据源
package com.template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperty {

    private String url ;
    private String username;
    private String password;
    private String driverClassName;

    private int initialSize = 0;
    private int minIdle;
    private int maxActive = 8;
    //配置获取连接等待超时的时间
    private int maxWait;
    //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    private int timeBetweenEvictionRunsMillis = 1000*60;
    //配置一个连接在池中最小生存的时间，单位是毫秒
    private int minEvictableTimeMillis = 1000*60*30;
    private String validationQuery;
    private boolean testWhileIdle = false;
    private boolean testOnBorrow = true;
    private boolean testOnReturn = false;
    //打开PSCache,并且指定每个连接上PSCache的大小
    private boolean PoolPreparedStatements = false;
    private int maxPoolPreparedStatementPerConnectionSize = -1;
    //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    private String filters;
    //合并多个DruidDataSource的监控数据
    private boolean useGlobalDataSourceStat = false;
    //通过connectProperties属性来打开mergeSql功能;慢SQL记录
    private String connectionProperties;
}
