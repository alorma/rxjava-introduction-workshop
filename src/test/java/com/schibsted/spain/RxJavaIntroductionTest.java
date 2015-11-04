package com.schibsted.spain;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observers.TestSubscriber;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class RxJavaIntroductionTest {

    @Test
    public void testItWorks() throws Exception {
        int value = Observable.just(4)
                .toBlocking().single();

        assertEquals(3, value);
    }

    @Test
    public void testNowObservable() throws Exception {
        Observer<Date> observer = new Observer<Date>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Date date) {
                System.out.println(date);
            }
        };

        Observable.just(new Date()).subscribe(observer);
    }

    @Test
    public void testDatesObservable() throws Exception {
        Observer<Date> observer = new Observer<Date>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Date date) {
                System.out.println(date);
            }
        };

        Observable<Date> observable = Observable.defer(() -> Observable.just(new Date()));

        Observable<Date> dateObservable = Observable.interval(1, TimeUnit.SECONDS)
                .take(4)
                .flatMap(aLong -> observable);


        TestSubscriber<Date> testSubscriber = new TestSubscriber<>(observer);
        dateObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();

    }

    @Test
    public void testIntervalObservable() throws InterruptedException {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(6);

        Observer<Long> observerLong = new Observer<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }
        };

        TestSubscriber<Long> longTestSubscriber = new TestSubscriber<Long>(observerLong);
        observable.subscribe(longTestSubscriber);

        System.out.println("Subscribed");

        longTestSubscriber.awaitTerminalEvent();

    }

    @Test
    public void testTake() {
        Observable.just("Patata").take(4).subscribe(System.out::println);
        Observable.just("Patata", "Jamon", "Aceitunas", "Ensaladilla russa", "Berberechos").take(4).subscribe(System.out::println);
    }


}
