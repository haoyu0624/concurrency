package com.hy.concurrency.example.publish;

import com.hy.concurrency.annoactions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    /**
     * unsafePublish实例创建后可以通过get方法获得里面的属性并且可以更改它的值，后续线程再用此对象时，无法保证此对象的数据
     * @param args
     */
    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
