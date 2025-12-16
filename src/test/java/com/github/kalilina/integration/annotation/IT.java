package com.github.kalilina.integration.annotation;

import com.github.kalilina.integration.TestApplicationRunner;
import com.github.kalilina.spring.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
public @interface IT {
}
