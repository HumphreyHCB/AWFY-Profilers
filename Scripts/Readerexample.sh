#!/usr/bin/env bash





# read a java flight recorder file

jfr summary Dumps/JFR/Bounce.jfr

# read perf data file

perf report --stdio -i Bounce.data

# convert yourkit snapshot to somthing we can read
 java -jar /home/hburchell/ProgramFiles/YourKit-JavaProfiler-2022.9-b178/YourKit-JavaProfiler-2022.9/lib/yourkit.jar -export Dumps/bounce-2023-03-08-shutdown.snapshot ./
# warring it makes a lot of mess

# this gives you just the hottest method
# java -Dexport.method.list.cpu -Dexport.class.list -Dexport.txt -jar /home/hburchell/ProgramFiles/YourKit-JavaProfiler-2022.9-b178/YourKit-JavaProfiler-2022.9/lib/yourkit.jar -export Dumps/bounce-2023-03-08-shutdown.snapshot ./

# for honest profiler
# you need java 8s tools.jar to use the reader
./console -log log.hpl