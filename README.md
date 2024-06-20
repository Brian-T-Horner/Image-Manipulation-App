# Image Manipulation Application

This is an image manipulation application written for Programming Design and Paradigms (CS5010) in collaboration with Ted Banken.

There are three main packages: the model, control, and view package.

Model contains all the logic and operations for loading, 
manipulating images, and saving images.

Image contains the data structure of a single image.

The model package contains the interfaces Model and Image and the
classes AbstractImage and ImageModel.

The controller has changed in this iteration of the project. The FileController has been dissolved
since its only difference from the ImageController was the view, which has now been implemented.
The AbstractController class has been changed so that it only provides a few common functions to 
each controller. The controller used for command line input and file input is now called
TextController, and the one that handles a UI is the UIController. These changes were necessary because the view
was extremely coupled to the controller.

Views have now been implemented. The TextView and FileView are similar, only prompting the user in
the command line if necessary. The more involved view JFrameView, handles user input to the GUI.

The command package still exists for use with the TextController.

ImageUtil is a static class that deals with loading and saving images.
BufferedImage and ImageIO are convenient JDK classes to do this. 
PPM files are read directly from the file, but all other file types supported
are read and written to a BufferedImage, which acts as an intermediary between
loading and saving our Image objects.

## Usage

This program can be used in multiple ways. By using "-text" as a command line argument,
the program can be used at the command line, which prompts the user with a "$" symbol which is used
as feedback to let the user know when the program is ready for another
command. A script can be run by typing "run script-file.txt" into
the command line once the program is run. 
Alternatively, the script can be run by adding "-file script.txt"
as a command line argument to this program. Comments inside scripts are
supported using the '#' character. If no command line arguments are used,
the program will provide the user with a UI to manipulate one image at a time.

The supported image types that can be loaded and saved are .ppm, .png
.bmp, and .jpg.

