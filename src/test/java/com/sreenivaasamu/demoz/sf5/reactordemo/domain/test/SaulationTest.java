package com.sreenivaasamu.demoz.sf5.reactordemo.domain.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.sreenivaasamu.demoz.sf5.reactordemo.domain.Soldier;

import lombok.extern.slf4j.Slf4j;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Slf4j
class SaulationTest {
	
	Soldier subhashchandra = new Soldier("Subhash Chandra", "Bose");
	Soldier bhagat = new Soldier("Bhagath", "Singh");
	Soldier chandra = new Soldier("Chandra Shekhar", "Azad");
	Soldier ranilakshmi = new Soldier("Rani Lakshmi", "Bai");
	Soldier mangalpandey = new Soldier("Mangal", "Pandey");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    public void monoTests() throws Exception {
        Mono<Soldier> personMono = Mono.just(chandra);
        Soldier person = personMono.block();

        log.info(person.salute());
    }

    public void monoFilter() throws Exception {
        Mono<Soldier> ranilakshmiMono = Mono.just(ranilakshmi);

        Soldier ranilakshmiMonoAxe = ranilakshmiMono
                .filter(soldier -> soldier.getFirstName().equalsIgnoreCase("foo"))
                .block();
      
        Executable exec = () -> {ranilakshmiMonoAxe.salute();};
        assertThrows(NullPointerException.class, exec);
    }

    @Test
    public void fluxTest() throws Exception {

        Flux<Soldier> soldiers = Flux.just(subhashchandra, chandra, bhagat, ranilakshmi, mangalpandey);

        soldiers.subscribe(soldier -> log.info(soldier.salute()));

    }

    @Test
    public void fluxTestFilter() throws Exception {

        Flux<Soldier> soldiers = Flux.just(subhashchandra, chandra, bhagat, ranilakshmi, mangalpandey);

        soldiers.filter(soldier -> soldier.getFirstName().equals(chandra.getFirstName()))
                .subscribe(soldier -> log.info(soldier.salute()));

    }

    @Test
    public void fluxTestDelayNoOutput() throws Exception {

        Flux<Soldier> soldiers = Flux.just(subhashchandra, chandra, bhagat, ranilakshmi, mangalpandey);

        soldiers.delayElements(Duration.ofSeconds(5))
                .subscribe(soldier -> log.info(soldier.salute()));
    }

    @Test
    public void fluxTestDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Soldier> soldiers = Flux.just(subhashchandra, chandra, bhagat, ranilakshmi, mangalpandey);

        soldiers.delayElements(Duration.ofSeconds(5))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(soldier -> log.info(soldier.salute()));

        countDownLatch.await();

    }

    @Test
    public void fluxTestFilterDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Soldier> soldiers = Flux.just(subhashchandra, chandra, bhagat, ranilakshmi, mangalpandey);

        soldiers.delayElements(Duration.ofSeconds(1))
                .filter(soldier -> soldier.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(soldier -> log.info(soldier.salute()));

        countDownLatch.await();
    }	
}
