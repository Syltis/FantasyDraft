var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        // $("#conversation").show();
    }
    else {
        // $("#conversation").hide();
    }
    $("#greetings").html("");
}



function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/fantasy/players/', function (feedback) {
            showGreeting(JSON.parse(feedback.body).content);
        });
        stompClient.subscribe('/fantasy/picks/', function (feedback) {
            showPick(JSON.parse(feedback.body).content);
        });
        sendName();
    });

}

function disconnect() {
    stompClient.send("/app/leave", {}, JSON.stringify({'name': $("#name").val()}));
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");

}

function sendName() {
    stompClient.send("/app/join", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendPick() {
    stompClient.send("/app/pick", {}, JSON.stringify({'name': $("#name").val(),'pick': $("#pick").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showPick(content) {
    $("#picks").append("<tr><td>" + content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendPick(); });
});