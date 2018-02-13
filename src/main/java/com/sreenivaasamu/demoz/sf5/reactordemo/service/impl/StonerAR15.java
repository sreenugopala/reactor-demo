package com.sreenivaasamu.demoz.sf5.reactordemo.service.impl;

import org.springframework.stereotype.Service;

import com.sreenivaasamu.demoz.sf5.reactordemo.domain.Bullet;
import com.sreenivaasamu.demoz.sf5.reactordemo.service.LauncherService;

@Service
public class StonerAR15 implements LauncherService {

	@Override
	public void fire(Bullet data) throws InterruptedException {
		System.out.printf("Firing started for Notification ID : [%1$s] %n ", data.getId());
		Thread.sleep(10000);
		System.out.printf("Fired Notification ID : [%1$s] %n ", data.getId());
	}
}
