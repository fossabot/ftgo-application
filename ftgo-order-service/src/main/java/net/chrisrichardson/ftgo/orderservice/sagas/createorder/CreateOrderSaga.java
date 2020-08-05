package net.chrisrichardson.ftgo.orderservice.sagas.createorder;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import net.chrisrichardson.ftgo.kitchenservice.api.CreateTicketReply;
import net.chrisrichardson.ftgo.orderservice.sagaparticipants.AccountingServiceProxy;
import net.chrisrichardson.ftgo.orderservice.sagaparticipants.ConsumerServiceProxy;
import net.chrisrichardson.ftgo.orderservice.sagaparticipants.KitchenServiceProxy;
import net.chrisrichardson.ftgo.orderservice.sagaparticipants.OrderServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaState> {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SagaDefinition<CreateOrderSagaState> sagaDefinition;

    public CreateOrderSaga(OrderServiceProxy orderService, ConsumerServiceProxy consumerService, KitchenServiceProxy kitchenService,
                           AccountingServiceProxy accountingService) {
        this.sagaDefinition =
                step()
                        .withCompensation(orderService.reject, CreateOrderSagaState::makeRejectOrderCommand)
                        .step()
                        .invokeParticipant(consumerService.validateOrder, CreateOrderSagaState::makeValidateOrderByConsumerCommand)
                        .step()
                        .invokeParticipant(kitchenService.create, CreateOrderSagaState::makeCreateTicketCommand) // 定义转发事务
                        .onReply(CreateTicketReply.class, CreateOrderSagaState::handleCreateTicketReply) // 收到成功回复后，调用handleCreateTicketReply()
                        .withCompensation(kitchenService.cancel, CreateOrderSagaState::makeCancelCreateTicketCommand) // 定义补偿事务
                        .step()
                        .invokeParticipant(accountingService.authorize, CreateOrderSagaState::makeAuthorizeCommand)
                        .step()
                        .invokeParticipant(kitchenService.confirmCreate, CreateOrderSagaState::makeConfirmCreateTicketCommand)
                        .step()
                        .invokeParticipant(orderService.approve, CreateOrderSagaState::makeApproveOrderCommand)
                        .build();
    }

    @Override
    public SagaDefinition<CreateOrderSagaState> getSagaDefinition() {
        return sagaDefinition;
    }
}
