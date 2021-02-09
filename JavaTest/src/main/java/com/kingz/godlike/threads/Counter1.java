package com.kingz.godlike.threads;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Copyright(C) 2017, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/9/20 13:40 <br>
 * description: <br>
 */
public class Counter1 extends Applet {
    private int count = 0;
    private Button onOff = new Button("Toggle"),
            start = new Button("Start");
    private TextField t = new TextField(10);
    private boolean runFlag = true;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("TTTTTT",10,10);
        g.draw3DRect(50, 59, 100, 20, true);
    }

    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    public void go() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                    }
                    if (runFlag) {
                        t.setText(Integer.toString(count++));
                    }
                }
            }
        }).start();
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args) {
        Counter1 applet = new Counter1();
        Frame aFrame = new Frame("Counter1");
        aFrame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
} ///:~