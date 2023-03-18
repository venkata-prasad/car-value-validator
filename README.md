# CAR DESCRIPTION VALIDATION TESTS

### Description
This is a Data driven test framework to validate the car description available in a file against the cazoo website. The UK reg car numbers present in the input file is searched in the cazoo website and the description is verified if present in the output file.

### Test Execution

To run the test, use the following command from the project root folder.
```command
 mvn clean test
```
if maven is not installed on the machine please try the below commands.

For Linux
```Linux
./mvnw clean test
```
For windows
```Linux
mvnw.cmd clean test
```
Testing can be done using either chrome or firefox(for now). This can be selected in the testng.xml file
```browser
<parameter name="browserName" value="chrome" />
 or
<parameter name="browserName" value="firefox" />
```

### Configuration
The following configuration is required for running the tests successfully. This is present in the utils.Constants class.
Any file present in the INPUT_DIR location will be used for the test allowing multiple files if required.
```config
BASE_URL = "https://www.cazoo.co.uk/value-my-car/";
INPUT_DIR = "src/test/resources/data/input/";
OUTPUT_LOCATION = "src/test/resources/data/output/car_output_v2.txt"
```

### Reports
The reports are generated using Extent reports and available in the test-output folder. In case of failure, the browser screenshot is available in the report.
