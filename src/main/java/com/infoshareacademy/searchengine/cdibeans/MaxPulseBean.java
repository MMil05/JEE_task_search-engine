package com.infoshareacademy.searchengine.cdibeans;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MaxPulseBean implements MaxPulse{

    @Override
    public double calculateMaxPulseMen(int age){
        return 202 - (0.55 * age);
    }

    @Override
    public double calculateMaxPulseWomen(int age){
        return 206 - (1.09 * age);
    }
}
