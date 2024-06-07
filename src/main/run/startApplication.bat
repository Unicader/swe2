@echo off
cd /d "../../.."

REM Define the directory for class files
set CLASS_DIR=bin

REM Delete old class files
rmdir /s /q %CLASS_DIR%

REM Compile the Java files and place the class files in the specified directory
javac -d %CLASS_DIR% -cp src/main/java;src/main/java/resources/gson-2.11.0.jar src/main/java/de/dhbw/cm/main/Main.java

REM Run the compiled Java program, specifying the class files directory in the classpath
java -cp %CLASS_DIR%;src/main/java/resources/gson-2.11.0.jar de.dhbw.cm.main.Main