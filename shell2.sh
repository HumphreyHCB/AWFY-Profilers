#!/usr/bin/env bash

PREFIX=$1


/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Bounce.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Bounce.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/CD.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/CD.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/DeltaBlue.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/DeltaBlue.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Havlak.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Havlak.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Json.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Json.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/List.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/List.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Mandelbrot.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Mandelbrot.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/NBody.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/NBody.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Permute.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Permute.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Queens.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Queens.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Richards.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Richards.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Sieve.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Sieve.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Storage.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Storage.txt
/home/hburchell/jprofiler13/bin/jpexport ProfilesDump$PREFIX/JProfiler/Towers.jps HotSpots -format=xml ProfilesDump$PREFIX/JProfiler/Towers.txt
