package edu.illinois.cs.cs125.mp7;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
    public static String getUS(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("USD")) {
                return rates.get("USD").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public static String getUK(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("GBP")) {
                return rates.get("GBP").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getChina(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("CNY")) {
                return rates.get("CNY").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getEuro(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("EUR")) {
                return rates.get("EUR").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
