package xuanngoc.gardenwatersystem.service;

import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Plant;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorValue;

public class PlantWaterService {

    public static final String WORKING_AUTO = "Hoạt động tự động";
    public static final String WORKING_MANUAL = "Hoạt động thủ công";
    public static final String FIXING = "Đang sửa chữa";
    public static final String BROKEN_DOWN = "Không hoạt động";
    public static final String WORKING = "Hoạt động";

    public static int plantWater(double sensorValue, Plant plant) {
        double minHumidity = plant.getMinHumidity();
        double maxHumidity = plant.getMaxHumidity();

        if (sensorValue < minHumidity) {
            return 1; // state = turn on
        } else if (sensorValue > maxHumidity) {
            return 0; // state = turn off
        }
        return 2; // state = currentState
    }

    public static boolean isDeviceWorking(Device device) {
        return device.getStatus().equals(WORKING_AUTO);
    }

    public static boolean isSensorWorking(Sensor sensor) {
        return sensor.getStatus().equals(WORKING);
    }

/*    public static void setSensorForDevice(Device device, Sensor sensor) {
        device.
    }*/
}
