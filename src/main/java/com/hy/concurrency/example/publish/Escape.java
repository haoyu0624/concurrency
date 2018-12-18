package com.hy.concurrency.example.publish;

import com.hy.concurrency.annoactions.NotRecommend;
import com.hy.concurrency.annoactions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass() {
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    /**
     * 对象未构造前，不允许发布对象
     * @param args
     */
    public static void main(String[] args) {
        new Escape();
    }
}
