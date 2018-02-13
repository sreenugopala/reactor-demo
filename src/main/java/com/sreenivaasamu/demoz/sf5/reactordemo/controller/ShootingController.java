package com.sreenivaasamu.demoz.sf5.reactordemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sreenivaasamu.demoz.sf5.reactordemo.domain.Bullet;

import reactor.bus.Event;
import reactor.bus.EventBus;

@Controller
public class ShootingController {

	@Autowired
	private EventBus eventBus;

	@GetMapping("/lockTarget/{param}")
	public ResponseEntity shoot(@PathVariable Integer param) {
		for (int i = 0; i < param; i++) {
			Bullet data = new Bullet();
			data.setId(i);

			eventBus.notify("sharpShooter", Event.wrap(data));

			System.out.printf("Fire Order [%1$s]: submitted successfully %n", i);
		}
		
		return new ResponseEntity(HttpStatus.OK);		
	}
}