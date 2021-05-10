package com.desgin.beahvior_type._02_strategy;

/**
 * 策略模式Demo
 *
 * 以AutoSize ui适配库的策略模式为学习模板
 */
public class StrategyDemo {
    // 1.首先定义策略接口
    public interface AutoAdaptStrategy {
        void applyAdapt(Object target, Object otherParams);
    }

    // 2.创建默认的策略
    class DefaultAutoAdaptStrategy implements AutoAdaptStrategy {
        @Override
        public void applyAdapt(Object target, Object otherParams) {
            //do default adapt things
        }
    }
    class CustomAutoAdaptStrategy implements AutoAdaptStrategy {
        @Override
        public void applyAdapt(Object target, Object otherParams) {
            //do custom adapt things
        }
    }
    // 3. 需增加API给外部,方便其设置策略类型
    public void setAutoAdaptStrategy(AutoAdaptStrategy autoAdaptStrategy){
        //  StrategyHolderClass.setAutoAdaptStrategy(new WrapperAutoAdaptStrategy(autoAdaptStrategy))
    }


    // 3.功能扩展:增加策略的全局wrapper，比如增加适配流程回调。
    public interface onAdaptListener {
        //适配前置/后置钩子函数接口
        void onAdaptBefore();
        void onAdaptAfter();
    }

    // 适配器模式(对象)
    class WrapperAutoAdaptStrategy implements AutoAdaptStrategy{
        private final AutoAdaptStrategy mAutoAdaptStrategy;

        /**
         * 保存包裹策略对象实例
         */
        public WrapperAutoAdaptStrategy(AutoAdaptStrategy autoAdaptStrategy) {
            mAutoAdaptStrategy = autoAdaptStrategy;
        }

        @Override
        public void applyAdapt(Object target, Object otherParams) {
            // 获取到onAdaptListener对象
            // if (onAdaptListener != null){
            //    onAdaptListener.onAdaptBefore();
            // }
            if (mAutoAdaptStrategy != null) {
                mAutoAdaptStrategy.applyAdapt(target, otherParams);
            }
            // if (onAdaptListener != null){
            //     onAdaptListener.onAdaptAfter();
            // }
        }
    }


}
