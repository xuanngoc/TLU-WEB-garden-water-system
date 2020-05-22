package xuanngoc.gardenwatersystem.model;

interface SensorProperties {

    fun getId(): Int;
    fun getName(): String;
    fun getStatus(): String;
    fun getState(): Boolean;
    fun getSensor_type_id(): Int;
    fun getGarden_id(): Int;

}
