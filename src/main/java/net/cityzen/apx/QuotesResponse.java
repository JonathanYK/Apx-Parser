package net.cityzen.apx;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuotesResponse {

    @JsonProperty(value = "quote", required = true)
    private List<Quote> quotes;

    public QuotesResponse() { }

}
