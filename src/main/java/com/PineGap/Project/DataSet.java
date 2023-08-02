package com.PineGap.Project;

import java.io.Serializable;
import java.util.ArrayList;

public class DataSet implements Serializable {

    private int id;
    private String name;
    private String description;
    private int minimum;
    private int maximum;
    private double pricePerDataPoint;
    private ArrayList < String > dataPoints = new ArrayList < String > ();
    private static boolean dataSetsInitialised = false;
    private boolean visible = true;
    private boolean bought = false;
    
    // list of all datasets
    private static ArrayList < DataSet > listofDataSets = new ArrayList < DataSet > ();

    public DataSet(String name, String description, int minimum, int maximum,
        double pricePerDataPoint, ArrayList < String > dataPoints) {
        this.id = listofDataSets.size() + 1;
        this.name = name;
        this.description = description;

        if(maximum < 0 ){
            throw new IllegalArgumentException("Maximum cannot be less than 0");
        } else{
            this.maximum = maximum;
        }

        if(minimum < 0 ){
            throw new IllegalArgumentException("Minimum cannot be less than 0");
        } else{
            this.minimum = minimum;
        }

        if(pricePerDataPoint < 0){
            throw new IllegalArgumentException("Price per datapoint must be >=0");
        } else{
            this.pricePerDataPoint = pricePerDataPoint;
        }
        this.dataPoints = dataPoints;
    }

    static void initaliseDataSet() {
        listofDataSets.add(new DataSet("Angling Stands - Roscommon ", "Angling Stand Locations in County Roscommon",
            2, 10, 20.60, 
            ReadDataPoint.readData("Datasets/Angling-Stands-Roscommon-2.csv")));

        listofDataSets.add(new DataSet("COVID-19 HPSC County Statistics ",
            "Covid case/death stats by county", 3, 11, 21.60,
            ReadDataPoint.readData("Datasets/COVID-19_HPSC_County_Statistics_Latest_Point_Geometry.csv")));

        listofDataSets.add(new DataSet("Oil Monthly Deliveries to Ireland ",
            "Oil Monthly Deliveries to Ireland", 3, 11, 21.60,
            ReadDataPoint.readData("Datasets/OilProducts_monthlyDeliveries_2018-1.csv")));

        listofDataSets.add(new DataSet("Hydro Energy Connections ",
            "Datasets of hydro energy generators connected to the electricity network are downloadable as GIS shapefiles",
            3, 11, 21.60, ReadDataPoint.readData("DataSets/Hydro_connections.csv")));

        listofDataSets.add(new DataSet("Local Property Tax (LPT) statistics 2021 ", 
        "Various Local Property Tax statistics including local authority breakdown as at end 2021",
            2, 10, 20.60,
            ReadDataPoint.readData("DataSets/local-property-tax-2021.csv")));

        listofDataSets.add(new DataSet("Sports and Recreation Clubs 2",
            " Youth Facilities ", 3, 11, 21.60,
            ReadDataPoint.readData("Datasets/Community_Sports_and_Youth_Centres.csv")));

        listofDataSets.add(new DataSet("Sligo Garda Stations ",
            "The datasets contains the Location of Garda Stations with contact information. Garda Stations in Sligo with location, address, division, district, and contact information",
            2, 10, 20.60, 
            ReadDataPoint.readData("Datasets/garda-stations.csv")));

        dataSetsInitialised = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public double getPricePerDataPoint() {
        return pricePerDataPoint;
    }

    public static ArrayList < DataSet > getDataSets() {
        return listofDataSets;
    }

    public static int getNumberDataSets() {
        return listofDataSets.size();
    }

    public static boolean isDataSetInitialised() {
        return dataSetsInitialised;
    }

    public int getNumberDataPoints() {
        return dataPoints.size();
    }

    public boolean isVisible(){
        return visible;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setMinimum(int minimum){
        this.minimum = minimum;
    }

    public void setMaximum(int maximum){
        this.maximum = maximum;
    }

    public void setPriceDataPoint(double pricePerDataPoint){
        this.pricePerDataPoint = pricePerDataPoint;
    }

    public void setVisible(){
        visible = true;
    }

    public void setInVisible(){
        visible = false;
    }

    public boolean isBought() {
        return bought;
    }
}