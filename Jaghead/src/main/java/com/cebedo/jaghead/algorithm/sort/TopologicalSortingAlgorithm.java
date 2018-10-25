/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.sort;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.List;

/**
 * In the field of computer science, a topological sort or topological ordering
 * of a directed graph is a linear ordering of its vertices such that for every
 * directed edge uv from vertex u to vertex v, u comes before v in the ordering.
 * For instance, the vertices of the graph may represent tasks to be performed,
 * and the edges may represent constraints that one task must be performed
 * before another; in this application, a topological ordering is just a valid
 * sequence for the tasks. A topological ordering is possible if and only if the
 * graph has no directed cycles, that is, if it is a directed acyclic graph
 * (DAG). Any DAG has at least one topological ordering, and algorithms are
 * known for constructing a topological ordering of any DAG in linear time.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting">Wikipedia
 * (Topological Sorting)</a>
 * @author Vic Cebedo <cebedo.vii@gmail.com>
 * @param <T1> {@link Graph} or any subclass.
 * @param <T2> {@link Vertex} or any subclass.
 */
interface TopologicalSortingAlgorithm<T1 extends Graph, T2 extends Vertex> {

    /**
     * Returns a topologically sorted list of {@link Vertex}.
     *
     * @param graph Graph to be sorted topologically.
     * @return Topologically sorted vertices.
     */
    List<T2> sort(T1 graph);

}
