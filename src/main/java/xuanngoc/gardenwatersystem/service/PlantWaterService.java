package xuanngoc.gardenwatersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xuanngoc.gardenwatersystem.model.Device;
import xuanngoc.gardenwatersystem.model.Plant;
import xuanngoc.gardenwatersystem.model.Sensor;
import xuanngoc.gardenwatersystem.model.SensorValue;

@Service
public class PlantWaterService {

    // Retrieve data each 10 seconds
    public static final double UPDATE_DATA_TIME = 10;

    public static final String WORKING_AUTO = "Hoạt động tự động";
    public static final String WORKING_MANUAL = "Hoạt động thủ công";
    public static final String FIXING = "Đang sửa chữa";
    public static final String BROKEN_DOWN = "Không hoạt động";
    public static final String WORKING = "Hoạt động";

    private SensorValueService sensorValueService;
    private GardenService gardenService;
    private DeviceService deviceService;

    @Autowired
    public void setSensorValueService(SensorValueService sensorValueService) {
        this.sensorValueService = sensorValueService;
    }

    @Autowired
    public void setGardenService(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public void plantWater(Integer gardenId) {
        double nearestTimeHumility = sensorValueService.findAvgTop1DateTimeHumilityValueByGardenId(gardenId);
        double nearestTimeTemperature = sensorValueService.findAvgTop1DateTimeTemperatureValueByGardenId(gardenId);

        Plant plant = gardenService.getById(gardenId).getPlant();

        double plantMinHumidity = plant.getMinHumidity();
        double plantMaxHumidity = plant.getMaxHumidity();

        double plantMinTemperature = plant.getMinTemperature();
        double plantMaxTemperature = plant.getMaxTemperature();

        if (nearestTimeHumility < plantMinHumidity) {
            gardenService.getById(gardenId).getDevices().forEach(device -> {
                if (device.getDeviceType().getName().contains("Máy bơm") && device.getStatus().equals(WORKING_AUTO)) {
                    device.setState(true);
                    deviceService.saveOrUpdate(device);
                }
            });
        } else if (nearestTimeHumility > plantMaxHumidity) {
            gardenService.getById(gardenId).getDevices().forEach(device -> {
                if (device.getDeviceType().getName().contains("Máy bơm") && device.getStatus().equals(WORKING_AUTO)) {
                    device.setState(false);
                    deviceService.saveOrUpdate(device);
                }
            });
        }

        if (nearestTimeTemperature < plantMinTemperature) {
            gardenService.getById(gardenId).getDevices().forEach(device -> {
                if (device.getDeviceType().getName().contains("Máy phun sương") && device.getStatus().equals(WORKING_AUTO)) {
                    device.setState(false);
                    deviceService.saveOrUpdate(device);
                }
            });
        } else if (nearestTimeTemperature > plantMaxTemperature) {
            gardenService.getById(gardenId).getDevices().forEach(device -> {
                if (device.getDeviceType().getName().contains("Máy phun sương") && device.getStatus().equals(WORKING_AUTO)) {
                    device.setState(true);
                    deviceService.saveOrUpdate(device);
                }
            });
        }

    }

    public static boolean isDeviceWorking(Device device) {
        return device.getStatus().equals(WORKING_AUTO);
    }

    public static boolean isSensorWorking(Sensor sensor) {
        return sensor.getStatus().equals(WORKING);
    }


}
