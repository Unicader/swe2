@echo off
cd /d "../../.."
javac -cp src/main/java;src/main/java/resources/gson-2.11.0.jar src/main/java/de/dhbw/cm/main/Main.java
java -cp src/main/java;src/main/java/resources/gson-2.11.0.jar de.dhbw.cm.main.Main