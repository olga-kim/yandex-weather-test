package response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AcceptableValues {

    public static List<String> getConditions() {
        return Collections.unmodifiableList(
                Arrays.asList("clear",
                        "partly",
                        "cloudy",
                        "overcast",
                        "partly-cloudy-and-light-rain",
                        "partly-cloudy-and-rain",
                        "overcast-and-rain",
                        "overcast-thunderstorms-with-rain",
                        "cloudy-and-light-rain",
                        "overcast-and-light-rain",
                        "cloudy-and-rain",
                        "overcast-and-wet-snow",
                        "partly-cloudy-and-light-snow",
                        "partly-cloudy-and-snow",
                        "overcast-and-snow",
                        "cloudy-and-light-snow",
                        "overcast-and-light-snow",
                        "cloudy-and-snow"));
    }

    public static List<String> getWindDirections() {
        return Collections.unmodifiableList(
                Arrays.asList("nw",
                        "n",
                        "ne",
                        "e",
                        "se",
                        "s",
                        "sw",
                        "w",
                        "с"));
    }

    public static List<String> getDayTimes() {
        return Collections.unmodifiableList(
                Arrays.asList("d",
                        "n"));
    }

    public static List<String> getSeasons() {
        return Collections.unmodifiableList(
                Arrays.asList("summer",
                        "autumn",
                        "winter",
                        "spring"));
    }

    public static List<Integer> getPrecipitationTypes() {
        return Collections.unmodifiableList(
                Arrays.asList(0,
                        1,
                        2,
                        3));
    }

    public static List<Double> getPrecipitationStrength() {
        return Collections.unmodifiableList(
                Arrays.asList(0D,
                        0.25,
                        0.5,
                        0.75,
                        1D));
    }

    public static List<Double> getCloudiness() {
        return Collections.unmodifiableList(
                Arrays.asList(0D,
                        0.25,
                        0.5,
                        0.75,
                        1D));
    }



    /*CLEAR(),
    PARTLY(),
    CLOUDY(),
    OVERCAST(),
    PARTLY_CLOUDY_AND_LIGHT_RAIN(),
    PARTLY_CLOUDY_AND_RAIN(),
    OVERCAST_AND_RAIN(),
    OVERCAST_THUNDERSTORMS_WITH_RAIN(),
    CLOUDY_AND_LIGHT_RAIN(),
    OVERCAST_AND_LIGHT_RAIN(),
    CLOUDY_AND_RAIN(),
    OVERCAST_AND_WET_SNOW(),
    PARTLY_CLOUDY_AND_LIGHT_SNOW(),
    PARTLY_CLOUDY_AND_SNOW(),
    OVERCAST_AND_SNOW(),
    CLOUDY_AND_LIGHT_SNOW(),
    OVERCAST_AND_LIGHT_SNOW(),
    CLOUDY_AND_SNOW();

    private String description;

    Condition(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }*/
}
