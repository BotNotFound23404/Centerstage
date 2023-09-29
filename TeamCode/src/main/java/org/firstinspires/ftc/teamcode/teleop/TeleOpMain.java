package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.OpBase;

@TeleOp(name="Manual Control")
public class TeleOpMain extends OpBase {

    @Override
    public void runOpMode() throws InterruptedException {
        // initialization
        initHardware();
        
        waitForStart();
        
        while (opModeIsActive()) { // loop
            mover.moveAndRotateRobot(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );
        }
    }
    
}