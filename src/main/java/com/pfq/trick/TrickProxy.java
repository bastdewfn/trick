package com.pfq.trick;

import com.pfq.trick.config.TrickConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by huwei on 2017/8/10.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({TrickBeanDefinitionRegistryPostProcessor.class,TrickConfig.class})
public @interface TrickProxy {
}
