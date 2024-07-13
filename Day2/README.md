**Exercise 00 – File Signatures**
Input/output classes in Java are represented by a broad hierarchy. The key classes describing byte input/output behavior are abstract classes InputStream and OutputStream. They do not implement specific mechanisms for byte flows processing, rather delegate them to their subclasses, such as FileInputStream/FileOutputStream.
To understand the use of this functionality, you should implement an application for analyzing signatures of arbitrary files. This signature allows to define file content type and consists of a set of "magic numbers." These numbers are usually located in the beginning of the file.
You need to implement an application that accepts the signatures.txt as an input (you should describe it on your own; the file name is explicitly stated in the program code). It contains a list of file types and their respective signatures in the HEX format.
During execution, your program shall accept full paths to files on hard disk and keep the type which file signature corresponds to. The result of program execution should be written to result.txt file. If a signature cannot be defined, the execution result is UNDEFINED (no information should be written into the file).
Notes:
We can accurately determine the content type by analyzing the file signature, since the file extension contained in the name (e. g. image.jpg) can be changed by simply renaming the file.
The signatures file shall contain at least 10 different formats for analysis.

**Exercise 01 – Words**
In addition to classes designed to handle byte flows, Java has classes to simplify handling of character flows (char). These include abstract classes Reader/Writer, as well as their implementations (FileReader/FileWriter, etc.). Of special interest are BufferedReader/BufferedWriter classes which accelerate flow handling via buffering mechanisms.
Now you need to implement an application that will determine the level of similarity between texts. The simplest and most obvious method to do this is to analyze the frequency of occurrence of the same words.
Your goal is to implement an application that accepts two files as an input (both files are passed as command-line arguments) and displays their similarity comparison outcome (cosine measure).
The program shall also create dictionary.txt file containing a dictionary based on these files.

**Exercise 02 – File Manager**
Let's implement a utility handling the files. The application shall display information about the files, folder content and size, and provide moving/renaming functionality. In essence, the application emulates a command line of Unix-like systems.
The program shall accept as an argument the absolute path to the folder where we start to work, and support the following commands:
mv WHAT WHERE – enables to transfer or rename a file if WHERE contains a file name without a path. ls – displays the current folder contents (file and subfolder names and sizes in KB) cd FOLDER_NAME – changes the current directory
