#!/usr/bin/env bash

# Define the base directory
base_dir="ProfilerDumps"

# Loop through 30 profile dumps
for i in {1..30}; do
    rm "${base_dir}/ProfilesDump${i}/AsyncDumps/"* \
       "${base_dir}/ProfilesDump${i}/HonestProfiler/"* \
       "${base_dir}/ProfilesDump${i}/JavaFlightRecorder/"* \
       "${base_dir}/ProfilesDump${i}/JProfiler/"* \
       "${base_dir}/ProfilesDump${i}/Perf/"* \
       "${base_dir}/ProfilesDump${i}/YourKit/"*
done
