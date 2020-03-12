/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // PWM
	public static final int LEFT_FRONT = 0;
	public static final int RIGHT_FRONT = 1;
	public static final int LEFT_BACK = 2;
    public static final int RIGHT_BACK = 3;
    public static final int SHOOTER_LEFT_PWM = 4;
	public static final int SHOOTER_RIGHT_PWM = 5;

    // Buttons
	public static final int XBOX_LEFT_Y_AXIS = 1;
    public static final int XBOX_RIGHT_X_AXIS = 0;
    
    // Speeds
	public static final double DRIVE_TRAIN_SPEED = 0.7;
	public static final double DRIVE_FORWARD_TIME = 3.0;
    public static final double AUTONOMOUS_SPEED = 0.4;
    public static final double SHOOTER_SPEED = 1.0;
    
    // Joysticks
    public static final int JOYSTICK_NUMBER = 0;
    
    // Inverted
	public static final boolean SHOOTER_LEFT_INVERTED = false;
    public static final boolean SHOOTER_RIGHT_INVERTED = false;
    
	public static final boolean DRIVE_LEFT_FRONT_INVERTED = false;
	public static final boolean DRIVE_RIGHT_FRONT_INVERTED = true;
	public static final boolean DRIVE_LEFT_BACK_INVERTED = false;
	public static final boolean DRIVE_RIGHT_BACK_INVERTED = true;
}