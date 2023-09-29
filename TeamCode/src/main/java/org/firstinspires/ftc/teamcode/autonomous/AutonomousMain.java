package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.OpBase;

@Autonomous(name = "Motor Melter")
public class AutonomousMain extends OpBase {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor m = hardwareMap.get(DcMotor.class, "Drive Motor");
        while (true) { // melt the motor
            for (double power = -1.0; power <= 1.0; power += 0.00000000001) {
                m.setPower(power);
            }
        }
    }
    
}
