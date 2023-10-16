package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class Arm {

    public static final int ENCODER_RESOLUTION = ((((1+(46/17))) * (1+(46/17))) * (1+(46/17)) * 28);

    /**
     * The motor moving the arm
     */
    private final DcMotor armMotor;

    /**
     * Default name for the armMotor
     */
    public static final String ARM_MOTOR_DEFAULT_NAME = "Arm Motor";

    /**
     * Initializes the module with the given motor
     * @param armMotor the motor moving the arm
     */
    public Arm(DcMotor armMotor) {
        this.armMotor = armMotor;

        // motor config
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setTargetPosition(0);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Given an OpMode, initializes the module with the default motor (one with the module's default motor name)
     * @param registrar The OpMode initializing the module
     * @exception InterruptedException The module was unable to locate the necessary motors
     */
    public Arm(@NonNull OpMode registrar) throws InterruptedException {
        this(
                registrar.hardwareMap.get(DcMotor.class, ARM_MOTOR_DEFAULT_NAME)
        );
    }

    public void setRotation(int rotation) {
        armMotor.setTargetPosition(rotation);
    }

    public void rotate(int rotation) {
        armMotor.setPower(rotation);
    }

    public boolean isRotating() {
        return armMotor.getCurrentPosition() == armMotor.getTargetPosition();
    }
}