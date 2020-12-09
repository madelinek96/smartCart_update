package com.example.foodquiz;

import android.app.Activity;

import java.util.Map;

public class QuizResponseActivity extends Activity{

    private String dietResponse1;
    private String dietResponse2;
    private String dietResponse3;
    private Boolean preferenceResponse;
    private Boolean avoidsResponse;
    private String  prepResponse;
    private Integer healthgoalResponse;
    private Integer budgetResponse;




    /* GETTERS */
    public String getDietResponse1(){
        return dietResponse1;
    }
    public String getDietResponse2(){
        return dietResponse2;
    }
    public String getDietResponse3(){
        return dietResponse3;
    }
    public Boolean getPreferenceResponse(){
        return preferenceResponse;
    }
    public Boolean getAvoidsResponse(){
        return avoidsResponse;
    }
    public String getPrepResponse(){
        return prepResponse;
    }
    public Integer getHealthgoalResponse() { return healthgoalResponse; }
    public Integer getBudgetResponse() { return budgetResponse; }

    /* SETTERS */
    public void setDietResponse1(String dietResponse1) {
        this.dietResponse1 = dietResponse1;
    }

    public void setDietResponse2(String dietResponse2) {
        this.dietResponse2 = dietResponse2;
    }

    public void setDietResponse3(String dietResponse3) {
        this.dietResponse3 = dietResponse3;
    }

    public void setPreferenceResponse(Boolean preferenceResponse) {
        this.preferenceResponse = preferenceResponse;
    }

    public void setAvoidsResponse(Boolean avoidsResponse) {
        this.avoidsResponse = avoidsResponse;
    }

    public void setPrepResponse(String prepResponse) {
        this.prepResponse = prepResponse;
    }

    public void setHealthgoalResponse(Integer healthgoalResponse) { this.healthgoalResponse = healthgoalResponse; }
    public void setBudgetResponse(Integer budgetResponse) { this.budgetResponse = budgetResponse; }


}
