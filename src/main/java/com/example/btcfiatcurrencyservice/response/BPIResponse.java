package com.example.btcfiatcurrencyservice.response;

import java.util.Map;

public class BPIResponse {
    private Map<String, String> time;
    private String disclaimer;
    private Map<String, Map<String, String>> bpi;

    public Map<String, String> getTime() {
        return time;
    }

    public void setTime(Map<String, String> time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, Map<String, String>> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Map<String, String>> bpi) {
        this.bpi = bpi;
    }
}
