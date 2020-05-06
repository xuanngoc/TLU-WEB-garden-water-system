package xuanngoc.gardenwatersystem.service;

import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Sensor;

public class PlantWaterService {

    private static final String WORKING = "Hoạt động";
    private static final String FIXING = "Đang sửa chữa";
    private static final String BROKEN_DOWN = "Không hoạt động";

    public static boolean plantWater(Sensor sensor, Double value) {
        Device device = sensor.getDevice();
        double minHumidity = device.getGarden().getPlant().getMinHumidity();
        double maxHumidity = device.getGarden().getPlant().getMaxHumidity();

        if (value < minHumidity) {
            return true;
        } else if (value > maxHumidity) {
            return false;
        } else if (device.getStatus().equals(FIXING) || device.getStatus().equals(BROKEN_DOWN)) {
            return false;
        }
        return false;
    }

    public static boolean checkStatusDevice(Device device) {
        return !(device.getStatus().equals(FIXING) || device.getStatus().equals(BROKEN_DOWN));
    }

    public static boolean isSensorWorking(Sensor sensor) {
        return sensor.getStatus().equals(WORKING);
    }
}
