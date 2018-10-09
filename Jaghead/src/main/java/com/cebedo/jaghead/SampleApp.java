/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cebedo.jaghead;

import com.cebedo.jaghead.core.Graph;
import com.cebedo.jaghead.core.GraphImpl;
import com.cebedo.jaghead.core.JSONDataImporter;
import com.cebedo.jaghead.core.DataImporter;

/**
 *
 * @author Vic
 */
public class SampleApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph = getSampleGraphJson();
    }

    private static Graph getSampleGraphJson() {
        String j = "{\n"
                + "    \"vertices\": \n"
                + "    [\n"
                + "        { \"id\": \"A\" },\n"
                + "        { \"id\": \"B\" },\n"
                + "        { \"id\": \"C\" },\n"
                + "        { \"id\": \"D\" },\n"
                + "        { \"id\": \"E\" },\n"
                + "        { \"id\": \"F\" },\n"
                + "        { \"id\": \"G\" },\n"
                + "        { \"id\": \"H\" }\n"
                + "    ],\n"
                + "    \"edges\": \n"
                + "    [\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"B\",\n"
                + "            \"weight\": 5\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"C\",\n"
                + "            \"weight\": 4\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"D\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"A\",\n"
                + "            \"target\": \"E\",\n"
                + "            \"weight\": 1\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"B\",\n"
                + "            \"target\": \"F\",\n"
                + "            \"weight\": 2\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"C\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"D\",\n"
                + "            \"target\": \"G\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"E\",\n"
                + "            \"target\": \"G\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"G\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"D\",\n"
                + "            \"target\": \"F\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"D\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        },\n"
                + "        { \n"
                + "            \"source\": \"F\",\n"
                + "            \"target\": \"H\",\n"
                + "            \"weight\": 3\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        DataImporter importer = new JSONDataImporter.Builder(j).build();
        Graph graph = new GraphImpl.Builder(
                importer.getVertices(),
                importer.getEdges())
                .build();
        return graph;
    }

}
