## Installing the jar files flatlaf-3.1.jar and xchart-3.8.2.jar
Included in the zip files are two jars. Flatlaf for style changes to our ui and xchart for displaying the histogram.

In order to install these for building the jar and references in code please follow the instructions below.

#### Installing jars for code references

Click File and then Project Structure and then go to modules. Press the + key, select jar and locate the jar's in the res folder. Once both have been added click apply.

#### Installing jars for building the main jar

Still in Project Structure click artifacts. Click the plus sign and select from modules with dependencies, select Grime as the main class and press Ok. Select the option "Include in project build and double click both flatlaf and xchart from the avaliable elements on the right so that they are located on the left. Click apply and then okay. Once you build the project it will include the dependency jars.

** Please be aware that these jar's will have to be removed from your zip submission. Use them to create the jar and then move them out of the project or even just the folders you will include in your submission.

--THIS INCLUDES THE SUBMISSION FOR RECIEVED CODE. It will go over the file limit if these are not removed.

## Navigating the UI

**File Menu**

There are two operations in the view: loading an image and saving an image.

Users can open .ppm, .png, .jpg, .bmp images using load, and save them using load.
A save path can be specified, but the default filename will be "img.png", saving in the working directory.

**Side Menu** 

Users can manipulate the image loaded using a variety of operations.
Combining an image from red, green, and blue images can be done by selecting a red, green, 
and blue file near the bottom of the menu and clicking the combine button.

A histogram of the color distribution can be seen at the bottom of the UI.
## Supported Commands in command line application

**load image-path image-name**: Load a .bmp, .ppm, .png, .jpg file from the specified path and refer it to henceforth in the program by the given image name.

 example: load folder/image.png image-name

**save image-path image-name**: Save a .bmp, .ppm, .png, .jpg file with the given name to the specified path which should include the name of the file.

example: save folder/image.jpg image-name

**greyscale component image-name dest-image-name**: Create a greyscale image using a specified component of an image: red-component, green-component, blue-component, value-component, luma-component, intensity-component.

example: greyscale luma-component name new-name

**greyscale image-name dest-image-name**: Create a greyscale image without specifying a component.

example: greyscale name new-name

**horizontal-flip image-name dest-image-name**: Flip an image horizontally to create a new image, referred to henceforth by the given destination name.

example: horizontal-flip name new-name

**vertical-flip image-name dest-image-name**: Flip an image vertically to create a new image, referred to henceforth by the given destination name.

example: vertical-flip name new-name

**brighten increment image-name dest-image-name**: brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening).

example: brighten 10 name new-name, brighten -30 name new-darkened-name

**rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue**: split the given image into three greyscale images containing its red, green and blue components respectively.

example: rgb-split name red-name green-name blue-name

**rgb-combine image-name red-image green-image blue-image**: Combine the three greyscale images into a single image that gets its red, green and blue components from the three images respectively.

example: rgb-combine new-image red green blue

**run script-file**: commands.Load and run the script commands in the specified file

example: run text-file.txt

**luma image-name dest-image-name**: Create a greyscale image based on the luma component of an image.
This is essentially shorthand for the greyscale command.

example: luma name new-name

**value image-name dest-image-name**: Create a greyscale image based on the value component of an image.
This is essentially shorthand for the greyscale command.

example: value name new-name

**intensity image-name dest-image-name**: Create a greyscale image based on the intensity component of an image.
This is essentially shorthand for the greyscale command.

example: intensity name new-name

**dither image-name dest-image-name**: Create a dithered image from the source image.

example: dither name new-name

**sharpen image-name dest-image-name**: Create a sharpened image from the source image.

example: sharpen name new-name

**blur image-name dest-image-name**: Create a blurred image from the source image.

example: blur name new-name

**sepia image-name dest-image-name**: Create a sepia-scaled image from the source image.

example: sepia name new-name
