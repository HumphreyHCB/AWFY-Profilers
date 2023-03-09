#!/usr/bin/env bash


# example of how to run a normal benchmark
#java -cp benchmarks/Java/benchmarks.jar Harness Towers 1 1

# you might need to set these on first time use
# sudo sysctl kernel.perf_event_paranoid=1
# sudo sysctl kernel.kptr_restrict=0


java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Havlak10_1000_15000.txt -cp benchmarks/Java/benchmarks.jar Harness Havlak 1000 15000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Mandelbrot10_1000_500.txt -cp benchmarks/Java/benchmarks.jar Harness Mandelbrot 1000 500

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/NBody10_1000_250000.txt -cp benchmarks/Java/benchmarks.jar Harness NBody 1000 250000


