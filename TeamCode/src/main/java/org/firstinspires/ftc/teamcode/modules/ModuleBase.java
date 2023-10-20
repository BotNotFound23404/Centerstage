package org.firstinspires.ftc.teamcode.modules;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class ModuleBase {
    /**
     * Used for logging from modules
     */
    protected final Telemetry telemetry;

    /**
     * Initializes the module and registers it with the specified OpMode
     * @param registrar The OpMode initializing the module
     */
    public ModuleBase(@NonNull OpMode registrar) {
        telemetry = registrar.telemetry;
    }
}
