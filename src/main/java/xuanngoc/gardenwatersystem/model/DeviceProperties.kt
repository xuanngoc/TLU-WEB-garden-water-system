package xuanngoc.gardenwatersystem.model

interface DeviceProperties {

    fun getId(): Int;
    fun getName(): String;
    fun getStatus(): String;
    fun getState(): Boolean;
    fun getIs_manual(): Boolean;
    fun getDevice_type_id(): Int;
    fun getGarden_id(): Int;

}