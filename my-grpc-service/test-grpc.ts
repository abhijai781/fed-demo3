import { createClient } from "@connectrpc/connect";
import { createGrpcTransport } from "@connectrpc/connect-node";
import { ServiceV1 } from "./src/generated/service/v1/service_pb.js";

async function testGrpcService() {
    // Create a gRPC transport
    const transport = createGrpcTransport({
        baseUrl: "http://localhost:4011",
        httpVersion: "2",
    });

    // Create a client
    const client = createClient(ServiceV1, transport);

    try {
        console.log("Testing getCards with userId: 1");
        const response = await client.queryGetCards({ userId: "1" });

        console.log("\n✅ Success! Response:");
        console.log(JSON.stringify(response, null, 2));

        if (response.getCards?.list?.items) {
            console.log(`\nFound ${response.getCards.list.items.length} cards:`);
            response.getCards.list.items.forEach((card, index) => {
                console.log(`  Card ${index + 1}:`);
                console.log(`    - userId: ${card.userId}`);
                console.log(`    - cardId: ${card.cardId}`);
                console.log(`    - cardExpiry: ${card.cardExpiry}`);
            });
        }
    } catch (error) {
        console.error("\n❌ Error:", error);
    }
}

testGrpcService();

