#!/bin/bash - 
#===============================================================================
#
#          FILE: compile.sh
# 
#         USAGE: ./compile.sh or bash ./compile.sh
# 
#   DESCRIPTION: compile the java files
# 
#       OPTIONS: ---
#  REQUIREMENTS: ---
#          BUGS: ---
#         NOTES: ---
#        AUTHOR: smallfly
#  ORGANIZATION: 
#       CREATED: 05/20/2015 23:05
#      REVISION:  ---
#===============================================================================

set -o nounset                              # Treat unset variables as an error
javac -encoding utf-8 -d . -cp .:./usefulinterface:./resourcepath:./usefulinterface:./customizegui:./factory/:./product/:./lib/beautyeye_lnf.jar ./customizegui/*.java ./usefulinterface/*.java ./resourcepath/*.java  ./factory/*.java ./product/*.java ./*.java


