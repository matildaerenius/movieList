import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MovieHOF {

    // HÖGRE ORDNINGENS FUNKTIONER

    // MovieHOF som räknar filmer från en Predicate<T>
    public static long countMovies(List<Movie> movies, Predicate<Movie> condition) {
        return movies.stream()
                .filter(condition)
                .count();
    }

    // MovieHOF för att hitta största filmen med en Comparator, returnerar en Optional, kan ju vara en tom lista
    public static Optional<Movie> findMax(List<Movie> movies, Comparator<Movie> comp) {
        return movies.stream()
                .max(comp);
    }



    // SPECIALISERADE FUNKTIONER, bara för att kunna visa i main :D

    public static long countMoviesFrom1975HOF(List<Movie> movies) {
        return countMovies(movies, m -> m.year() == 1975);
    }

    // Denna returnerar endast cast från EN film med högst rating, gjort annorlunda i MovieFunctions fyi
    public static List<String> castOfHighestRatedMovieHOF(List<Movie> movies) {
        return findMax(movies, Comparator.comparingDouble(Movie::imdbRating))
                .map(Movie::cast)
                .orElse(List.of());
    }

    public static int longestMovieRuntimeHOF(List<Movie> movies) {
        return findMax(movies, Comparator.comparingInt(Movie::runtime))
                .map(Movie::runtime)
                .orElse(0);
    }

}
