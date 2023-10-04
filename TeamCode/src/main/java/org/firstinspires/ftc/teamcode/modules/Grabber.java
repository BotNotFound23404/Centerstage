package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public final class Grabber {
    /**
     * The first servo controlling the grabber
     */
    private final Servo servo1;

    /**
     * The second servo controlling the grabber
     */
    private final Servo servo2;

    /**
     * The default name for the first grabber motor
     */
    public static final String SERVO1_DEFAULT_NAME = "Grabber Servo 1";

    /**
     * The default name for the second grabber motor
     */
    public static final String SERVO2_DEFAULT_NAME = "Grabber Servo 2";

    /**
     * The position the motor should be in when the grabber is active
     */
    private static final double ACTIVE_SERVO_POSITION = 0.5;

    /**
     * The position the motor should be in when the grabber is inactive
     */
    private static final double INACTIVE_SERVO_POSITION = 0;

    private boolean isGrabbing;

    /**
     * Is the grabber active?
     * @return whether or not the grabber is grabbing
     */
    public boolean isGrabbing() {
        return isGrabbing;
    }

    /**
     * Initializes the module with the given motor
     * @param servo1 The first servo controlling the grabber
     * @param servo2 The second servo controlling the grabber
     */
    public Grabber(Servo servo1, Servo servo2) {
        this.servo1 = servo1;
        this.servo2 = servo2;

        isGrabbing = true; // release() only runs if isGrabbing isn't set to false
        release(); // in case the grabber is currently active, deactivate it
    }

    /**
     * Given an OpMode, initializes the module with the default motor (one with the module's default motor name)
     * @param registrar The OpMode initializing the module
     * @exception InterruptedException The module was unable to locate the necessary motors
     */
    public Grabber(@NonNull OpMode registrar) throws InterruptedException {
        this(
                registrar.hardwareMap.get(Servo.class, SERVO1_DEFAULT_NAME),
                registrar.hardwareMap.get(Servo.class, SERVO2_DEFAULT_NAME)
            );
    }

    /**
     * Rotates the grabber by the specified amount
     * @param rotation The amount to rotate the grabber by
     */
    public void rotate(double rotation) {
        // rotate relative to current position to preserve grab state
        servo1.setPosition(servo1.getPosition() + rotation);
        servo2.setPosition(servo2.getPosition() + rotation);
    }

    /**
     * If the grabber isn't currently active, activate it
     */
    public void grab() {
        if (isGrabbing()) { return; }
        isGrabbing = true;

        // rotating servos in different directions to rotate the middle gear
        servo1.setPosition(ACTIVE_SERVO_POSITION);
        servo2.setPosition(-ACTIVE_SERVO_POSITION);
    }

    /**
     * If the grabber is currently active, deactivate it
     */
    public void release() {
        if (!isGrabbing()) { return; }
        isGrabbing = false;

        // rotating servos in different directions to rotate the middle gear
        servo1.setPosition(INACTIVE_SERVO_POSITION);
        servo2.setPosition(-INACTIVE_SERVO_POSITION);
    }

    /**
     * Toggles whether or not the grabber is active
     */
    public void toggleGrabState() {
        isGrabbing = !isGrabbing;
        if (isGrabbing) {
            grab();
        }
        else {
            release();
        }
    }
}
