# JavaFest

I'm going into this with no plan and am making it up one feature at a time. Let's see what each release brings us.

---

## Current Functionality

Currently, this is a rudimentary image editor. You can either choose to print an ASCII code copy of an image, or
generate a new image to `output.png`

## Instructions

* Download most recent `javafest-1.x.x.jar` file from "Releases"
* Navigate to directory in command line and run `java -jar .\<NAME OF JAR> <EDITOR TYPE> <OPTIONAL FLAGS>`

### Editor Types

* `ascii`
* `copy`

### Optional Flags

| Flag                    | Description                                      |
| ----------------------- | ------------------------------------------------ |
| `-w <NUMBER OF PIXELS>` | The width of the image to be produced in pixels  |
| `-h <NUMBER OF PIXELS>` | The height of the image to be produced in pixels |
| `-p <FILE PATH>`        | The image to edit                                |
| `-i`                    | Invert the image's color                         |
| `-f`                    | Include flames footer (only applies to ASCII)    |

---

## Major Releases

### v1

A jar that prints a picture of Madeline Kahn as Mrs. White in Clue saying "flames", pluse a few other tricks

### v2

A jar that accepts an image and provides a few options to edit it

## History

### 2022-12-10

* Started with a simple Spring Boot jar setup with simple MKAMWICSF functionality.

### 2022-12-17

* Decided to steer this towards image editing software for now