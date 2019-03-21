# JA(va)G(raph)HEAD
> A Java graph theory library for data modeling and problem solving.

## Table of Contents
- [Getting Started](#getting-started)
- [Basics](#basics)
- [Data](#data)
    - [Import](#import)
    - [Visualization](#visualization)
- [Algorithms](#algorithms)
    - [Minimum Spanning Trees](#minimum-spanning-trees)
    - [Search](#search)
        - [Breadth-First](#breadth-first)
        - [Depth-First](#depth-first)
        - [Connectivity](#connectivity)
        - [Path Finder](#path-finder)
    - [Shortest Path](#shortest-path)
    - [Topological Sorting](#topological-sorting)
    - [Minimum Cut](#minimum-cut)
- [Samples](#samples)

## Getting Started
> You may download the latest code at [Github](https://github.com/VicCebedo/Jaghead/releases).

## Basics
- Creating `Vertex` `A`, `B` and `C`.
    ```java
    Set<Vertex> vertices = new HashSet<>();
    Vertex vertexA = new VertexBuilder("A").build();
    Vertex vertexB = new VertexBuilder("B").build();
    Vertex vertexC = new VertexBuilder("C").build();
    vertices.add(vertexA);
    vertices.add(vertexB);
    vertices.add(vertexC);
    ```
- Creating `Edge` `Hello` and `World`, and assigning each a source and target `Vertex`.
    ```java
    Set<Edge> edges = new HashSet<>();

    // Edge "Hello" is connected by two vertices:
    // Vertex "A" is the source, and vertex "B" is the target,
    // with a weight of 20 units.
    Edge edgeHello = new EdgeBuilder<>("Hello", vertexA, vertexB, 20)
            .build();

    // For edge "World", "B" is the source and "C" is the target,
    // with 15 units of weight.
    Edge edgeWorld = new EdgeBuilder<>("World", vertexB, vertexC, 15)
            .build();

    edges.add(edgeHello);
    edges.add(edgeWorld);
    ```
- Creating a `Graph` that contains the data we have just created.
    ```java
    Graph graph = new GraphBuilder(vertices, edges)
            .build();
    ```
## Data
- ### Import
    - Create a new instance of `Graph` from JSON data:
        ```java
        // Build a data importer,
        // then import results to the graph builder.
        DataImporter importer = new DataJSONImporter.Builder(json).build();
        Graph graph = new GraphImpl.Builder(
                            importer.getVertices(),
                            importer.getEdges())
                        .build();
        ```
    - Sample JSON is available at the [Samples](#samples) section.
- ### Visualization
    - Convert `Graph` to Cytoscape JSON:
        ```java
        // Given a graph, print a JSON that can be consumed by
        // Cytoscape to visualize the graph.
        DataCytoscapeExporter.newInstance().export(graph);
        ```
    - Sample output of `DataCytoscapeExporter.newInstance().export(graph);`:
        ```javascript
        [{
            "data": {
                "id": "A"
            }
        }, {
            "data": {
                "id": "B"
            }
        }, {
            "data": {
                "id": "D_G",
                "source": "D",
                "target": "G",
                "weight": 3
            }
        }]
        ```
    - Viewing the visualization:
        1. Open the root directory.
        1. Open `visualization.html`.
        1. Edit the `elements` property:
            ```javascript
            var cy = cytoscape({
                container: document.getElementById('cy'),
                elements:  [], // Replace this empty array with the Cytoscape JSON.
                style: [...],
                layout: { name: 'random' }
            });
            ```
## Algorithms
- ### Minimum Spanning Trees
    ```java
    // Print the graph before simplifying it to MST.
    // You can use the cytoscape output to visualize the graph.
    System.out.println("Before:");
    DataCytoscapeExporter.newInstance().export(graph);

    // Do the MST operation.
    Graph mst = JagheadMst.PRIM.mst(graph);

    // Print after MST simplification.
    System.out.println("After:");
    DataCytoscapeExporter.newInstance().export(mst);
    ```
- ### Search
    - ### Breadth First
        - Check edges:
            ```java
            // Start walking from vertex "A".
            // Loop through all edges of the graph and
            // collect all vertices where its incident edge weight is greater than 35.
            Set<Vertex> results = JagheadSearch.BreadthFirst.EDGE.search(graph, "A", (CheckerEdge) (Edge t1) -> {
                return t1.weight().doubleValue() > 35;
            });
            System.out.println(results);
            ```
        - Check vertices:
            ```java
            // Start walking from vertex "A".
            // Loop through all vertices of the graph and
            // collect all vertices if a vertex has more than 5 outgoing incident edges.
            Set<Vertex> results = JagheadSearch.BreadthFirst.VERTEX.search(graph, "A", (CheckerVertex) (Vertex t1) -> {
                return graph.incidentOutEdges(t1).size() > 5;
            });
            System.out.println(results);
            ```
    - ### Depth First
        - Check edges:
            ```java
            // Start walking from vertex "A".
            // Loop through all edges of the graph and
            // collect the vertex where the ID is "H".
            Set<Vertex> results = JagheadSearch.DepthFirst.EDGE.search(graph, "A", (CheckerEdge) (Edge t1) -> {
                return t1.target().id().equalsIgnoreCase("H");
            });
            System.out.println(results);
            ```
        - Check vertices:
            ```java
            // Start walking from vertex "A".
            // Loop through all vertices of the graph and
            // collect the vertex where the ID is "H".
            Set<Vertex> results = JagheadSearch.DepthFirst.VERTEX.search(graph, "A", (CheckerVertex) (Vertex t1) -> {
                return t1.id().equalsIgnoreCase("H");
            });
            System.out.println(results);
            ```
    - ### Connectivity
        ```java
        // Checks whether a graph is connected or not.
        boolean isConnected = JagheadConnectivity.BREADTH_FIRST.connected(graph);
        System.out.println(isConnected);
        ```
    - ### Path Finder
        ```java
        // Get a list of paths (linked list of vertices) from vertex "A" to "H".
        Set<List<Vertex>> paths = JagheadPathFinder.BACKTRACK.findPaths(graph, "A", "H");

        // Print each path.
        for (List<Vertex> path : paths) {
            System.out.println(path);
        }
        ```
- ### Shortest Path
    ```java
    // Gets the shortest path from vertex "A" to all the other vertices in the graph.
    Map<Vertex, Number> distMap = JagheadShortestPath.DIJKSTRA.shortestPath(graph, "A");
    System.out.println(distMap);
    ```
- ### Topological Sorting
    ```java
    // Topologically sort the graph.
    List<Vertex> topologicalSorting = JagheadTopologicalSorting.KAHN.sort(graph);
    System.out.println(topologicalSorting);
    ```

- ### Minimum Cut
    ```java
    // Gets the minimum cut of the given graph.
    Graph minCut = JagheadMinCut.KARGER.minCut(graph);
    System.out.println(minCut);
    ```
## Samples
- Sample code can be viewed in `com.cebedo.sample.SampleApp.java`.
- JSON string that can be imported to create a new instance of `Graph`.
     ```java
     String json =
          "{"
        + "    'vertices': "
        + "    ["
        + "        { 'id': 'A' },"
        + "        { 'id': 'B' },"
        + "        { 'id': 'C' },"
        + "        { 'id': 'D' },"
        + "        { 'id': 'E' },"
        + "        { 'id': 'F' },"
        + "        { 'id': 'G' },"
        + "        { 'id': 'H' }"
        + "    ],"
        + "    'edges': "
        + "    ["
        + "        { "
        + "            'source': 'A',"
        + "            'target': 'B',"
        + "            'weight': 5"
        + "        },"
        + "        { "
        + "            'source': 'A',"
        + "            'target': 'C',"
        + "            'weight': 4"
        + "        },"
        + "        { "
        + "            'source': 'A',"
        + "            'target': 'D',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'A',"
        + "            'target': 'E',"
        + "            'weight': 1"
        + "        },"
        + "        { "
        + "            'source': 'B',"
        + "            'target': 'F',"
        + "            'weight': 2"
        + "        },"
        + "        { "
        + "            'source': 'C',"
        + "            'target': 'H',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'D',"
        + "            'target': 'G',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'E',"
        + "            'target': 'G',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'G',"
        + "            'target': 'H',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'D',"
        + "            'target': 'F',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'D',"
        + "            'target': 'H',"
        + "            'weight': 3"
        + "        },"
        + "        { "
        + "            'source': 'F',"
        + "            'target': 'H',"
        + "            'weight': 3"
        + "        }"
        + "    ]"
        + "}";
     ```

