#!/bin/bash

# Define the base directory for the profiler dumps
base_dir="/home/hburchell/Repos/AWFY-Profilers/ProfilerDumps"

# Define the list of benchmarks
benchmarks=("Bounce" "CD" "DeltaBlue" "Havlak" "Json" "List" "Mandelbrot" "NBody" "Permute" "Queens" "Richards" "Sieve" "Storage" "Towers" "Bounce")

# Define the YourKit Java Profiler jar location
yourkit_jar="/home/hburchell/YourKit-JavaProfiler-2024.3/lib/yourkit.jar"

# Loop through all ProfilesDump directories
for i in $(seq 1 10); do
    echo "Processing ProfilesDump$i..."
    
    for benchmark in "${benchmarks[@]}"; do
        snapshot_file="${base_dir}/ProfilesDump${i}/YourKit/${benchmark}-2024-04-19-shutdown.snapshot"
        destination_txt="${base_dir}/ProfilesDump${i}/YourKit/"

        # Check if the snapshot file exists
        if [ -f "$snapshot_file" ]; then
            echo "Exporting $snapshot_file to $destination_txt"
            # Export the snapshot to TXT
            sudo java -Dexport.method.list.cpu -Dexport.class.list -Dexport.txt -jar $yourkit_jar -accept-eula -export $snapshot_file $destination_txt

            # Rename the "Method-list-CPU.txt" file
            method_list_file="${base_dir}/ProfilesDump${i}/YourKit/Method-list-CPU.txt"
            new_method_list_file="${base_dir}/ProfilesDump${i}/YourKit/${benchmark}-Method-list-CPU.txt"
            if [ -f "$method_list_file" ]; then
                mv "$method_list_file" "$new_method_list_file"
                echo "Renamed Method-list-CPU.txt to ${benchmark}-Method-list-CPU.txt"
            fi
        else
            echo "Snapshot file not found: $snapshot_file"
        fi
    done
done

echo "All exports completed."
