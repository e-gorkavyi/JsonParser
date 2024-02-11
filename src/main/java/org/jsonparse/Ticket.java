package org.jsonparse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("departure_date")
    @Expose
    private LocalDate departureDate;
    @SerializedName("departure_time")
    @Expose
    private LocalTime departureTime;
    @SerializedName("arrival_date")
    @Expose
    private LocalDate arrivalDate;
    @SerializedName("arrival_time")
    @Expose
    private LocalTime arrivalTime;
    @SerializedName("carrier")
    @Expose
    private String carrier;
    @SerializedName("stops")
    @Expose
    private int stops;
    @SerializedName("price")
    @Expose
    private int price;
}
