package com.carvaluation.utils;

public interface Constants {

    String BASE_URL = "https://www.cazoo.co.uk/value-my-car/";
    String INPUT_DIR = "src/test/resources/data/input/";
    String OUTPUT_LOCATION = "src/test/resources/data/output/car_output_v2.txt";

    String CAR_REG_PATTERN = "(?=.{7})(([a-zA-Z]?){2}(\\d){2}[ ]{0,1}([a-zA-Z]){3})";
    int TIMEOUT_PERIOD = 10;
}
