package com.singal.zy.library.tick;

/**
 * @author li
 *         Create on 2017/11/9.
 * @Description
 */

public interface TickAnimatorListener {
    /**
     * 点击动画开始监听
     *
     * @param tickView
     */
    void onAnimationStart(TickView tickView);

    /**
     * 点击动画结束监听
     *
     * @param tickView
     */
    void onAnimationEnd(TickView tickView);

    abstract class TickAnimatorListenerAdapter implements TickAnimatorListener{
        @Override
        public void onAnimationStart(TickView tickView) {

        }

        @Override
        public void onAnimationEnd(TickView tickView) {

        }
    }

}
