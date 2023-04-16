package hw1;

/**
 * The purpose of this assignment is to implement one class that models a
 * removeable and reachargeable camera battery.
 * 
 * @author Nick McCullough - COMS 227 Assignment 1
 *
 */

public class CameraBattery
{
	/*
	 * Number of charger settings
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;

	/*
	 * Constant rate of external charger only
	 */
	public static final double CHARGE_RATE = 2.0;

	/*
	 * Default power consumption of the camera
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;

	/*
	 * Value to hold ongoing battery charge, important
	 */
	private double batteryCharge;

	/*
	 * Value to hold initial battery charge
	 */
	private double batteryStartingCharge;

	/*
	 * value of the batteryCapacity
	 */
	private double batteryCapacity;

	/*
	 * variable equal to default power consumption
	 */
	private double camPower;

	/*
	 * value of the charge of the camera
	 */
	private double cameraCharge;

	/*
	 * integer value to hold if connected or not, instead of conditional
	 */
	private int isConnected;
	
	/*
	 * integer value for connected
	 */
	private int connected = 1;
	
	/*
	 * integer value for disconnected
	 */
	private int disconnected = 0;

	/*
	 * the setting of the charger
	 */
	private int chargerSetting;

	/*
	 * value of the total drain amount
	 */
	private double totalDrain;

	/*
	 * Create new camera battery, given constructor
	 */
	public CameraBattery(double batteryStartingCharge, double batteryCapacity)
	{
		this.isConnected = 0; // disconnected
		this.batteryCapacity = Math.max(batteryStartingCharge, batteryCapacity);
		this.batteryCharge = batteryStartingCharge; // initialize and hold value for current charge
		this.batteryStartingCharge = Math.min(batteryStartingCharge, batteryCapacity); // starting charge min
		this.camPower = DEFAULT_CAMERA_POWER_CONSUMPTION;
		this.totalDrain = 0; // there is no drain on the battery yet
		this.chargerSetting = 0; // charger setting at zero

	}

	/*
	 * - Indicates the user has pressed the setting button one time on the external
	 * charger.
	 * 
	 * - The charge setting increments by one - or if already at the maximum setting
	 * wraps around to setting 0.
	 * 
	 * hint: use % operator
	 */
	public void buttonPress()
	{
		Math.min(chargerSetting, 0.0); // cannot go below zero
		Math.max(chargerSetting, 3.0); // cannot go above three (four total settings)
		// note: ^ unnecessary or necessary? doesn't change specChecker

		chargerSetting = ((chargerSetting + 1) % NUM_CHARGER_SETTINGS); // increment
	}

	/**
	 * - Charges the battery connected to the camera (assuming it is connected) for
	 * a given number of minutes.
	 * 
	 * - The amount of charging in milliamp-hours (mAh) is the number minutes times
	 * the CHARGE_RATE constant.
	 * 
	 * - However, charging cannot exceed the capacity of the battery connected to
	 * the camera or no charging if the battery is not connected.
	 * 
	 * - The method returns the actual amount the battery has been charged.
	 * 
	 * @param minutes
	 * @return
	 */
	public double cameraCharge(double minutes)
	{
			isConnected = Math.min(connected, disconnected); // connected
			double cameraChargeRate = (minutes * CHARGE_RATE); // local charge rate variable relevant to this method only
			//batteryCharge = Math.min(batteryCharge + cameraChargeRate, batteryCapacity); // charge cannot be higher than
			cameraCharge += cameraChargeRate;
			batteryCharge += cameraChargeRate;
			return cameraChargeRate;
		}

	

	/**
	 * - Drains the battery connected to the camera (assuming it is connected) for a
	 * given number of minutes.
	 * 
	 * - The amount of drain in milliamp-hours (mAh) is the number of minutes times
	 * the cameras power consumption.
	 * 
	 * - However, the amount cannot exceed the amount of charge contained in the
	 * battery assuming it is connected to the camera, or no amount drain if the
	 * battery is not connected.
	 * 
	 * - The method returns the actual amount drain from the battery.
	 * 
	 * @param minutes
	 * @return
	 */
	public double drain(double minutes)
	{
			isConnected = Math.min(connected, disconnected); // connected
			double drainRate = (minutes * camPower);
			drainRate = Math.min(drainRate, batteryCharge); // camera's charge cannot be less than the current drain
			totalDrain += drainRate; // value to keep track of ongoing drain
			batteryCharge -= drainRate; // updated battery charge minus the current drain rate
			return drainRate; // returns the amount drained from the battery
		}

	

	/**
	 * - Charges the battery connected to the external charger (assuming it is
	 * connected) for a given number of minutes.
	 * 
	 * - The amount of charging in milliamp-hours (mAh) is the number minutes times
	 * the charger setting (a number between 0 inclusive and NUM_CHARGER_SETTINGS
	 * exclusive) the CHARGE_RATE constant.
	 * 
	 * - However, charging cannot exceed the capacity of the battery connected to
	 * the camera or no charging if the battery is not connected.
	 * 
	 * - The method returns the actual amount the battery has been charged.
	 * 
	 * @param minutes
	 * @return
	 */
	public double externalCharge(double minutes)
	{
			isConnected = Math.min(connected, disconnected); // connected
			double externalChargeRate; // local external charge rate relevant to this method only
			externalChargeRate = CHARGE_RATE * minutes * chargerSetting; // charger setting variable
			batteryCharge = Math.min(batteryCharge + externalChargeRate, batteryCapacity);
			return externalChargeRate; // returns the amount the battery has been charged
		}
	

	/*
	 * Reset the battery monitoring system by setting the total battery drain count
	 * back to 0.
	 */
	public void resetBatteryMonitor()
	{
		totalDrain = 0; // setting totalDrain back to zero
	}

	/**
	 * Get the battery's capacity. (Getter)
	 * 
	 * @return
	 */
	public double getBatteryCapacity()
	{
		return batteryCapacity;
	}

	/**
	 * Get the battery's current charge. (Getter) DONE
	 * 
	 * @return
	 */
	public double getBatteryCharge()
	{
		return batteryCharge;
	}

	/**
	 * Get the current charge of the camera's battery. (Getter) DONE
	 * 
	 * @return
	 */
	public double getCameraCharge()
	{
		return Math.max(batteryCharge, cameraCharge);
	}

	/**
	 * Get the power consumption of the camera. (Getter) DONE
	 * 
	 * @return
	 */
	public double getCameraPowerConsumption()
	{
		return camPower;
	}

	/**
	 * Get the external charger setting. (Getter) DONE
	 * 
	 * @return
	 */
	public int getChargerSetting()
	{
		// NUM_CHARGER_SETTINGS;
		return chargerSetting;
	}

	/**
	 * * - Get the total amount of power drained from the battery since the last
	 * time the battery monitor was started or reset. (Getter)
	 * 
	 * @return
	 */
	public double getTotalDrain()
	{
		return totalDrain;
	}

	/**
	 * - Move the battery to the external charger. Updates any variables as needed
	 * to represent the move.
	 */
	public void moveBatteryExternal()
	{
		isConnected = connected;
	}

	/**
	 * - Move the battery to the camera. Updates any variables as needed to
	 * represent the move.
	 */
	public void moveBatteryCamera()
	{
		isConnected = connected;
	}

	/**
	 * - Remove the battery from either the camera or external charger. - Updates
	 * any variables as needed to represent the removal.
	 */
	public void removeBattery()
	{
		isConnected = disconnected;
	}

	/*
	 * Set the power consumption of the camera. (Setter)
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption)
	{
		this.camPower = cameraPowerConsumption;
	}

}
