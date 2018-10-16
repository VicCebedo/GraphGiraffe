/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.Edge;
import com.cebedo.jaghead.algorithm.search.checker.Checker;
import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.algorithm.search.checker.EdgeChecker;
import com.cebedo.jaghead.algorithm.search.checker.VertexChecker;
import java.util.Set;

/**
 * Search algorithm is any algorithm which solves the search problem, namely, to
 * retrieve information stored within some data structure, or calculated in the
 * search space of a problem domain.
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Search_algorithm">Wikipedia (Search
 * Algorithm)</a>
 * @author Vic Cebedo
 * @param <T1> {@link Graph} or any subclass.
 * @param <T2> {@link Vertex} or {@link Edge} or any subclass.
 * @param <T3> {@link Checker} or any subclass. Function parameter that will be
 * run, parameterized with the edge or vertex of the graph.
 */
public interface SearchAlgorithm<T1 extends Graph, T2, T3 extends Checker> {

    /**
     * Returns a set of {@link Vertex} or {@link Edge} where the parameter
     * <code>checker</code> returned true.
     *
     * @param graph Graph that will be traversed by the algorithm.
     * @param sourceId ID of the vertex that will be the starting point of the
     * traversal.
     * @param checker The <code>check()</code> function can be used to check
     * each vertex or edge for a match.
     * @return Set of vertices or edges that matched the
     * {@link EdgeChecker#check(com.cebedo.jaghead.Edge)} or
     * {@link VertexChecker#check(com.cebedo.jaghead.Vertex)}.
     */
    Set<T2> search(T1 graph, String sourceId, T3 checker);
}
