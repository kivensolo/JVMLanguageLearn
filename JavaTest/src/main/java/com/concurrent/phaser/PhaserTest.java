package com.concurrent.phaser;

import com.concurrent.executors.SingleExecutorSender;

import java.util.concurrent.Phaser;

/**
 * Phaser适用场景：
 * 例如比赛,一个比赛分为3个阶段(phase): 初赛、复赛和决赛,
 * 现在规定只要所有运动员完成上一个阶段的比赛就可以进行下一阶段的比赛,
 * 并且比赛的过程中允许退赛(deregister)。
 * <p>
 * 本例测试场景：
 * 5个loler 开始一场比赛需要分为3个阶段: 接受游戏匹配、选择英雄、进入游戏
 */
public class PhaserTest {
    //LOL选手个数
    private static int playerNum = 5;

    public static void main(String[] args) {

        MyPhaser phaser = new MyPhaser(playerNum);
        System.out.println("===== 匹配到玩家 =====");

        for (int i = 0; i < playerNum; i++) {
            SingleExecutorSender sender = SingleExecutorSender.getInstance();
            sender.execute(new Player(phaser));
        }
    }
}

class MyPhaser extends Phaser {
    //定义结束阶段.这里是完成3个阶段以后结束
    private int phaseToTerminate = 2;

    public MyPhaser(int parties) {
        super(parties);
    }

    /**
     * 当每个阶段的所有线程到达时会触发
     * @param phase     阶段index
     * @param registeredParties  注册的执行线程
     * @return false: 继续执行下一阶段
     * true:  phaser结束
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        System.out.println(String.format("*第%d阶段完成(共%d名玩家)", phase + 1, registeredParties));
        if(registeredParties != 5){
            System.out.println("===== 游戏取消 =====");
            return true;
        }
        //到达结束阶段,或者还没到结束阶段但是party不为5,都返回true,结束phaser
        return phase == phaseToTerminate;
    }
}

class Player implements Runnable {
    private Phaser phaser;

    public Player(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        //第一阶段做的事
        System.out.println("玩家 [" + Thread.currentThread().getName() + "]:接受游戏");
        phaser.arriveAndAwaitAdvance();
        //第二阶段
        if(Thread.currentThread().getName().equals("pool-1-thread-3")){
            System.out.println("玩家 [" + Thread.currentThread().getName() + "]:放弃选择英雄");
            phaser.arriveAndDeregister();
        }else{
            System.out.println("玩家 [" + Thread.currentThread().getName() + "]:已选择英雄");
            phaser.arriveAndAwaitAdvance();
            //第三阶段
            System.out.println("玩家 [" + Thread.currentThread().getName() + "]:进入游戏");
            phaser.arriveAndAwaitAdvance();
        }
    }
}