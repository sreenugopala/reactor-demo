package com.sreenivaasamu.demoz.sf5.reactordemo.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sreenivaasamu.demoz.sf5.reactordemo.domain.Bullet;
import com.sreenivaasamu.demoz.sf5.reactordemo.service.LauncherService;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class SharpShooter implements Consumer<Event<Bullet>> {
 
    @Autowired
    private LauncherService launcherService;
     
    @Override
    public void accept(Event<Bullet> target) {
        Bullet bullet = target.getData();
         
        try {
        	launcherService.fire(bullet);
        } catch (InterruptedException e) {
            // ignore        
        }   
    }
}