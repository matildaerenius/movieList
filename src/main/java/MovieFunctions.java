import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieFunctions {

    public static long countMoviesFrom1975(List<Movie> movies) {
        return movies.stream()
                .filter(x -> x.year() == 1975)
                .count();
    }

    public static int longestMovieRuntime(List<Movie> movies) {
        return movies.stream()
                .mapToInt(Movie::runtime)
                .max()
                .orElse(0);
    }

    public static long uniqueGenresFrom1975(List<Movie> movies) {
        return movies.stream()
                .filter(x -> x.year() == 1975)
                .flatMap(m -> m.genres().stream())
                .distinct()
                .count();
    }

    public static List<String> castOfHighestRatedMovie(List<Movie> movies) {
        double maxRating = movies.stream()
                .mapToDouble(Movie::imdbRating)
                .max()
                .orElse(0.0);

        return movies.stream()
                .filter(movie -> movie.imdbRating() == maxRating)
                .flatMap(movie -> movie.cast().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static String titleOfLeastAmountOfCast(List<Movie> movies) {
        int minCast = movies.stream()
                .mapToInt(movie -> movie.cast().size())
                .min()
                .orElse(0);

        return movies.stream()
                .filter(movie -> movie.cast().size() == minCast)
                .map(Movie::title)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public static long actorsInMoreThanOneMovie(List<Movie> movies) {
//        return movies.stream()
//                .flatMap(x -> x.cast().stream())
//                .distinct()
//                .filter(actor -> movies.stream()
//                        .filter(film -> film.cast().contains(actor))
//                        .count() > 1)
//                .count();

        Map<String, Long> actorCount = countMap(movies);
        return actorCount.values().stream()
                .filter(count -> count > 1)
                .count();
    }

    public static String mostFeaturedActor(List<Movie> movies) {
//        return movies.stream()
//                .flatMap(x -> x.cast().stream())
//                .distinct()
//                .max(Comparator.comparingLong(actor ->
//                        movies.stream()
//                                .filter(y -> y.cast().contains(actor))
//                                .count()))
//                .orElse("No movie found :(");


//        Map<String, Long> actorCount = countMap(movies);
//        return actorCount.keySet().stream()
//                .max(Comparator.comparingLong(actorCount::get))
//                .orElse("No actor found :(");

        Map<String, Long> actorCount = countMap(movies);

        // Högsta antalet filmer för skådis
        long maxCount = actorCount.values().stream()
                .max(Long::compare)
                .orElse(0L);

        // Skådisar med det maxvärdet filtreras
        List<String> mostFeaturedActors = actorCount.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        return String.join(", ", mostFeaturedActors);

    }

    // Brutit ut nedan kodsnutt till en egen metod så kod inte blir duplicerad
    public static Map<String, Long> countMap(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.cast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));
    }

    public static long uniqueLanguages(List<Movie> movies) {
        return movies.stream()
                .flatMap(x -> x.languages().stream())
                .distinct()
                .count();
    }

    public static boolean isAnyTitleDuplicated(List<Movie> movies) {
        return movies.stream()
                .map(Movie::title)
                .distinct()
                .count() < movies.size();
    }

}
