package net.liavontsibrechka.spittr.config;

import net.liavontsibrechka.spittr.data.DBConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DBConfig.class})
public class RootConfig {
}
