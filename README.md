# roman_datamodels_java

## Running the tests

### Set up a Python environment

The test suite uses the Python [roman_datamodels](https://github.com/spacetelescope/roman_datamodels) package to generate
test data, so you'll first need to create a Python environment and install the necessaries.  The test-requirements.txt file
lists the dependencies needed.  Here's an example of creating an environment using conda:

```
$ conda create --name roman-datamodels-java python=3.13
...
$ conda activate roman-datamodels-java
$ pip install -r test-requirements.txt
...
```

### Set the ASDF_JAVA_TESTS_PYTHON_PATH variable

The ASDF_JAVA_TESTS_PYTHON_PATH environment variable tells the test suite where to find
the python binary for your new environment:

```
$ export ASDF_JAVA_TESTS_PYTHON_PATH=`which python`
```

### Run the tests

Use maven's test command to run the tests:

```
$ mvn test
...
```
