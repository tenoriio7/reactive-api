document.addEventListener("DOMContentLoaded", function() {
    var webhooksDiv = document.getElementById("webhook");

    fetch("http://localhost:8080/webhook")
        .then(response => response.json())
        .then(data => {
            data.forEach(webhook => {
                var div = document.createElement("div");
                div.classList.add("message");

                // Parse da propriedade notification para exibir como JSON formatado
                var notificationObj = JSON.parse(webhook.notification);
                div.textContent = JSON.stringify(notificationObj, null, 2);

                webhooksDiv.appendChild(div);
            });
        })
        .catch(error => {
            console.error("Erro ao buscar webhooks:", error);
        });
});
