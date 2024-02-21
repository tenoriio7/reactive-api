package br.com.tenorio.reactiveapi.handler

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebsocketHandler : TextWebSocketHandler() {

    private val sessions: MutableList<WebSocketSession> = mutableListOf()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        sessions.add(session)
        println("adicionei a sessao ${session.id}")
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        super.handleMessage(session, message)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        sessions.remove(session)
    }

    fun sendMessageToAll(message: TextMessage) {
        sessions.forEach { session ->
            session.sendMessage(message)
        }
    }
}
