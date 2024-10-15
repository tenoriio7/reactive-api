// instancia de conexao com websocket
var ws = new WebSocket("ws://localhost:8080/webhooks")

//elemento que contem o webhook
var webhook = document.getElementById("webhook")

// quando alguma mensagem via websocket chega
ws.onmessage = function (event) {

    var mesage = event.data // extrai o valor
    var div = document.createElement("div") // cria uma div pra colocar a msg
    div.classList.add("message") //adiciona
    div.textContent = JSON.stringify(JSON.parse(mesage), null, 2) // parse para json
    div.style.transform = "scale(0)" // transforma o style
    webhook.appendChild(div) // coloca o valor
    setTimeout(function () {
        div.style.transform = "scale(1)"
    }, 0)
    webhook.scrollTop = webhook.scrollHeight // configura o scroll para ficar alinhado
}