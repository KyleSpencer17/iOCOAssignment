H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

JSON Data for Post Request:

{
  "client":"Greg Guy",
  "vatRate":15.5,
  "invoiceDate":"2012-04-23",
  "items": [{"description":"Dell XPS21","unitPrice": 10000, "quantity":1},{"description":"Dog Bowl","unitPrice": 100, "quantity":10}]
}
