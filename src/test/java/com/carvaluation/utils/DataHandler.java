package com.carvaluation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataHandler {
    private static final Logger log = LogManager.getLogger(DataHandler.class);

    @DataProvider(name = "registrationNumberList")
    public static Object[][] inputDataHandler() throws IOException {
        final String directory = Constants.INPUT_DIR;
        List<String> regNumberList = new ArrayList<>();

        try (Stream<Path> stream = Files.list(Paths.get(directory))) {
            List<String> fileList = stream
                    .filter(file -> !Files.isDirectory(file))
                    .filter(file -> {
                        try {
                            return !Files.isHidden(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            for (String fileName : fileList) {
                regNumberList.addAll(inputFileReader(directory + fileName));
            }
        }
        return dataConverter(regNumberList);
    }

    private static List<String> inputFileReader(String filePath) {
        String inputLine;
        List<String> regNumberList = new ArrayList<>();
        Pattern pattern = Pattern.compile(Constants.CAR_REG_PATTERN);
        Matcher matcher;
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((inputLine = bufferedReader.readLine()) != null) {
                if (inputLine.length() != 0) {
                    matcher = pattern.matcher(inputLine);
                    while (matcher.find()) {
                        regNumberList.add(matcher.group(1).replaceAll("\\s", ""));
                    }
                }
            }
        } catch (Exception exception) {
            log.error("Error while reading input file :" + exception);
        }
        return regNumberList;
    }

    private static Object[][] dataConverter(List<String> regNumberList) {
        final int listLength = regNumberList.size();
        Object[][] regNumberArray = new Object[listLength][1];
        for (int i = 0; i < listLength; i++) {
            regNumberArray[i][0] = regNumberList.toArray()[i];
        }
        return regNumberArray;
    }

    public static Map<String, String> outputFileReader() {
        String outputLine;
        LinkedHashMap<String, String> outputMap = new LinkedHashMap<>();
        try {
            FileReader fileReader = new FileReader(Constants.OUTPUT_LOCATION);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((outputLine = bufferedReader.readLine()) != null) {
                if (outputLine.length() != 0) {
                    var splitLine = outputLine.split(",", 2);
                    outputMap.put(splitLine[0], splitLine[1].replaceFirst(",", " "));
                }
            }
        } catch (Exception exception) {
            log.error("Error while reading output file :" + exception);
        }
        return outputMap;
    }

}
