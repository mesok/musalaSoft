package framework.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Events {

	@JsonProperty("id")
    public String id;
	
	@JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    @JsonProperty("date")
    public String date;
    
    @JsonProperty("location")
    public String location;
    
}
