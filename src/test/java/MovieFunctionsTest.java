import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MovieFunctionsTest {

    private final List<Movie> sampleMovies = Arrays.asList(
            new Movie(
                    "1",
                    "Jaws",
                    1975,
                    124,
                    Arrays.asList("Adventure", "Drama", "Thriller"),
                    "Steven Spielberg",
                    Arrays.asList("Roy Scheider", "Robert Shaw", "Richard Dreyfuss"),
                    8.1,
                    List.of("English")
            ),
            new Movie(
                    "2",
                    "Dog Day Afternoon",
                    1975,
                    125,
                    Arrays.asList("Crime", "Drama"),
                    "Sidney Lumet",
                    Arrays.asList("Al Pacino", "John Cazale"),
                    8.0,
                    List.of("English")
            ),
            new Movie(
                    "3",
                    "Movie from 1976",
                    1976,
                    99,
                    List.of("Comedy"),
                    "Some Director",
                    Arrays.asList("Actor A", "Actor B"),
                    6.5,
                    Arrays.asList("English", "French")
            ),
            new Movie(
                    "4",
                    "Monty Python and the Holy Grail",
                    1975,
                    91,
                    Arrays.asList("Adventure", "Comedy", "Fantasy"),
                    "Terry Gilliam, Terry Jones",
                    Arrays.asList("Graham Chapman", "John Cleese", "Eric Idle"),
                    8.2,
                    Arrays.asList("English", "French", "Latin")
            ),
            new Movie(
                    "5",
                    "Duplicate Title",
                    1977,
                    110,
                    List.of("Horror"),
                    "Director X",
                    Arrays.asList("Actor A", "Richard Dreyfuss"),
                    7.3,
                    List.of("English")
            ),
            new Movie(
                    "6",
                    "Duplicate Title",
                    1979,
                    120,
                    List.of("Action"),
                    "Director Y",
                    Arrays.asList("Robert Shaw", "Someone Else"),
                    7.1,
                    List.of("German")
            )
    );

    @Test
    public void testCountMoviesFrom1975() {
        long result = MovieFunctions.countMoviesFrom1975(sampleMovies);
        Assertions.assertEquals(3, result);
        Assertions.assertNotEquals(1, result);
    }

    @Test
    public void testLongestMovieRuntime() {
        int result = MovieFunctions.longestMovieRuntime(sampleMovies);
        Assertions.assertEquals(125, result);
        Assertions.assertNotEquals(90, result);
    }

    @Test
    public void testUniqueGenresFrom1975() {
        long result = MovieFunctions.uniqueGenresFrom1975(sampleMovies);
        Assertions.assertEquals(6, result);
        Assertions.assertNotEquals(5, result);
    }

    @Test
    public void testCastOfHighestRatedMovie() {
        List<String> cast = MovieFunctions.castOfHighestRatedMovie(sampleMovies);
        Assertions.assertTrue(cast.contains("Graham Chapman"));
        Assertions.assertTrue(cast.contains("John Cleese"));
        Assertions.assertTrue(cast.contains("Eric Idle"));
        Assertions.assertEquals(3, cast.size());
        Assertions.assertNotEquals(1, cast.size());
    }

    @Test
    public void testTitleOfLeastAmountOfCast() {
        String title = MovieFunctions.titleOfLeastAmountOfCast(sampleMovies);
        String expected = "Dog Day Afternoon, Movie from 1976, Duplicate Title";
        Assertions.assertEquals(expected, title);
    }

    @Test
    public void testActorsInMoreThanOneMovie() {
        long result = MovieFunctions.actorsInMoreThanOneMovie(sampleMovies);
        Assertions.assertEquals(3, result);
        Assertions.assertNotEquals(1, result);
    }

    @Test
    public void testMostFeaturedActor() {
        String actor = MovieFunctions.mostFeaturedActor(sampleMovies);
        String expected = "Actor A, Richard Dreyfuss, Robert Shaw";
        Assertions.assertEquals(expected, actor);

    }

    @Test
    public void testUniqueLanguages() {
        long result = MovieFunctions.uniqueLanguages(sampleMovies);
        Assertions.assertEquals(4, result);
        Assertions.assertNotEquals(1, result);
    }

    @Test
    public void testIsAnyTitleDuplicated() {
        boolean result = MovieFunctions.isAnyTitleDuplicated(sampleMovies);
        Assertions.assertTrue(result);
        Assertions.assertFalse(false);
    }


    // MovieHOF tester
    @Test
    public void testCountMovies() {
        Predicate<Movie> isFrom1975 = movie -> movie.year() == 1975;
        long count = MovieHOF.countMovies(sampleMovies, isFrom1975);
        Assertions.assertEquals(3, count);
        Assertions.assertNotEquals(1, count);
    }

    @Test
    public void testFindMaxMovie() {
        Comparator<Movie> runtimeComp = Comparator.comparingInt(Movie::runtime);
        Optional<Movie> maxMovie = MovieHOF.findMax(sampleMovies, runtimeComp);

        Assertions.assertTrue(maxMovie.isPresent());
        Assertions.assertEquals("Dog Day Afternoon", maxMovie.get().title());
    }
}

