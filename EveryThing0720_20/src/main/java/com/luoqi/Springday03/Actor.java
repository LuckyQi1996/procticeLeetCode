package com.luoqi.Springday03;


public class Actor implements IActor{
    @Override
    public void basicAct(float money) {
        System.out.println("基本表演"+ money);
    }

    @Override
    public void dangerAct(float money) {
        System.out.println("危险表演"+ money);
    }
}
