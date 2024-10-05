# Exchange Currency

clear && curl -X POST http://localhost:8091/v1/currency-exchange \
  -H "Content-Type: application/json" \
  -d '{
        "fromValue": "12",
        "fromCurrencyCode": "USD",
        "toCurrencyCode": "DKK"
      }'
