package com.schibsted.spain;

import org.junit.Test;
import rx.Observable;
import rx.Observer;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class RxJavaIntroductionTest {

  @Test
  public void testItWorks() throws Exception {
    int value = Observable.just(4)
        .toBlocking().single();

    assertEquals(3,value);
  }

  @Test
  public void testNowObservable() throws Exception{
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
}
