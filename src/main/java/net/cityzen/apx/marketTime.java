package net.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class marketTime {

    @JsonProperty("market")
    private String marketName;

    @JsonProperty("date_applied")
    private long dateApplied;

    @JsonProperty("date_applied_simple_format")
    private String fullDate;

    @JsonProperty("net_volume")
    private double netVol;

    @JsonProperty("price")
    private double price;

    public marketTime() { }
}
