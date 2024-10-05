# Create config

curl -X POST http://localhost:8091/v1/admin/currency-converter \
     -H "Content-Type: application/json" \
     -d '{
           "currencyCode": "EUR",
           "enabled": true,
           "frequencyInMinutes": 10
         }'
