package com.xmcc.springboot_demo.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration//表示该类为配置类
public class DruidConfig {
    @Bean(value = "druidDataSource", initMethod = "init", destroyMethod = "close")//把该方法的返回值作为bean交给spring管理，指定创建和销毁的方法
    @ConfigurationProperties(prefix = "spring.druid")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return druidDataSource;
    }
    @Bean
    public StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);//慢查询中是否记录日志
        statFilter.setSlowSqlMillis(5);//慢查询时间
        statFilter.setMergeSql(true);//格式化sql
        return statFilter;
    }
@Bean
    public ServletRegistrationBean servletRegistration(){
        return  new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

}
}
