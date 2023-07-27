#!/bin/bash
npm run build:dev; python3 -m http.server 8890 --directory dist
