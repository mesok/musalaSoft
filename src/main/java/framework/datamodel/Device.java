package framework.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {

    @JsonProperty("name")
    public String name;

    @JsonProperty("type")
    public String type;

    @JsonProperty("height")
    public int height;

    @JsonProperty("width")
    public int width;

    @JsonProperty("isFullscreen")
    public boolean isFullscreen;
}
