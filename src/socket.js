import { ref } from "vue";
// import { io } from "socket.io-client";

import * as io from 'socket.io-client';



export const state = ref({
  connected: false,
  recalls: [],
});

// "undefined" means the URL will be computed from the `window.location` object
const userId=JSON.parse(localStorage.getItem("loginFlag"))["id"];
const URL = "http://localhost:8003?userId="+userId;

export const socket = io(URL,{
  autoConnect: false
});

socket.on("connect", () => {
  state.value.connected = true;
  console.log("连接",state.value.connected)

});

socket.on("disconnect", () => {
  state.value.connected = false;
  console.log("断开",state.value.connected)

});

socket.on("recall", (...args) => {
  state.value.recalls.push(args);
  console.log("recall",state.value.recalls)
});

