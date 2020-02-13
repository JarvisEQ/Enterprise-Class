 # What's inside
 
 ### config.txt
 
 This is the config file used to test the simulation. It was the same one that was provided in the project PDF
 
 ### sampleOutput.txt
 
 This is the output from running this program. It will look slightly different from the example one given, but the same content will be there.
 
 ### src directory. 
 
 This contains the .java files. There are three of them there:
 
 * Main.java - starts the program
 
 * Conveyors.java - the parent object in this simulation
 
 * Routers.java - the child object, this implements Runnable and does all of the locking and what not. 
 
 ### out directory
 
 This contains compiled versions of the files in the src directory. OpenJDK 11 was used to make these, but it was also tested on OpenJDK 13
