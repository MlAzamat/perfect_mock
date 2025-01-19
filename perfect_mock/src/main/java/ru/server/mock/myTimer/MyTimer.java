package ru.server.mock.myTimer;

public class MyTimer {
    public static void myTimer(int timerMin, int timerMax) {
        try {
            Thread.sleep(getRandomDiceNumber(timerMin, timerMax));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static int getRandomDiceNumber(int min, int max)
    {
        return (int) (Math.random() * (max - min)) + min;
    }
}
