#!/bin/bash
JAVA_VERSION=`java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }'`

if [[ $JAVA_VERSION < "15.0.0" ]]
 then
   echo "Java version MUST be 15 or greater"
   exit 0
fi

cd classes/me/mayhem
java -cp libs/jsfml.jar:classes/.:resources/. me/mayhem/Mayhem