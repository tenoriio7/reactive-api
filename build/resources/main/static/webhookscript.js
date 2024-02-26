var ws = new WebSocket("ws://localhost:8080/webhooks")
var webhook = document.getElementById("webhook")

ws.onmessage = function (event) {
    var mensagem = event.data
    var div = document.createElement("div")
    div.classList.add("message")
    div.textContent = JSON.stringify(JSON.parse(mensagem), null, 2)
    div.style.transform = "scale(0)"
    webhook.appendChild(div)
    setTimeout(function () {
        div.style.transform = "scale(1)"
    }, 0)
    webhook.scrollTop = webhook.scrollHeight
}