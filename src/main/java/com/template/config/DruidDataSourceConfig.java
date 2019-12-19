package com.template.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
//@ServletComponentScan("com.demo2.filters")
public class DruidDataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    @Resource
    private DruidDataSourceProperty druidDataSourceProperty = new DruidDataSourceProperty();

    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(druidDataSourceProperty.getUrl());
        dataSource.setName(druidDataSourceProperty.getUsername());
        dataSource.setUsername(druidDataSourceProperty.getUsername());
        dataSource.setPassword(druidDataSourceProperty.getPassword());
        dataSource.setDriverClassName(druidDataSourceProperty.getDriverClassName());

        dataSource.setInitialSize(druidDataSourceProperty.getInitialSize());
        dataSource.setMinIdle(druidDataSourceProperty.getMinIdle());
        dataSource.setMaxActive(druidDataSourceProperty.getMaxActive());
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(druidDataSourceProperty.getMaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperty.getTimeBetweenEvictionRunsMillis());
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperty.getMinEvictableTimeMillis());
        dataSource.setValidationQuery(druidDataSourceProperty.getValidationQuery());
        dataSource.setTestWhileIdle(druidDataSourceProperty.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidDataSourceProperty.isTestOnBorrow());
        dataSource.setTestOnReturn(druidDataSourceProperty.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidDataSourceProperty.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperty.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dataSource.setFilters(druidDataSourceProperty.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        dataSource.setConnectionProperties(druidDataSourceProperty.getConnectionProperties());

        return dataSource;
    }

//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        logger.info("init Druid Servlet Configuration ");
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
//        servletRegistrationBean.setServlet(new StatViewServlet());
//        servletRegistrationBean.addUrlMappings("/druid/*");
//        Map<String, String> initParameters = new HashMap<String, String>();
//        initParameters.put("loginUsername", "admin");// 用户名
//        initParameters.put("loginPassword", "admin");// 密码
//        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
//        initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
//        //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
//        servletRegistrationBean.setInitParameters(initParameters);
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }
}
