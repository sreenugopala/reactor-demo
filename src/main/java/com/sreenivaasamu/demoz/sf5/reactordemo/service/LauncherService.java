package com.sreenivaasamu.demoz.sf5.reactordemo.service;

import com.sreenivaasamu.demoz.sf5.reactordemo.domain.Bullet;

public interface LauncherService {

	void fire(Bullet data) throws InterruptedException;
}
