import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MongoDBConnector {

    public MongoDBConnector() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uri = prop.getProperty("mongodb.uri");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

//            // Skriver ut alla filmer
//            for (Movie movie : movieList) {
//                System.out.println(movie);
//            }

            System.out.println("Antal filmer från år 1975: "
                    + MovieFunctions.countMoviesFrom1975(movieList)
                    + " st");

            System.out.println("Längsta film runtime: "
                    + MovieFunctions.longestMovieRuntime(movieList)
                    + " min");

            System.out.println("Unika genrer från år 1975: "
                    + MovieFunctions.uniqueGenresFrom1975(movieList)
                    + " st");

            System.out.println("Skådisar i högsta IMDb-ratede filmen: "
                    + String.join(", ", MovieFunctions.castOfHighestRatedMovie(movieList)));

            System.out.println("Filmtitel med minst antal skådisar: "
                    + MovieFunctions.titleOfLeastAmountOfCast(movieList));

            System.out.println("Antalet skådisar medverkade i mer än en film: "
                    + MovieFunctions.actorsInMoreThanOneMovie(movieList)
                    + " st");

            System.out.println("Skådis medverkande i flest filmer: "
                    + MovieFunctions.mostFeaturedActor(movieList));

            System.out.println("Unika språk i samtliga filmer: "
                    + MovieFunctions.uniqueLanguages(movieList)
                    + " st");

            System.out.println("Finns det några dubblettitlar? " +
                    MovieFunctions.isAnyTitleDuplicated(movieList));


            // Använder hof funk istället för att visa att dom fungerar, visas ju i test också dock

            System.out.println("\nMovieHOF - filmer från 1975: " +
            MovieHOF.countMoviesFrom1975HOF(movieList));

            System.out.println("MovieHOF - skådisar i högsta ratade filmen: " +
                    MovieHOF.castOfHighestRatedMovieHOF(movieList));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MongoDBConnector m = new MongoDBConnector();
    }
}
