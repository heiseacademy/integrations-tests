package com.github.sparsick.heise.integration;

import com.google.gson.Gson;

import java.util.Objects;

public class Starship {
    private String name;
    private String model;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;
    private String cargoCapacity;
    private String consumables;
    private String hyperdriveRating;
    private String mglt;
    private String starshipClass;


    public static Starship from(String json) {

        Gson gson = new Gson();
        return  gson.fromJson(json, Starship.class);
//        Starship starship = new Starship();
//        starship.name = jsonMap.get("name").getAsString();
//        starship.model = jsonMap.get("model").getAsString();
//        starship.costInCredits = jsonMap.get("cost_in_credits").getAsString();
//        starship.length = Double.parseDouble(jsonMap.get("length").getAsString());
//        starship.maxAtmospheringSpeed = jsonMap.get("max_atmosphering_speed").getAsString();
//        starship.crew = Integer.parseInt(jsonMap.get("crew").getAsString());
//        starship.passengers = Integer.parseInt(jsonMap.get("passengers").getAsString());
//        starship.cargoCapacity = jsonMap.get("cargo_capacity").getAsString();
//        starship.consumables = jsonMap.get("consumables").getAsString();
//        starship.hyperdriveRating = jsonMap.get("hyperdrive_rating").getAsDouble();
//        starship.mglt = jsonMap.get("MGLT").getAsInt();
//        starship.starshipClass = jsonMap.get("starship_class").getAsString();
//        return starship;
    }

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

    public String getCostInCredits() {
        return costInCredits;
    }

    public void setCostInCredits(String costInCredits) {
        this.costInCredits = costInCredits;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public String getMglt() {
        return mglt;
    }

    public void setMglt(String mglt) {
        this.mglt = mglt;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Starship starship = (Starship) o;
        return Objects.equals(name, starship.name) && Objects.equals(model, starship.model) && Objects.equals(manufacturer, starship.manufacturer) && Objects.equals(costInCredits, starship.costInCredits) && Objects.equals(length, starship.length) && Objects.equals(maxAtmospheringSpeed, starship.maxAtmospheringSpeed) && Objects.equals(crew, starship.crew) && Objects.equals(passengers, starship.passengers) && Objects.equals(cargoCapacity, starship.cargoCapacity) && Objects.equals(consumables, starship.consumables) && Objects.equals(hyperdriveRating, starship.hyperdriveRating) && Objects.equals(mglt, starship.mglt) && Objects.equals(starshipClass, starship.starshipClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, manufacturer, costInCredits, length, maxAtmospheringSpeed, crew, passengers, cargoCapacity, consumables, hyperdriveRating, mglt, starshipClass);
    }

    @Override
    public String toString() {
        return "Starship{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", costInCredits='" + costInCredits + '\'' +
                ", length=" + length +
                ", maxAtmospheringSpeed='" + maxAtmospheringSpeed + '\'' +
                ", crew=" + crew +
                ", passengers=" + passengers +
                ", cargoCapacity=" + cargoCapacity +
                ", consumables='" + consumables + '\'' +
                ", hyperdriveRating=" + hyperdriveRating +
                ", mglt=" + mglt +
                ", starshipClass='" + starshipClass + '\'' +
                '}';
    }
}
