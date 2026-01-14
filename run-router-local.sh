#!/bin/bash
  ghcr.io/wundergraph/cosmo/router:latest
  -e EXECUTION_CONFIG_FILE=/app/config.json \
  -e LISTEN_ADDR=0.0.0.0:3002 \
  -e DEV_MODE=true \
  -v "$(pwd)/router.execution.config.json:/app/config.json:ro" \
  --pull always \
  --add-host=host.docker.internal:host-gateway \
  -p 3002:3002 \
  --rm \
  --name cosmo-router \
docker run \

echo "Starting Cosmo Router with local execution config..."

# NOTE: DEMO_MODE must NOT be set, as it overrides your config with demo schema
# This uses your router.execution.config.json file directly
# Option 1: Run with local execution config (no API token needed)


