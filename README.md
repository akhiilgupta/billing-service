# Biller Service

Base URL for the service: https://biller-service.sloppy.zone

## JWT Authentication

JWT scheme ID: 7e528140-c760-4699-b864-26d6474121b5 <br>
JWT secret (base64 encoded): MTFiMjEyYWEtNDJkOC00MGEzLWIyZTYtZDQ1MzU2ZjhkYTE4

### JWT Token format

```
Header: {
  "alg": "HS256",
  "typ": "JWT"
}
Payload: {
  "aud": "7e528140-c760-4699-b864-26d6474121b5", // mandatory
  "jti": "82deb476-0318-45d9-9a0b-c8e704ee430f" // mandatory
}
Signature: HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), your-256-bit-secret)
```
## Sample APIs
Token is limited to 1 per request and needs to be generated each time to access the resource

### 1. Fetch customer bills
```
curl --location --request POST 'https://biller-service.sloppy.zone/v1/bills/fetch' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI3ZTUyODE0MC1jNzYwLTQ2OTktYjg2NC0yNmQ2NDc0MTIxYjUiLCJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwianRpIjoiODJkZWI0NzYtMDMxOC00NWQ5LTlhMGItYzhlNzA0ZWU0MzBmIn0.6LWCUgSdxPGe4VHQ7jqZZQhT8UHlFvThsv3o7-mKPY0' \
--header 'Content-Type: application/json' \
-d '{
    "customerIdentifiers": [
        {
            "attributeName": "mobileNumber",
            "attributeValue": "9999999998"
        }
    ]
}'
```
### 2. Fetch receipts
```
curl --location --request POST 'https://biller-service.sloppy.zone/v1/bills/fetchReceipt' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI3ZTUyODE0MC1jNzYwLTQ2OTktYjg2NC0yNmQ2NDc0MTIxYjUiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjIsImp0aSI6ImEwZmM3YmY2LTI4N2ItNGYyNy04NGJjLWE4ODY5NWZlOWJjOCJ9.uGdoY-A4Yqvc206EJVZzfLxCOnp-al750P__U6GDCqA' \
--data-raw '{
    "billerBillID"   : "e21e1Rasd3r1r3r2",
    "platformBillID" : "SETU121341312121",
    "paymentDetails" : {
        "platformTransactionRefID" : "TXN12121219",
        "uniquePaymentRefID"       : "XXXXAYYDDD999999",
        "amountPaid" : {
            "value" : 8900
        },
        "billAmount" : {
            "value" : 8900
        }
    }
}'
```
## Database Schema
### 1. Customers Table
```
 id |      name      |         email         | display_name  |   phone    | account_id | metadata | active |         created_at         
----+----------------+-----------------------+---------------+------------+------------+----------+--------+----------------------------

  1 | Ashok Kumar    | ashok.kumar@gmail.com | Ashok Kumar   | 9999999998 | 1234567890 | {}       | t      | 2020-05-24 18:50:38.173382

  2 | Rohan Saraf    | rohan.saraf@gmail.com | Rohan Saraf   | 9999999997 | 1234567891 | {}       | t      | 2020-05-24 18:50:38.173382

  3 | Varun Mundhra  | v2mundhra@gmail.com   | Varun Mundhra | 9999999996 | 1234567892 | {}       | t      | 2020-05-24 18:50:38.173382

  4 | Avi Gupta      | avi@gmail.com         | Avi           | 9699999996 | 1234567893 | {}       | t      | 2020-05-24 18:50:38.173382

  5 | Love Mehta     | love@gmail.com        | Love          | 8999999996 | 1234567894 | {}       | t      | 2020-05-24 18:50:38.173382

  6 | Akhil Gupta    | akhil@gmail.com       | Akhil Gupta   | 7999999996 | 1234567895 | {}       | t      | 2020-05-24 18:50:38.173382

  7 | Chinmaya Verma | chinmaya@gmail.com    | Varun         | 6999999996 | 1234567896 | {}       | t      | 2020-05-24 18:50:38.173382
```
### 2. Bills Table
```
 id |     bill_id      |     status      | customer_account | bill_type | recurrence | amount_exactness | pending_amount | total_amount | metadata |          due_date          |         created_at        
----+------------------+-----------------+------------------+-----------+------------+------------------+----------------+--------------+----------+----------------------------+---------------------------
  1 | e21e1Rasd3r1r3r1 | CREATED         | 1234567890       | DEBIT     | ONE_TIME   | EXACT            |            900 |          900 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  2 | e21e1Rasd3r1r3r2 | CREDIT_RECEIVED | 1234567890       | DEBIT     | ONE_TIME   | EXACT            |           8900 |         8900 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  3 | e21e1Rasd3r1r3r3 | CREATED         | 1234567892       | DEBIT     | ONE_TIME   | EXACT            |            890 |          890 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  4 | e21e1Rasd3r1r3r4 | SETTLED         | 1234567893       | DEBIT     | ONE_TIME   | EXACT            |            400 |          400 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  5 | e21e1Rasd3r1r3r5 | CREDIT_RECEIVED | 1234567890       | DEBIT     | ONE_TIME   | EXACT            |           4000 |          400 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  6 | e21e1Rasd3r1r3r6 | SETTLED         | 1234567895       | DEBIT     | ONE_TIME   | EXACT            |            400 |          450 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  7 | e21e1Rasd3r1r3r7 | CREDIT_RECEIVED | 1234567896       | DEBIT     | ONE_TIME   | EXACT            |            400 |          400 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382

  9 | e21e1Rasd3r1r3r8 | CREATED         | 1234567897       | DEBIT     | ONE_TIME   | EXACT            |            400 |          460 | {}       | 2020-05-25 18:50:38.173382 | 2020-05-24 18:50:38.173382
```
