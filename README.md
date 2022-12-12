# JavaFest

I'm going into this with no plan and am making it up one feature at a time. Let's see what each release brings us.

## Current Functionality

I like it when Madeline Kahn as Mrs. White in Clue says flames, so I made a jar that prints Madeline Kahn as Mrs. White
in Clue saying flames. I like to think that justifies its own release.

## Instructions

* Download most recent `javafest-1.x.x.jar` file from "Releases"
* Navigate to directory in command line and run `java -jar .\<FILE_NAME>`
* To specify an optional width, add `-w <WIDTH>`
* To specify an optional height, add `-h <HEIGHT>`
* To specify a custom picture (to say "flames"), add `-p <FILE_PATH>`
* To invert image colors, add `-i`
* To create a local copy of the image of the specified size, add `-c`
    * NOTE: Inversion does not currently work with the copy

## History

### 2022-12-10

* Started with a simple Spring Boot jar setup with simple MKAMWICSF functionality.

## Release Log

### v1.0.0

Initial release of Madeline Kahn as Mrs. White in Clue saying "flames."

### v1.1.0

Add custom sizing option

### v1.2.0

Add custom height option

### v1.3.0

Change how args are handled

### v1.4.0

Add custom picture option

#### v1.4.1

Expand default height to match PowerShell

### V1.5.0

Add ability to invert image

### V1.6.0

Add ability to copy