package com.example.csvsorting.domain.service;

import com.example.csvsorting.domain.entity.ClientDetails;
import com.example.csvsorting.domain.exception.EntityNotFoundException;

import java.io.*;
import java.util.*;

/**
 * CSV file sorting class
 * @author ilyin
 * @since 11.08.2022
 */
public class CsvSorting {

    public int bufferSize;

    private List<ClientDetails> clientDetails = new LinkedList<ClientDetails>();
    private List<PartFileSorted> parts = new LinkedList<PartFileSorted>();
    private String headerLine;
    private ClientDetailsFidComparator fidCompare =  new ClientDetailsFidComparator();

    /**
     *  Main Csv Sorting method
     */
    public void csvFileSort(String fileName, int bufferSize, boolean sortOrder) throws IOException {
        this.bufferSize = bufferSize;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        headerLine = br.readLine();
        String currentLine;
        boolean isFileDivided = false;
        int bufferSizeCounter = 0;

        while((currentLine = br.readLine()) != null){

            String[] words = currentLine.split(";");
            Integer fid = Integer.parseInt(words[0]);
            String serialNum = words[1], memberCode  = words[2],acctType = words[3], openedDt = words[4];
            String acctRte   = words[5], reportingDt = words[6],creditLimit = words[7];

            ClientDetails clientDetails = new ClientDetails(fid, serialNum, memberCode,acctType,openedDt, acctRte, reportingDt, creditLimit);

            this.clientDetails.add(clientDetails);
            bufferSizeCounter++;

            if(this.clientDetails.size() >= bufferSize){
                isFileDivided = true;

                sortCurrentPart(this.clientDetails, fidCompare, sortOrder);
                parts.add(new PartFileSorted<>(this.clientDetails));
                this.clientDetails.clear();
            }
        }
        if(isFileDivided && !clientDetails.isEmpty()){
            sortCurrentPart(this.clientDetails, fidCompare, sortOrder);
            parts.add(new PartFileSorted<>(this.clientDetails));
            this.clientDetails.clear();
        }
        sortCurrentPart(this.clientDetails, fidCompare, sortOrder);
        partFileMerge(sortOrder);

    }

    /**
     *  Merge sorting part of original file
     */
    public void partFileMerge(boolean sortOrder) throws IOException {

        String outputFile = "OutputDataFile.csv";

        List<Iterator<ClientDetails>> detailsIterator = new ArrayList<>();
        List<ClientDetails> details = new ArrayList<>();

        for (PartFileSorted pfs:parts) {
            detailsIterator.add(pfs.iterator());
        }
        for (Iterator<ClientDetails> it:detailsIterator) {
            details.add(it.next());
        }

        ClientDetails value;
        int filesCounter = parts.size();
        int cnt = 2;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            bw.write(headerLine);
            bw.newLine();

            if (filesCounter > 1) {
                while (filesCounter != 0) {
//                    value = details.stream().min(fidCompare).get();
                    try {
                        if(sortOrder)
                            value = Optional.ofNullable(details.stream().min(fidCompare).get()).orElseThrow(() -> new EntityNotFoundException("Part of file is read"));
                        else
                            value = Optional.ofNullable(details.stream().max(fidCompare).get()).orElseThrow(() -> new EntityNotFoundException("Part of file is read"));

                        int index = details.indexOf(value);
                        if (detailsIterator.get(index).hasNext()) {
                            details.set(index, detailsIterator.get(index).next());
                        } else {
                            filesCounter--;
                            details.remove(value);
                            detailsIterator.remove(index);
                        }
                        /**
                         * Visualisation
                         */
                        System.out.println("line = " + cnt++ + "  " + value);

                        bw.write(value.getCsvString());
                        bw.newLine();
                        bw.flush();

                    } catch (EntityNotFoundException e) {
                        int testPoint = 0;
                    }

                }
            }else{

                for (ClientDetails c : clientDetails) {
                    /**
                     * Visualisation
                     */
                    System.out.println("line = " + cnt++ + "  " + c);
                    bw.write(c.getCsvString());
                    bw.newLine();
                    bw.flush();
                }
            }
        }


        File dir = new File(PartFileSorted.pathname);

        if(dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for(File file : listFiles){
                file.delete();
            }
        }
    }

    public void sortCurrentPart(List<ClientDetails> clientDetails,ClientDetailsFidComparator cmp, boolean sortOrder){
        if(sortOrder)
            Collections.sort(clientDetails,cmp);
//            clientDetails.sort(cmp);
        else
            Collections.sort(clientDetails,Collections.reverseOrder(cmp));
    }


    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}


//                try {
//                    minValue = Optional.ofNullable(details.stream().min(fidCompare).get()).orElseThrow(() -> new EntityNotFoundException("File is read"));
//                } catch (EntityNotFoundException) {
//                    int testPoint = 0;
//                }
