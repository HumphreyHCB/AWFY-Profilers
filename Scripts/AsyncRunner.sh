#!/usr/bin/env bash


# example of how to run a normal benchmark
#java -cp benchmarks/Java/benchmarks.jar Harness Towers 1 1

# you might need to set these on first time use
# sudo sysctl kernel.perf_event_paranoid=1
# sudo sysctl kernel.kptr_restrict=0

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Bounce10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Bounce 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/CD10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness CD 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/DeltaBlue10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness DeltaBlue 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Havlak10_1000_15000.txt -cp benchmarks/Java/benchmarks.jar Harness Havlak 1000 15000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Json10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Json 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/List10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness List 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Mandelbrot10_1000_500.txt -cp benchmarks/Java/benchmarks.jar Harness Mandelbrot 1000 500

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/NBody10_1000_250000.txt -cp benchmarks/Java/benchmarks.jar Harness NBody 1000 250000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Permute10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Permute 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Queens10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Queens 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Richards10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Richards 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Sieve10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Sieve 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Storage10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Storage 1000 1000

java -agentpath:Async/build/libasyncProfiler.so=start,event=cpu,interval=10,file=Dumps/Towers10_1000_1000.txt -cp benchmarks/Java/benchmarks.jar Harness Towers 1000 1000
