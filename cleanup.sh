#!/bin/bash 
find . -name .gradle -exec rm -rf {} \;
find . -name build -exec rm -rf {} \;