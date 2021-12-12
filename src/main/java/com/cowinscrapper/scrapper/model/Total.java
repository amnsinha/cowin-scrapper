package com.cowinscrapper.scrapper.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Total{
    public int other;
    public int confirmed;
    public int deceased;
    public int recovered;
    public int tested;
    public int vaccinated1;
    public int vaccinated2;
}
