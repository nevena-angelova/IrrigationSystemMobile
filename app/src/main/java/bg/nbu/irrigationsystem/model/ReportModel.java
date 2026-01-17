package bg.nbu.irrigationsystem.model;

import java.util.List;

public class ReportModel {

    private long plantId;
    private boolean needsIrrigation;
    private String growthPhaseName;
    private String growthPhaseDetails;
    private double soilMoisture;
    private double minSoilMoisture;
    private double maxSoilMoisture;
    private int irrigationDuration;
    private List<String> warnings;

    public long getPlantId() {
        return plantId;
    }

    public boolean needsIrrigation() {
        return needsIrrigation;
    }

    public String getGrowthPhaseName() {
        return growthPhaseName;
    }

    public String getGrowthPhaseDetails() {
        return growthPhaseDetails;
    }

    public double getSoilMoisture() {
        return soilMoisture;
    }

    public double getMinSoilMoisture() {
        return minSoilMoisture;
    }

    public double getMaxSoilMoisture() {
        return maxSoilMoisture;
    }

    public int getIrrigationDuration() {
        return irrigationDuration;
    }

    public List<String> getWarnings() {
        return warnings;
    }
}
