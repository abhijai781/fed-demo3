# Testing the gRPC Service on Port 4011

## Method 1: Using the Test Script (Recommended)

Run the included test script:

```bash
npm run test:grpc
```

This will test the gRPC service and display the results in a formatted way.

## Method 2: Using the Test Script Directly

```bash
npx tsx test-grpc.ts
```

## Method 3: Testing via the Router (GraphQL Endpoint)

Since the router on port 3002 connects to the gRPC service on 4011, you can test indirectly:

### Using PowerShell:
```powershell
$response = Invoke-WebRequest -Uri "http://localhost:3002/graphql" -Method POST -Headers @{"Content-Type"="application/json"} -Body '{"query":"{ getCards(userId: \"1\") { userId cardId cardExpiry } }"}'; $response.Content
```

### Using curl (if installed):
```bash
curl -X POST http://localhost:3002/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"{ getCards(userId: \"1\") { userId cardId cardExpiry } }"}'
```

## Method 4: Check Service Health

### Check if service is listening:
```powershell
netstat -ano | findstr ":4011"
```

### Check the home endpoint:
```powershell
Invoke-WebRequest -Uri "http://localhost:4011/" -Method GET
```

## Expected Response

When testing with userId "1", you should see:

```json
{
  "getCards": {
    "list": {
      "items": [
        {
          "userId": "1",
          "cardId": "1001",
          "cardExpiry": "1229"
        },
        {
          "userId": "1",
          "cardId": "1002",
          "cardExpiry": "1129"
        }
      ]
    }
  }
}
```

## Troubleshooting

1. **Service not running?**
   - Start with: `npm run start:service`

2. **Port already in use?**
   - Check with: `netstat -ano | findstr ":4011"`
   - Kill process: `Stop-Process -Id <PID> -Force`

3. **REST API not responding?**
   - Verify the Card service is running on port 8001
   - Test directly: `Invoke-WebRequest -Uri "http://localhost:8001/cardservice/api/cards/1"`

