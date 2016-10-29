#!/bin/bash

BASE_DIR=`pwd`
echo "### process dir: $BASE_DIR"
COUNTER=0
for folder  in `ls $BASE_DIR`
do
	if [ -d "$folder" ]; then
		cd $folder
		#echo "$folder"
		if [ -f "pom.xml" ]; then 
			echo "### $folder/pom.xml"
			mvn clean
			let "COUNTER+=1"
		fi
		cd ..
	fi
done
echo "Done, process count: $COUNTER"
