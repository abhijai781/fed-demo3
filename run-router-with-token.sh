#!/bin/bash

# Option 2: Run with Cosmo CDN (requires API token and Zscaler certificate)
# First, build custom image with Zscaler CA cert installed

echo "Building custom router image with Zscaler certificate..."
docker build -f Dockerfile.router -t cosmo-router-with-zscaler .

echo "Starting Cosmo Router with Zscaler certificate..."

# Replace YOUR_API_TOKEN with your actual token
docker run \
  --name cosmo-router \
  --rm \
  -p 3002:3002 \
  --add-host=host.docker.internal:host-gateway \
  -v "router.execution.config.json:/app/config.json:ro" \
  -e DEV_MODE=true \
  -e LISTEN_ADDR=0.0.0.0:3002 \
  -e EXECUTION_CONFIG_FILE=/app/config.json \
  -e GRAPH_API_TOKEN=eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI4MjAxMjVlYy1jN2U2LTQ4MDEtOWNiNS1jZTVmODExMTYzN2IiLCJmZWRlcmF0ZWRfZ3JhcGhfaWQiOiIxMjRjNDY4Zi1mMzY0LTQ1OWItYjkxMy1jMDhhZjE5MzExNGQiLCJhdWQiOiJjb3NtbzpncmFwaC1rZXkiLCJvcmdhbml6YXRpb25faWQiOiIwOGQ0NGU4Ny1mYTg5LTRmYTgtYmRkZC0yYThmZmNjNmYxN2YiLCJpYXQiOjE3Njc5NzA0OTd9.wwtyMgB86pRVXifBH72hlMPSi5Acp36Fwu_JfBuCLVY \
  cosmo-router-with-zscaler

