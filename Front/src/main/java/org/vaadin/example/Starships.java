package org.vaadin.example;

import java.util.ArrayList;

public class Starships {

    //Variables
    ArrayList<Starship> starships;

    //Constructors
    public Starships() {
    }

    public Starships(ArrayList<Starship> starships) {
        this.starships = starships;
    }

    //Getters and Setters

    public ArrayList<Starship> getStarships() {
        return starships;
    }

    public void setStarships(ArrayList<Starship> starships) {
        this.starships = starships;
    }


    //toString

    @Override
    public String toString() {
        return "Starships{" +
                "starships=" + starships +
                '}';
    }
}
