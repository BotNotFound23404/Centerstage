package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Point;

public final class PositionalDriveTrain extends DriveTrain {
    private Point remainingDistance;

    private boolean killUpdaterThread = false;

    private final Thread positionUpdaterThread = new Thread(() -> {
        double prevTime = parent.getRuntime();
        double deltaTime;

        while (!killUpdaterThread) {
            deltaTime = parent.getRuntime() - prevTime;
            prevTime = parent.getRuntime();

            // rotate, then move
            if (remainingDistance.rotation != 0) {
                double absRemainingRotation = Math.abs(remainingDistance.rotation);
                // (same w/ x & y, distance is measured in nanoseconds) if deltaTime > remaining
                //  distance, set motors to fractional power; otherwise, just set to full power
                if (remainingDistance.rotation < deltaTime) {
                    setVelocity(0, 0, Math.copySign(absRemainingRotation / deltaTime, remainingDistance.rotation));
                    remainingDistance.rotation = 0;
                }
                else {
                    setVelocity(0, 0, Math.copySign(1, remainingDistance.rotation));
                    remainingDistance.rotation -= Math.copySign(deltaTime, remainingDistance.rotation);
                }
            }
            else if (remainingDistance.x != 0 || remainingDistance.y != 0) {
                double absRemainingDistX = Math.abs(remainingDistance.x);
                double absRemainingDistY = Math.abs(remainingDistance.y);
                double powerX = Math.copySign(absRemainingDistX < deltaTime ? absRemainingDistX / deltaTime : 1, remainingDistance.x);
                double powerY = Math.copySign(absRemainingDistY < deltaTime ? absRemainingDistY / deltaTime : 1, remainingDistance.y);
                setVelocity(powerX, powerY, 0);
                remainingDistance.subtract(new Point(Math.copySign(Math.max(absRemainingDistX, deltaTime), remainingDistance.x),
                        Math.copySign(Math.max(absRemainingDistY, deltaTime), remainingDistance.y),
                        0));
            }
            else {
                setVelocity(0, 0, 0);
            }
        }
    });

    public PositionalDriveTrain(@NonNull OpMode registrar) throws InterruptedException {
        super(registrar);
        remainingDistance = new Point(0, 0, 0);

        if (positionUpdaterThread.isAlive()) {
            positionUpdaterThread.interrupt();
        }

        positionUpdaterThread.start();
    }



    
    public void moveAndRotateRobot(double distX, double distY, double rotation) {
        remainingDistance = remainingDistance.add(new Point(distX, distY, rotation));
    }

    public void moveAndRotateRobot(@NonNull Point distance) {
        moveAndRotateRobot(distance.x, distance.y, distance.rotation);
    }

    @Override
    public void cleanupModule() {
        super.cleanupModule();
        killUpdaterThread = true;
    }
}
