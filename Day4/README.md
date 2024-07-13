**Exercise 00 – Packages**
Code can be organized on different levels. Packages are one of the code organization methods where classes are located in individual folders.
Now your task is to implement functionality that prints a two-colored image in the console.
An example of a black-and-white BMP image (this format is mandatory for the solution). Image size is 16*16 pixels.
Your application shall accept input parameters corresponding to characters that should be displayed in place of white and black pixels. Another main function startup parameter is the full path to the image on your disk.
If "." character is used for white color and "0" for black
README.txt file must contain instructions for compiling and starting your source code from the console (non-IDE). Instruction is written for the state where the console is opened in the project's root folder.

**Exercise 01 – First JAR**
Now you need to create a distribution package of the application—a JAR archive. It is important that the image be contained in that archive (a command-line parameter for the full path to the file is not required in this task).
Archive and all compiled files shall be put in target folder during assembly (without a manual file transfer; you may apply cp command to the resource folder).
README.txt file should also contain information on the archive assembly and startup.

**Exercise 02 – JCommander & JCDP**
Now you should use external libraries:
JCommander for the command line.
JCDP or JColor for using colored output
Archives with these libraries shall be downloaded and included in the previous task's project.
Now application startup parameters shall be processed with JCommander tools. The image should be displayed using the "colored" output option of JCDP library.
README.txt file shall also contain the information about including external libraries in the final assembly.


