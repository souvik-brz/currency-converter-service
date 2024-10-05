# Update Config

clear && curl -X PUT http://localhost:8091/v1/admin/currency-converter/1 \
  -H "Content-Type: application/json" \
  -d '{
           "currencyCode": "EUR",
           "enabled": true,
           "frequencyInMinutes": 10
         }'
