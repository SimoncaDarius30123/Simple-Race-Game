package isp.lab10.raceapp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Semaphore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SemaphorePanel semaphorePanel = new SemaphorePanel();
        frame.getContentPane().add(semaphorePanel);
        frame.pack();
        frame.setVisible(true);

        SemaphoreThread semaphoreThread = new SemaphoreThread(semaphorePanel);
        semaphoreThread.start();
        semaphoreThread.join();

        //Turn on the timer when the race begins
        TimerThread timerThread = new TimerThread();
        timerThread.start();

        JFrame carFrame = new JFrame("Car Race");
        carFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CarPanel carPanel = new CarPanel();
        carFrame.getContentPane().add(carPanel);
        carFrame.pack();
        carFrame.setSize(500, 300);
        carFrame.setVisible(true);

        PlaySound playSound = new PlaySound();
        playSound.playSound();

        Car car1 = new Car("Golf 4", carPanel);
        Car car2 = new Car("BMW M3", carPanel);
        Car car3 = new Car("Dacia Logan", carPanel);
        Car car4 = new Car("Lamborghini", carPanel);

        car1.start();
        car2.start();
        car3.start();
        car4.start();

        car1.join();
        car2.join();
        car3.join();
        car4.join();

        // Stop the timer when the race is finished
        timerThread.stopTimer();
        timerThread.join();

        playSound.stopSound();

        long elapsedTime = timerThread.getElapsedTime();
        System.out.println("Race finished in " + elapsedTime / 1000.0f + " seconds");
    }
}

