package bfs_example;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class BreadthFirstSearch<T> {

    private final Vertex<T> startVertex;

    public void traverse() {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            if (!current.isVisited()) {
                current.setVisited(true);
                System.out.println(current);
                queue.addAll(current.getNeighbors());
            }
        }
    }
}
