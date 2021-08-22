package com.jakimenkogrpcdemo.client

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import ru.jakimenko.grpcdemo.proto.MessageOuterClass
import ru.jakimenko.grpcdemo.proto.TelegramMessageGrpc


interface GRpcClientService {
    fun call(): String
}

@Service
class GRpcClientServiceImpl: GRpcClientService {

    @GrpcClient("TgMessage")
    lateinit var stub: TelegramMessageGrpc.TelegramMessageBlockingStub

    override fun call(): String {
        val responseId = stub.saveUpdate(makeUpdateObj())
        println("responseId = ${responseId.id}")
        val id = responseId.id
        val updateObj = stub.getUpdate(MessageOuterClass.ResponseId.newBuilder().setId(id).build())
        println("from user: ${updateObj.message.from.firstName}")
        return updateObj.message.from.firstName
    }

    fun makeUpdateObj(): MessageOuterClass.Update {
        return MessageOuterClass.Update.newBuilder()
            .setUpdateId(137609777)
            .setMessage(
                MessageOuterClass.Message.newBuilder()
                    .setMessageId(18)
                    .setDate(1629310777)
                    .setText("/hello")
                    .setFrom(
                        MessageOuterClass.User.newBuilder()
                            .setId(365683777)
                            .setIsBot(false)
                            .setFirstName("Ivan")
                            .setLastName("Ivanov")
                            .setUsername("ivanov")
                            .setLanguage(MessageOuterClass.Lang.RUS)
                    )
                    .setChat(
                        MessageOuterClass.Chat.newBuilder()
                            .setId(365683777)
                            .setFirstName("Ivan")
                            .setLastName("Ivanov")
                            .setUsername("ivan")
                            .setType("private")
                    )
                    .putAllServiceMap(
                        mutableMapOf(
                            "1" to MessageOuterClass.ServiceType.newBuilder().setId(101).build(),
                            "2" to MessageOuterClass.ServiceType.newBuilder().setId(202).build()
                        )
                    )
            )
            .build()
    }
}

