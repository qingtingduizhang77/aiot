package com.cityiot.guanxin.config.filter;

import com.cityiot.guanxin.config.filter.provider.AreaFilterCauseProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFilterConfig {
    @Bean
    public DataFilterAdvisor dataFilterAdvisor() {
        return new DataFilterAdvisor();
    }
    @Bean
    public AreaFilterCauseProvider areaFilterCauseProvider () {
        return new AreaFilterCauseProvider();
    }
}
