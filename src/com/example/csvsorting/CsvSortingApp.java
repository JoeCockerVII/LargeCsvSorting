package com.example.csvsorting;

import com.example.csvsorting.domain.service.CsvSorting;

import java.io.*;

public class CsvSortingApp {

    public static void main(String[] args) throws IOException {

        /**
         * Input datasets parameter
         */
//        String fileName = "InputDataFile.csv";
        String fileName = "InputDataFile1.csv";
//        String fileName = "InputDataFile2.csv";
//        String fileName = "InputDataFile3.csv";
//        String fileName = "InputDataFile4.csv";
        /**
         *  Sorting parameter
         *  true  - ascend; false - descend
         */
        boolean sortOrder = true;
//        boolean sortOrder = false;
        /**
         * Memory limitation modeling
         */
        int bufferSize = 10;

        CsvSorting csvSorting = new CsvSorting();
        csvSorting.csvFileSort(fileName, bufferSize, sortOrder);
    }
}
