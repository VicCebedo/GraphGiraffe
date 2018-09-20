/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead.algorithm.search;

import com.cebedo.jaghead.GenericVertex;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Vic
 * @param <T1>
 */
public class PathFinder<T1 extends GenericVertex> {

    private void findAllPaths() {
        // Create a queue which stores the paths.
        Queue<T1> toVisit = new LinkedList<>();

        // path vector to store the current path
//    vector<int> path;
//    path.push_back(src);
//    q.push(path);
//
//    while (!q.empty()) {
//        path = q.front();
//        q.pop();
//        int last = path[path.size() - 1];
// 
//        // if last vertex is the desired destination
//        // then print the path
//        if (last == dst) 
//            printpath(path);        
// 
//        // traverse to all the nodes connected to 
//        // current vertex and push new path to queue
//        for (int i = 0; i < g[last].size(); i++) {
//            if (isNotVisited(g[last][i], path)) {
//                vector<int> newpath(path);
//                newpath.push_back(g[last][i]);
//                q.push(newpath);
//            }
//        }
//    }
//}
    }

}
