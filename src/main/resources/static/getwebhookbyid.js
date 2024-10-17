document.addEventListener("DOMContentLoaded", function() {
    var searchBtn = document.getElementById("searchBtn"); // Botão de buscar
    var webhookIdInput = document.getElementById("webhookIdInput"); // Campo de texto para o ID
    var webhooksDiv = document.getElementById("webhook"); // Div onde os resultados serão exibidos

    // Adiciona o evento de clique ao botão
    searchBtn.addEventListener("click", function() {
        var webhookId = webhookIdInput.value; // Pega o valor do campo de texto

        // Limpa a área de resultado
        webhooksDiv.innerHTML = "";

        if (webhookId) {
            // Faz a requisição fetch com o ID informado
            fetch(`http://localhost:8080/webhook/${webhookId}`)
                .then(response => response.json())
                .then(data => {
                    var div = document.createElement("div");
                    div.classList.add("message");

                    // Parse da propriedade notification para exibir como JSON formatado
                    var notificationObj = JSON.parse(data.notification);
                    div.textContent = JSON.stringify(notificationObj, null, 2);

                    webhooksDiv.appendChild(div);
                })
                .catch(error => {
                    console.error("Erro ao buscar webhook:", error);
                    webhooksDiv.textContent = "Erro ao buscar webhook. Verifique o console para mais detalhes.";
                });
        } else {
            webhooksDiv.textContent = "Por favor, digite um ID válido.";
        }
    });
});
