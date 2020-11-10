importScripts('https://www.gstatic.com/firebasejs/8.0.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.0.1/firebase-messaging.js');

// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyBmU4SYVom8JhQfspeX5C6MGsyx7VpxFoo",
    authDomain: "notifproj-9aeb3.firebaseapp.com",
    databaseURL: "https://notifproj-9aeb3.firebaseio.com",
    projectId: "notifproj-9aeb3",
    storageBucket: "notifproj-9aeb3.appspot.com",
    messagingSenderId: "405936828698",
    appId: "1:405936828698:web:02792066030f24f7b73ca3"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
messaging.onBackgroundMessage(function (payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
});