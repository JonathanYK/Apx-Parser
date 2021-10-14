package net.cityzen.apx;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarketDataPerHour {

    @JsonProperty("hour")
    private int hour;

    @JsonProperty("data")
    private List<marketTime> data;

    public MarketDataPerHour() {
        data = new ArrayList<>();
    }

    public void addMarketTimeToList(marketTime time) {
        data.add(time);
    }
}
