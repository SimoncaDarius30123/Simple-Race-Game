package isp.lab10.raceapp;

import javax.swing.*;
import java.awt.*;

class Car extends Thread {
    private String name;
    private int distance = 0;
    private CarPanel carPanel;





    public Car(String name, CarPanel carPanel) {
        //set thread name;
        setName(name);
        this.name = name;
        this.carPanel = carPanel;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        while (distance < 400) {
            // simulate the car moving at a random speed
            int speed = (int) (Math.random() * 10) + 1;
            distance += speed;

            carPanel.updateCarPosition(name, distance);

            try {
                // pause for a moment to simulate the passage of time
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        carPanel.carFinished(name);
        long endTime = System.currentTimeMillis();
        System.out.print(((endTime-startTime)/1000.0f) + " seconds" );
        System.out.println();
    }
}

class CarPanel extends JPanel {
    private int[] carPositions;
    private String[] carNames;
    private Color[] carColors;

    private int[] finishOrder; // variabilă pentru a stoca ordinea de sosire a mașinilor
    private int numFinished = 0;

    public CarPanel() {
        carPositions = new int[4];
        carNames = new String[]{"Golf 4", "BMW M3", "Dacia Logan", "Lamborghini"};
        carColors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        finishOrder = new int[4];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 4; i++) {
            int yPos = 50 + i * 50; // Vertical position of the car
            int xPos = carPositions[i]; // Horizontal position of the car
            int carSize = 30; // Size of the car

            g.setColor(carColors[i]);
            g.fillOval(xPos, yPos, carSize, carSize);
            g.setColor(Color.BLACK);
            g.drawString(carNames[i], xPos, yPos - 5);
        }
    }

    public void updateCarPosition(String carName, int distance) {
        int carIndex = getCarIndex(carName);
        if (carIndex != -1) {
            carPositions[carIndex] = distance;
            repaint();
        }
    }

    public void carFinished(String carName) {
        int pos = numFinished;
        finishOrder[pos] = getCarIndex(carName);
        numFinished++;
        final String[] positions = {"first","second","third","fourth"};
        updateCarPosition(carName,400);
        System.out.print(carName + " finished the race on " + positions[pos] + " place in " );
    }

    private int getCarIndex(String carName) {
        for (int i = 0; i < 4; i++) {
            if (carNames[i].equals(carName)) {
                return i;
            }
        }
        return -1;
    }
}
