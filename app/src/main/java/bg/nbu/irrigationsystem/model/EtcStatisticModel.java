package bg.nbu.irrigationsystem.model;

import java.time.LocalDate;

public class EtcStatisticModel {
    private int controllerId;

    private String plantType;

    private String date;

    private double tMin;

    private double tMax;

    private double tMean;

    private double rhMin;

    private double rhMax;

    private double etc;

    public int getControllerId(){
        return this.controllerId;
    }

    public void setControllerId(int controllerId) {
        this.controllerId = controllerId;
    }

    public String getPlantType(){
        return this.plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double gettMin() {
        return tMin;
    }

    public void settMin(double tMin) {
        this.tMin = tMin;
    }

    public double gettMax() {
        return tMax;
    }

    public void settMax(double tMax) {
        this.tMax = tMax;
    }

    public double gettMean() {
        return tMean;
    }

    public void settMean(double tMean) {
        this.tMean = tMean;
    }

    public double getRhMin() {
        return rhMin;
    }

    public void setRhMin(double rhMin) {
        this.rhMin = rhMin;
    }

    public double getRhMax() {
        return rhMax;
    }

    public void setRhMax(double rhMax) {
        this.rhMax = rhMax;
    }

    public double getEtc() {
        return etc;
    }

    public void setEtc(double etc) {
        this.etc = etc;
    }
}
