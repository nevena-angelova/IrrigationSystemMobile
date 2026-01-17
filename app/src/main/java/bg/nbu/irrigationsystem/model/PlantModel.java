package bg.nbu.irrigationsystem.model;

import java.io.Serializable;
import java.util.List;

public class PlantModel implements Serializable {

    private int id;
    private String plantingDate;
    private PlantTypeModel plantType;
    private ReportModel report;
    private int deviceId;
    private int relayId;

    public int getId() {
        return id;
    }

    public String getPlantingDate() {
        return plantingDate;
    }

    public PlantTypeModel getPlantType() {
        return plantType;
    }

    public ReportModel getReport() {
        return report;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getRelayId() {
        return relayId;
    }

    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon() {
        if (plantType != null && plantType.getName() != null) {
            switch (plantType.getName()) {
                case "Домат":
                    this.icon = "🍅";
                    break;
                case "Ягода":
                    this.icon = "🍓";
                    break;
                case "Морков":
                    this.icon = "🥕";
                    break;
                case "Картоф":
                    this.icon = "🥔";
                    break;
                default:
                    this.icon = "";
            }
        } else {
            this.icon = "";
        }
    }

    public String getGrowthPhaseName() {
        return report.getGrowthPhaseName();
    }

    public String getGrowthPhaseDetails() {
        return report.getGrowthPhaseDetails();
    }

    public double getSoilMoisture() {
        return report.getSoilMoisture();

    }

    public boolean needsIrrigation() {
        return report.needsIrrigation();
    }

    public List<String> getWarnings() {
        return report.getWarnings();
    }

    public double getMaxSoilMoisture() {
        return report.getMaxSoilMoisture();
    }

    public double getIrrigationDuration() {
        return report.getIrrigationDuration();
    }

    public double getMinSoilMoisture() {
        return report.getMinSoilMoisture();
    }
}
