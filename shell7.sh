#!/usr/bin/env bash

for i in {1..30}; do cat example$i.data RebenchDump/OrginalAs/example$i.data > RebenchDump/data$i.data; done
