package com.example.wahyunainggolan.bola.model;

/**
 * Created by Wahyu Nainggolan on 20/06/2018.
 */

public class Predict {
    private String href;
    private String hometeam;
    private String awayteam;

    public Predict(String href, String hometeam, String awayteam) {
        this.href = href;
        this.hometeam = hometeam;
        this.awayteam = awayteam;
    }


    public Predict() {
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHometeam() {
        return hometeam;
    }

    public void setHometeam(String hometeam) {
        this.hometeam = hometeam;
    }

    public String getAwayteam() {
        return awayteam;
    }

    public void setAwayteam(String awayteam) {
        this.awayteam = awayteam;
    }
}

