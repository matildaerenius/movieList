import org.bson.Document;
import java.util.List;

public record Movie(
        String id,
        String title,
        int year,
        int runtime,
        List<String> genres,
        String director,
        List<String> cast,
        double imdbRating,
        List<String> languages
) {
    public static Movie fromDocument(Document doc) {
        return new Movie(
                doc.getObjectId("_id").toString(),
                doc.getString("title"),
                doc.getInteger("year", 0),
                doc.getInteger("runtime", 0),
                doc.getList("genres", String.class),
                doc.getString("director"),
                doc.getList("cast", String.class),
                doc.get("imdb", Document.class) != null ? doc.get("imdb", Document.class).getDouble("rating") : 0.0,
                doc.getList("languages", String.class)
        );
    }
}


