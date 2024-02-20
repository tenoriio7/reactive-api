var socket = new WebSocket('ws://localhost:8080/ws');
socket.onopen = function(event) {
    console.log('Connected to WebSocket');
    socket.send('Mensagem de evento manual');
};
