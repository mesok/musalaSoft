package framework.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Url {

    @JsonProperty("label")
    public String label;

    @JsonProperty("locale")
    public String locale;

    @JsonProperty("country")
    public String country;

    @JsonProperty("url")
    public String url;
    
    @JsonProperty("product")
    public String product;
}
