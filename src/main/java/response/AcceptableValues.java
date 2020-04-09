package response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static List<Integer> getMoonCodes() {
        return Collections.unmodifiableList(
                IntStream.rangeClosed(0, 15)
                        .boxed()
                        .collect(Collectors.toList()));
    }

    public static List<String> getMoonTexts() {
        return Collections.unmodifiableList(
                Arrays.asList("full-moon",
                        "decreasing-moon",
                        "last-quarter",
                        "new-moon",
                        "growing-moon",
                        "first-quarter"));
    }
}
