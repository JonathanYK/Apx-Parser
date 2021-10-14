package net.cityzen.apx;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuoteValue {

    @JsonProperty(value = "tLabel", required = true)
    private String tLabel;

    @JsonProperty(value = "value", required = true)
    private double value;

    public QuoteValue() { }


}
