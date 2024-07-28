package extras;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DBHandlerMongo {

    public static String[][] retrieveHighscores(int size, String difficulty) {
        String[][] namedScores = new String[2][size];

        Collection<Object> c;

        try {
            MongoClient client = MongoClients.create("mongodb+srv://syedhuzaif199:MongoMongo123@huzaifscluster.rcqlzzv.mongodb.net/?retryWrites=true&w=majority");

            try {
                MongoDatabase db = client.getDatabase("snakedb");
                MongoCollection<Document> collection = db.getCollection("playerhighscores_" + difficulty);
                List<Document> results = new ArrayList<>();
                collection.find().sort(Sorts.descending("Score")).into(results);
                int i = 0;

                for(Iterator<Document> iterator = results.iterator(); iterator.hasNext(); ++i) {
                    Document result = iterator.next();
                    c = result.values();
                    Iterator<Object> itr = c.iterator();

                    for(int j = 0; itr.hasNext() && i < size; ++j) {
                        Object ret = itr.next();
                        if (j == 1) {
                            namedScores[0][i] = (String)ret;
                        }

                        if (j == 2) {
                            namedScores[1][i] = String.valueOf(ret);
                        }
                    }
                }
            } catch (Throwable throwable) {
                try {
                    client.close();
                } catch (Throwable var14) {
                    throwable.addSuppressed(var14);
                }

                throw throwable;
            }

            client.close();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return namedScores;
    }

    public static void uploadScore(String name, int score, String difficulty) {
        try {
            MongoClient client = MongoClients.create("mongodb+srv://syedhuzaif199:MongoMongo123@huzaifscluster.rcqlzzv.mongodb.net/?retryWrites=true&w=majority");

            try {
                MongoDatabase db = client.getDatabase("snakedb");
                MongoCollection<Document> collection = db.getCollection("playerhighscores_" + difficulty);
                Document doc = (new Document("Name", name)).append("Score", score);
                collection.insertOne(doc);
            } catch (Throwable throwable) {
                try {
                    client.close();
                } catch (Throwable t) {
                    throwable.addSuppressed(t);
                }

                throw throwable;
            }

            client.close();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}
