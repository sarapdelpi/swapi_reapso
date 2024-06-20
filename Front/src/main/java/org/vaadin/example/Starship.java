package org.vaadin.example;

public class Starship {


    //Variables
    private String name;
    private String model;
    private String manufacturer;
    private String length;
    private String crew;

//Constructors
    public Starship() {
    }

    public Starship(String name, String model, String manufacturer, String length, String crew) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.length = length;
        this.crew = crew;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }


    //toString

    @Override
    public String toString() {
        return "{\n" +
                "\t\"name\": " + name +
                ",\t\"model\": \"" + model + '\"' +
                ",\t\"manufacturer\": \"" + manufacturer + '\"' +
                ",\t\"length\": \"" + length + '\"' +
                ",\t\"crew\": \"" + crew + '\"' +
                "\n}";
    }

}
