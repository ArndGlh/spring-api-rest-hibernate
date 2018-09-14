package arnaudg.configs;

import org.springframework.context.annotation.ComponentScan;

// @Configuration
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@ComponentScan("arnaudg.security")
public class SecurityXmlConfig {

    public SecurityXmlConfig() {
        super();
    }

}