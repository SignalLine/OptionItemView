package com.singal.zy.library.tick;

/**
 * @author li
 *         Create on 2017/11/9.
 * @Description 动画执行速率枚举配置
 *
 */

enum  TickRateEnum {

    SLOW(800,480,720),

    NORMAL(500,300,450),

    FAST(300,180,270);

    public static final int RATE_MODE_SLOW = 0;
    public static final int RATE_MODE_NORMAL = 1;
    public static final int RATE_MODE_FAST = 2;

    private int mRingAnimatorDuration;
    private int mCircleAnimatorDuration;
    private int mScaleAnimatorDuration;

    TickRateEnum(int mRingAnimatorDuration,int mCircleAnimatorDuration,int mScaleAnimatorDuration){
        this.mCircleAnimatorDuration = mCircleAnimatorDuration;
        this.mRingAnimatorDuration = mRingAnimatorDuration;
        this.mScaleAnimatorDuration = mScaleAnimatorDuration;
    }

    public int getRingAnimatorDuration() {
        return mRingAnimatorDuration;
    }

    public TickRateEnum setRingAnimatorDuration(int mRingAnimatorDuration) {
        this.mRingAnimatorDuration = mRingAnimatorDuration;
        return this;
    }

    public int getCircleAnimatorDuration() {
        return mCircleAnimatorDuration;
    }

    public TickRateEnum setCircleAnimatorDuration(int mCircleAnimatorDuration) {
        this.mCircleAnimatorDuration = mCircleAnimatorDuration;
        return this;
    }

    public int getScaleAnimatorDuration() {
        return mScaleAnimatorDuration;
    }

    public TickRateEnum setScaleAnimatorDuration(int mScaleAnimatorDuration) {
        this.mScaleAnimatorDuration = mScaleAnimatorDuration;
        return this;
    }

    public static TickRateEnum getRateEnum(int rateMode){
        TickRateEnum tickRateEnum = TickRateEnum.NORMAL;
        switch (rateMode) {
            case RATE_MODE_FAST:
                tickRateEnum = TickRateEnum.FAST;
                break;
            case RATE_MODE_SLOW:
                tickRateEnum = TickRateEnum.SLOW;
                break;
            case RATE_MODE_NORMAL:
                tickRateEnum = TickRateEnum.NORMAL;
            default:
                break;
        }

        return tickRateEnum;
    }

}
