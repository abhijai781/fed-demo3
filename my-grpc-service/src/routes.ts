import type { ConnectRouter } from "@connectrpc/connect";
import { ServiceV1, type QueryGetCardsRequest } from "./generated/service/v1/service_pb";

export default (router: ConnectRouter) => {
    router.service(ServiceV1, {
        queryGetCards: async (req: QueryGetCardsRequest) => {
            const userId = req.userId;

            // Call the REST API to fetch cards for the given userId
            const response = await fetch(`http://localhost:8001/cardservice/api/cards/${encodeURIComponent(userId)}`);

            if (!response.ok) {
                throw new Error(`Failed to fetch cards: ${response.statusText}`);
            }

            const cards = await response.json();

            // Return the cards with the expected protobuf structure
            return {
                getCards: {
                    list: {
                        items: cards.map((card: any) => ({
                            userId: card.userId,
                            cardId: card.cardId,
                            cardExpiry: card.cardExpiry
                        }))
                    }
                }
            };
        }
    });
};