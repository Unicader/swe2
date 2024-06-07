#!/bin/bash

# Wechsle ins Basisverzeichnis
cd "$(dirname "$0")/../../.."

# Definiere das Verzeichnis für die Klassendateien
CLASS_DIR="bin"

# Delete old class files
rm -rf "$CLASS_DIR"

# Erstelle das Verzeichnis, falls es nicht existiert
mkdir -p "$CLASS_DIR"

# Kompiliere die Java-Dateien und platziere die Klassendateien im angegebenen Verzeichnis
javac -d "$CLASS_DIR" -cp "src/main/java:src/main/java/resources/gson-2.11.0.jar" src/main/java/de/dhbw/cm/main/Main.java

# Führe das kompilierte Java-Programm aus und spezifizieren das Verzeichnis für die Klassendateien im Klassenpfad
java -cp "$CLASS_DIR:src/main/java/resources/gson-2.11.0.jar" de.dhbw.cm.main.Main
