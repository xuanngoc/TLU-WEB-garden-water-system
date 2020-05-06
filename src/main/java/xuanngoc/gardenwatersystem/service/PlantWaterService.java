package xuanngoc.gardenwatersystem.service;

import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Sensor;

public class PlantWaterService {

    public static void plantWater(Sensor sensor, Double value) {
        Device device = sensor.getDevice();
        double minHumidity = device.getGarden().getPlant().getMinHumidity();
        double maxHumidity = device.getGarden().getPlant().getMaxHumidity();

        if (value < minHumidity) {
            device.setState(true);
        } else if (value > maxHumidity) {
            device.setState(false);
        }
    }
    

}
