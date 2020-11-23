var stompClient = null;
connect();

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat', function (greeting) {
            var content = JSON.parse(greeting.body).content;
            var user = JSON.parse(greeting.body).user;
            var time = JSON.parse(greeting.body).time;
            showMessage(content, user, time);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    if($("#content").val()!=""){
        stompClient.send("/app/chat", {}, JSON.stringify({'content': $("#content").val()}));
        $("#content").val("");
    }

}

function showMessage(message, user, time) {
    var tmp = time.split("T");
    var h = tmp[1].split(":")[0];
    var m = tmp[1].split(":")[1];
    $("#messages").append("<tr style='border-style: none;'><td style='border-style: none;'> " +h+":"+m+"</td><td style='border-style: none;'>[" +user+"]: </td><td style='min-width: 250px; border-style: none; text-align: left;'>" +message+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});