package xuanngoc.gardenwatersystem.model;

import javax.persistence.*;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Version
    private Integer version;

    private String name;
    private double maxHumidity;
    private double minHumidity;
    private double maxTemperature;
    private double minTemperature;
    /*private double maxPh;
    private double minPh;*/
    private double area;

    public void setId(Integer id) {
        this.Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

/*    public double getMaxPh() {
        return maxPh;
    }

    public void setMaxPh(double maxPh) {
        this.maxPh = maxPh;
    }

    public double getMinPh() {
        return minPh;
    }

    public void setMinPh(double minPh) {
        this.minPh = minPh;
    }*/

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
