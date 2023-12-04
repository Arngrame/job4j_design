package ru.job4j.chapter01.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        if (nodes != null && !nodes.isEmpty() && source != null && source.hasNext()) {
            int nodesCount = nodes.size();

            int i = 0;
            while (source.hasNext()) {
                int nodeIndex = i % nodesCount;
                nodes.get(nodeIndex).add(source.next());
                i++;
            }
        }
    }
}