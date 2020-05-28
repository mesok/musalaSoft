package framework.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteDetails {

	@JsonProperty("name")
    public String name;

    @JsonProperty("url")
    public String url;

    @JsonProperty("resourceFolder")
    public String resourceFolder;
    
}
