var ws = new WebSocket("ws://localhost:8080/webhooks")
var mensagem = document.getElementById("mensagem")
var botaoEnviar = document.getElementById("botao-enviar")
var ul = document.getElementById("mensagens-recebidas")

botaoEnviar.onclick = function(){
    ws.send(mensagem.value)
}

ws.onmessage = function(event) {
    var li = document.createElement("li")
    li.innerHTML = event.data + ' <span class="expander">[Expandir]</span>'

    // Adicionar um evento de clique ao span expander
    var expander = li.querySelector('.expander');
    expander.addEventListener('click', function() {
        var conteudoExpandido = document.createElement('div');
        conteudoExpandido.classList.add('expandido');
        conteudoExpandido.innerHTML = event.data;
        li.appendChild(conteudoExpandido);
        expander.remove(); // Remover o span expander
    });

    ul.appendChild(li)
}
