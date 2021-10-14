package net.cityzen.apx;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import lombok.Data;

@Data
public class Quote {

    @JsonProperty(value = "market", required = true)
    private String market;

    @JsonProperty(value = "date_applied", required = true)
    private long dateApplied;

    @JsonProperty(value = "values", required = true)
    private List<QuoteValue> quoteValues;

    public Quote() { }


    public QuoteValue getQuoteByTlabel(String desiredTlabel) {
            for (QuoteValue currQuote : quoteValues) {
                if (currQuote.getTLabel().equals(desiredTlabel))
                    return currQuote;
            }
            throw new AssertionError(desiredTlabel+" tLabel not found!");

    }
}
