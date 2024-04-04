package hibuy.server.common.config;

import hibuy.server.common.config.properties.KakaoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {KakaoProperties.class})
public class PropertiesConfiguration {
}
