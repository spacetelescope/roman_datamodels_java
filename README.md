# roman_datamodels_java

## API documentation

API documentation for this library is hosted on [javadoc.io](https://javadoc.io/doc/edu.stsci/roman-datamodels/latest/index.html).

## Getting started

### Installation

The roman-datamodels package is published on [Maven Central](https://mvnrepository.com/artifact/edu.stsci/roman-datamodels).  Include
the package in your pom.xml like so:

```
<dependency>
    <groupId>edu.stsci</groupId>
    <artifactId>roman-datamodels</artifactId>
    <version>x.y.z</version>
</dependency>
```

### Usage

The [RomanDatamodels#open](https://javadoc.io/doc/edu.stsci/roman-datamodels/latest/edu/stsci/roman/datamodels/RomanDatamodels.html#open(java.nio.file.Path))
method is used to read a Roman ASDF file and wrap it in a datamodel instance:

```java
final Path path = Path.of("/path/to/roman_file.asdf");
try (final RomanModel<?> model = RomanDatamodels.open(path)) {
    // ...
}
```

Next determine what type of datamodel you're dealing with.  To test for an image:

```java
if (model instanceof RomanImageModel) {
    final RomanImageModel<?> imageModel = (RomanImageModel<?>) model;
}
```

To access the image data, call the [RomanImageModel#getData](https://javadoc.io/doc/edu.stsci/roman-datamodels/latest/edu/stsci/roman/datamodels/model/RomanImageModel.html#getData())
method:

```java
final double[][] imageData = imageModel.getData().toArray(new double[0][0]);
final double[] flattenedImageData = imageModel.getData().toArray(new double[0]);
```

FITS WCS parameters are available from the [FitsWcs](https://javadoc.io/doc/edu.stsci/roman-datamodels/latest/edu/stsci/roman/datamodels/metadata/FitsWcs.html)
instance in the metadata:

```java
final FitsWcs fitsWcs = imageModel.getMeta().getFitsWcs();
```

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
