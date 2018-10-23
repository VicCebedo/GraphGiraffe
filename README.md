# JA(va)G(raph)HEAD
> A Java graph theory library for data modeling and problem solving.

## Table of Contents
- [Getting Started](#getting-started)
- [Basics](#basics)
- [Data](#data)
    - [Import](#import)
    - [Export](#export)
- [Algorithms](#algorithms)
    - [Minimum Spanning Trees](#minimum-spanning-trees)
    - [Search](#search)
        - [Breadth-First](#breadth-first)
        - [Depth-First](#depth-first)
        - [Connectivity](#connectivity)
        - [Path Distance](#path-distance)
        - [Path Finder](#path-finder)
    - [Shortest Path](#shortest-path)
    - [Topological Sorting](#topological-sorting)
- [Samples](#samples)
- [Known Issues](#known-issues)


## Getting Started
> You may download the latest code at [Github](https://github.com/VicCebedo/Jaghead/releases).
## Basics
> The implementations `VertexImpl`, `EdgeImpl` and `GraphImpl` are immutable. If an object is immutable, its fields cannot be altered after the instance of the object has been created.

- Creating a new `Vertex` instance with `"vertex_id_here"` as `id`:
    ```java
    Vertex vertex = new VertexImpl.Builder("vertex_id_here").build();
    ```
- Creating a new `Edge` instance with `"edge_id_here"` as `id`, `Vertex` `sourceVertx` as the source, `Vertex` `targetVertx` as the target, with `weight` `55`:
    ```java
    Edge edge = new EdgeImpl.Builder<>("edge_id_here", sourceVertx, targetVertx)
                    .withWeight(55)
                    .build();
    ```
- Creating a new `Graph` instance with empty sets of `vertices` and `edges`:
    ```java
    Set<Vertex> vertices = new HashSet<>();
    Set<Edge> edges = new HashSet<>();
    Graph graph = new GraphImpl.Builder(vertices, edges).build();
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
    Graph mst = JagheadMinimumSpanningTrees.PRIM.mst(graph);

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
            Set<Vertex> results = JagheadSearch.BreadthFirst.EDGE.search(graph, "A", 
                (CheckerEdge) (Edge edge) -> {
                    return edge.getWeight().doubleValue() > 35;
            });
            System.out.println(results);
            ```
        - Check vertices:
            ```java
            // Start walking from vertex "A".
            // Loop through all vertices of the graph and
            // collect all vertices if a vertex has more than 5 outgoing incident edges.
            Set<Vertex> results = JagheadSearch.BreadthFirst.VERTEX.search(graph, "A", 
                (CheckerVertex) (Vertex vertx) -> {
                    return graph.incidentEdgesOutgoing(vertx).size() > 5;
            });
            System.out.println(results);
            ```
    - ### Depth First
        - Check edges:
            ```java
            // Start walking from vertex "A".
            // Loop through all edges of the graph and
            // collect all vertices where its incident edge weight is greater than 35.
            Set<Vertex> results = JagheadSearch.DepthFirst.EDGE.search(graph, "A", 
                (CheckerEdge) (Edge edge) -> {
                    return edge.getWeight().doubleValue() > 35;
            });
            System.out.println(results);
            ```
        - Check vertices:
            ```java
            // Start walking from vertex "A".
            // Loop through all vertices of the graph and
            // collect all vertices if a vertex has more than 5 outgoing incident edges.
            Set<Vertex> results = JagheadSearch.DepthFirst.VERTEX.search(graph, "A", 
                (CheckerVertex) (Vertex vertx) -> {
                    return graph.incidentEdgesOutgoing(vertx).size() > 5;
            });
            System.out.println(results);
            ```
    - ### Connectivity
        ```java
        // Checks whether a graph is connected or not.
        boolean isConnected = JagheadConnectivity.BREADTH_FIRST.connected(graph);
        System.out.println(isConnected);
        ```
    - ### Path Distance
        ```java
        // Get a path where total distance is more than 60,
        // start walking from vertex "A".
        Map<String, ?> path = JagheadPathDistance.BACKTRACK.findPath(graph, "A", 60);
        System.out.println(path);
        ```
    - ### Path Finder
        ```java
        // Get a list of paths (linked list of vertices)
        // from vertex "A" to "H".
        List<List<Vertex>> paths = JagheadPathFinder.BACKTRACK.findPaths(graph, "A", "H");
    
        // Print each path.
        paths.forEach(path -> {
            System.out.println(path);
        });
        ```
- ### Shortest Path
    ```java
    // Gets the shortest path from vertex "A"
    // to all the other vertices in the graph.
    Map<Vertex, Number> distMap = JagheadShortestPath.DIJKSTRA.shortestPath(graph, "A");
    System.out.println(distMap);
    ```
- ### Topological Sorting
    ```java
    // Topologically sort the graph.
    List<Vertex> topologicalSorting = JagheadTopologicalSorting.KAHN.sort(graph);
    System.out.println(topologicalSorting);
    ```
## Samples
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
## Known Issues
- `JagheadPathFinder.BACKTRACK`: Improve presentation of results, duplicate nodes.
- `JagheadPathDistance.BACKTRACK`: Only checks one path. Should have same algorithm with `JagheadPathFinder.BACKTRACK`.
- `JagheadConnectivity.BREADTH_FIRST`: Add a warning to all traversing algorithms if graph is not connected.
- `GraphImpl`: Cache heavy functions.
- Missing documentation on some classes.
- Upload project to Maven.
