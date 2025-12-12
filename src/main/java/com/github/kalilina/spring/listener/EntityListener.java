package com.github.kalilina.spring.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

//    @EventListener(condition = "#p0.accessType.name == 'READ'")
    @EventListener
    public void acceptEntity(EntityEvent event) {
        System.out.println(event);
    }
}
