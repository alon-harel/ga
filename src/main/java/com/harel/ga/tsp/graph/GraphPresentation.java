package com.harel.ga.tsp.graph;

import com.harel.ga.Chromosome;
import com.harel.ga.tsp.City;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;
import java.util.UUID;

public class GraphPresentation {
    public static void main(String args[]) throws InterruptedException {
        Graph graph = new SingleGraph("Tutorial 1");

        Node a = graph.addNode("A");
        a.setAttribute("xy", 0, 100);
        a.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
        Node b = graph.addNode("B");
        b.setAttribute("xy", 200, 0);
        Node c = graph.addNode("C");
        c.setAttribute("xy", 0, 0);
        graph.addEdge("AB", "A", "B").addAttribute("ui.style", "fill-color: rgb(0,100,255);");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

        //  graph.

        graph.display();
    }

    public void buildGraph(Chromosome chromosome, String rgbColor) {
        Graph graph = new SingleGraph(UUID.randomUUID().toString());

        City currentCity = (City)chromosome.getGenes().get(0);
        Node currentNode = graph.addNode(Integer.toString(0));
        currentNode.addAttribute("layout.frozen");
        currentNode.addAttribute("ui.style", String.format("fill-color: %s;", rgbColor));

        currentNode.setAttribute("xy", currentCity.getXCoordinate(), currentCity.getYCoordinate());

        for (int index = 1; index < chromosome.getGenes().size(); index++) {
            City nextCity = (City)chromosome.getGenes().get(index);
            Node nextNode = graph.addNode(Integer.toString(index));
            nextNode.addAttribute("layout.frozen");
            nextNode.addAttribute("ui.style", String.format("fill-color: %s;", rgbColor));


            nextNode.setAttribute("xy", nextCity.getXCoordinate(), nextCity.getYCoordinate());

            graph.addEdge(Integer.toString(index), currentNode.getId(), nextNode.getId());
            currentNode = nextNode;
        }

        graph.display();
    }
}
