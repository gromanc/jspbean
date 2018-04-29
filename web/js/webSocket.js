/**
 * Created by Roma on 14.09.2014.
 */
var websocket;
//Counter used to keep track of the count of messages sent to server
var counter;

var serverUrl= "ws://localhost:8080"+"/jspbean"+"/webSocket"
window.onload = function(ContextPath) {

  counter = 1;
  var wsStartBtn = document.getElementById("startWsConn");
  var wsSendBtn = document.getElementById("sendWsData");
  var wsStatusDiv = document.getElementById("wsStatus");
  wsStartBtn.onclick = function() {

    //Websocket connection initialization.
    websocket = new WebSocket(serverUrl);

    //registering the callback for various events.
    websocket.onopen = function() {

      wsStatusDiv.innerHTML = "Connected to the web socket";
      var wsSendDiv = document.getElementById("wsSendStatus");
      wsSendDiv.innerHTML = "";

    }
    websocket.onerror = function() {
      wsStatusDiv.innerHTML = "ERROR while connecting to the web socket";
    }

    websocket.onclose = function() {
      wsStatusDiv.innerHTML = "Connection  to Web Socket CLOSED";
    }

    websocket.onmessage = function(event) {
      //Showing the message from the server on the UI.
      var serverMsgList = document.getElementById("msgServer");
      var aMessage = document.createElement("li");
      aMessage.innerHTML = event.data;
      serverMsgList.appendChild(aMessage);
    }
  }

  wsSendBtn.onclick = function() {
    var wsSendDiv = document.getElementById("wsSendStatus");
    var msg;
    if (websocket != null) {
      if (websocket.readyState == WebSocket.OPEN) {
        websocket.send("Sending message number: " + counter + " from client");
        msg = "Message sent!!";

        counter++;
      }
      else if (websocket.readyState == WebSocket.CLOSING) {
        msg = "Websocket connection is CLOSING. Please start a new connection";

      }
      else if (websocket.readyState == WebSocket.CLOSED) {
        msg = "Websocket connection is CLOSED. Please start a new connection";

      }
      wsSendDiv.innerHTML = msg;
      console.log(msg);
    }
  }

  var closeBtn = document.getElementById("closeWsConn");
  closeBtn.onclick = function() {
    if (websocket != null) {
      websocket.close();
    }
  }
}