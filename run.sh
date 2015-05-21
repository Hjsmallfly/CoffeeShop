#!/bin/bash - 
#===============================================================================
#
#          FILE: run.sh
# 
#         USAGE: ./run.sh or bash run.sh
# 
#   DESCRIPTION: 
# 
#       OPTIONS: ---
#  REQUIREMENTS: ---
#          BUGS: ---
#         NOTES: ---
#        AUTHOR: smallfly  
#  ORGANIZATION: 
#       CREATED: 05/20/2015 23:19
#      REVISION:  ---
#===============================================================================

set -o nounset                              # Treat unset variables as an error

java -cp .:./lib/beautyeye_lnf.jar exercise.MainFrame
