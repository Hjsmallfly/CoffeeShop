@echo off
javac -encoding utf-8 -d . -cp .;factory\;product\;lib\beautyeye_lnf.jar *.java factory\*.java product\*.java
pause