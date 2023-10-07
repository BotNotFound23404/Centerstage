package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class Arm {

    /**
     * lmao its the arm motor
     */
    private final DcMotor armMotor;

    /**
     * default name for the armMotor
     */
    public static final String ARM_MOTOR_DEFAULT_NAME = "Arm Motor";

    public Arm(DcMotor armMotor) {
        this.armMotor = armMotor;

        // motor config
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public Arm(@NonNull OpMode registrar) throws InterruptedException {
        this(
                registrar.hardwareMap.get(DcMotor.class, ARM_MOTOR_DEFAULT_NAME)
        );
    }
}