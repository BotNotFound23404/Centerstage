package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public final class Grabber {
    /**
     * The servo controlling the grabber
     */
    private final Servo servo;

    /**
     * The default name for the grabber motor
     */
    public static final String SERVO_DEFAULT_NAME = "Grabber Servo";

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
     * @param servo The servo controlling the grabber
     */
    public Grabber(Servo servo) {
        this.servo = servo;

        isGrabbing = true; // release() only runs if isGrabbing isn't set to false
        release(); // in case the grabber is currently active, deactivate it
    }

    /**
     * Given an OpMode, initializes the module with the default motor (one with the module's default motor name)
     * @param registrar The OpMode initializing the module
     * @exception InterruptedException The module was unable to locate the necessary motors
     */
    public Grabber(@NonNull OpMode registrar) throws InterruptedException {
        this(registrar.hardwareMap.get(Servo.class, SERVO_DEFAULT_NAME));
    }

    /**
     * If the grabber isn't currently active, activate it
     */
    public void grab() {
        if (isGrabbing()) { return; }
        isGrabbing = true;

        servo.setPosition(ACTIVE_SERVO_POSITION);
    }

    /**
     * If the grabber is currently active, deactivate it
     */
    public void release() {
        if (!isGrabbing()) { return; }
        isGrabbing = false;

        servo.setPosition(INACTIVE_SERVO_POSITION);
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
