package net.cityzen.apx;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class allMarketDatas {

    @JsonProperty("allAvailableDatas")
    private List<MarketDataPerHour> allAvailableDatas;

    public allMarketDatas() {
        allAvailableDatas = new ArrayList<>();
    }
    public void addMarketDataToallDatas(MarketDataPerHour mdh) {
        allAvailableDatas.add(mdh);
    }
}
