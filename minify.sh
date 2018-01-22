#!/bin/bash
set -ve

cd "$(dirname "$0")"
mvn clean package
cd target
unzip -d tmp ./RainbowMode-*.jar
cd tmp
rm -rf META-INF/
zip -v ../RainbowMode-minified.jar ./*
cd ..
rm -rf tmp
