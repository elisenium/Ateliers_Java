package server;

import domaine.Query;
import domaine.QueryFactory;

import java.net.http.HttpClient;
import java.util.Scanner;

public class ProxyServer {
    QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private final HttpClient client = HttpClient.newHttpClient();

    public void startServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String url = scanner.nextLine();
                Query query = this.queryFactory.getQuery();
                query.setMethod(Query.QueryMethod.GET);
                query.setUrl(url);
                QueryHandler queryHandler = new QueryHandler(query);
                queryHandler.sendQueryAndPrintResponse();
            }
        }
    }
}
