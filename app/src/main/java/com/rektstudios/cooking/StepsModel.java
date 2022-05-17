package com.rektstudios.cooking;

public class StepsModel {
    private String stepDescription;
    private int stepTimeInSeconds;

    public StepsModel(String stepDescription, int stepTimeInSeconds) {
        this.stepDescription = stepDescription;
        this.stepTimeInSeconds = stepTimeInSeconds;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public int getStepTimeInSeconds() {
        return stepTimeInSeconds;
    }

    public void setStepTimeInSeconds(int stepTimeInSeconds) {
        this.stepTimeInSeconds = stepTimeInSeconds;
    }
}
