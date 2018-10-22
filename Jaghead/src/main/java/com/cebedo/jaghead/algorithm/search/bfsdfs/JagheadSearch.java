/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search.bfsdfs;

import com.cebedo.jaghead.Graph;
import com.cebedo.jaghead.Vertex;
import java.util.Set;

/**
 *
 * @author Vic
 */
public class JagheadSearch {

    public static enum BreadthFirst {
        EDGE {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return BFSEdge.newInstance().search(graph, sourceId, checker);
            }
        },
        VERTEX {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return BFSVertex.newInstance().search(graph, sourceId, checker);
            }
        };

        abstract Set<Vertex> run(Graph graph, String sourceId, Checker checker);

        public <T extends Checker> Set<Vertex> search(Graph graph, String sourceId, T checker) {
            return this.run(graph, sourceId, checker);
        }
    }

    public static enum DepthFirst {
        EDGE {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return DFSEdge.newInstance().search(graph, sourceId, checker);
            }
        },
        VERTEX {
            @Override
            Set<Vertex> run(Graph graph, String sourceId, Checker checker) {
                return DFSVertex.newInstance().search(graph, sourceId, checker);
            }
        };

        abstract Set<Vertex> run(Graph graph, String sourceId, Checker checker);

        public <T extends Checker> Set<Vertex> search(Graph graph, String sourceId, T checker) {
            return this.run(graph, sourceId, checker);
        }
    }
}
