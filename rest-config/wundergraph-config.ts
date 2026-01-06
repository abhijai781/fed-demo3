import {
  configureWunderGraphApplication,
  introspect,
  api,
} from '@wundergraph/sdk';

// Introspect your Spring Boot REST API via OpenAPI
const cardsApi = introspect.openApi({
  apiNamespace: "cards",
  source: {
    kind: "url",
    url: "http://localhost:8001/cardservice/v3/api-docs", // Spring Boot OpenAPI spec
  },
});

// Configure WunderGraph application
configureWunderGraphApplication({
  apis: [
    cardsApi
  ],
  operations: [
    // You can define custom GraphQL operations here if needed
  ],
  server: {
    // Optional: add custom server logic
  },
  codeGenerators: [
    {
      templates: [
        // Generates TypeScript client
        require('@wundergraph/sdk/dist/codegen/templates/typescript')
      ],
      path: "./generated",
    }
  ]
});