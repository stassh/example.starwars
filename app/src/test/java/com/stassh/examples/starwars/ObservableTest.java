package com.stassh.examples.starwars;

import org.junit.Test;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ObservableTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_observable() {
        Observable.just("one", "two", "three", "four", "five")
            .subscribe(new Consumer<String>() {
                @Override
                public void accept(@NonNull String s) throws Exception {
                    System.out.println(s);
                }
            });
    }
}