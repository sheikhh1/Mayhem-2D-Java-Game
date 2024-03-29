#!/bin/bash
JAVA_VERSION=`java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }'`

if [[ $JAVA_VERSION < "11.0.0" ]]
 then
   echo "Java version MUST be 15 or greater"
   exit 0
fi

find -name "*.java" > sources.txt
javac -d classes -cp libs/jsfml.jar @sources.txt
javadoc -d docs -cp libs/jsfml.jar @sources.txt