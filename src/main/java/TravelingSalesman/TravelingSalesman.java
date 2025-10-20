package TravelingSalesman;

public class TravelingSalesman {

    private static final int INF = Integer.MAX_VALUE;
    private static int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };

    private static int numCities = graph.length;

    private static boolean[] visited;
    private static int minCost = INF;

    public static void main (String[] args){
        visited = new boolean[numCities];
        visited[0] = true;
        tsp(0,1,0);
        System.out.println("Minimum TSP cost: " +minCost);
    }

    private static void tsp(int currentCity, int count, int cost) {
        if(count  == numCities) {
            if(graph[currentCity][0]!= 0) {
                minCost = Math.min(minCost, cost + graph[currentCity][0]);
            }//inner if
            return;
        }//outer if
        for(int nextCity = 0; nextCity < numCities; nextCity++) {
            if(!visited[nextCity] && graph[currentCity][nextCity] != 0) {
                visited[nextCity] = true;
                tsp(nextCity, count + 1, cost + graph[currentCity][nextCity]);
                visited[nextCity] = false;
            }
        }
    }
}
