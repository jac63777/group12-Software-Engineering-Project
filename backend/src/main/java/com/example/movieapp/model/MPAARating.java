package com.example.movieapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MPAARating {
    G("G"), 
    PG("PG"), 
    PG13("PG-13"), // Changed to PG13 (no hyphen)
    R("R"), 
    NC17("NC-17"); // Changed to NC17 (no hyphen)

    private final String value;

    MPAARating(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MPAARating fromValue(String value) {
        for (MPAARating rating : values()) {
            if (rating.value.equalsIgnoreCase(value) || rating.name().equalsIgnoreCase(value)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown MPAARating: " + value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

