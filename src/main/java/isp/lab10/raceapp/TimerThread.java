package isp.lab10.raceapp;

class TimerThread extends Thread {
    private long elapsedTime = 0;
    private boolean running = true;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (running) {
            try {
                Thread.sleep(10); // increment the timer every 10 milliseconds
                elapsedTime += 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        this.elapsedTime = endTime - startTime;
    }

    public void stopTimer() {
        running = false;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
