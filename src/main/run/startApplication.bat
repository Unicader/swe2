@echo off
cd /d "..\..\..\"
javac -cp .;resources/gson-2.11.0.jar .;de/dhbw/cm/main/Main.java
java -cp .;resources/gson-2.11.0.jar .;de/dhbw/cm/main/Main