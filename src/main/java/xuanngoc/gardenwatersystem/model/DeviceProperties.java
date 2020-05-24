package xuanngoc.gardenwatersystem.model;

public interface DeviceProperties {

    int getId();
    String getName();
    String getStatus();
    boolean getState();
    boolean getIs_manual();
    int getDevice_type_id();
    int getGarden_id();

}