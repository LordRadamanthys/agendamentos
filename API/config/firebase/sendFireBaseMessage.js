
var admin = require("firebase-admin");

var serviceAccount = require("../firebase/pushnotification.json");

var registrationToken = "dcKmXJspe5g:APA91bEdhbwqiUD47zSsjevh7KAYna9ggPazK9lgDSyJrpzY4bqLB4KBD-J_LeHA4TikBDI0zPgAWydy-1uyqAZWFcXphSNmZr-05ba4MBl8WnzuSnf3WQWxkbtan5Y_csV8SsHV7sw3"

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://pushnotification-228b3.firebaseio.com"
  });

var payload = {
    data:{
        MyKey1:"EU CONSEGUI ESSA MERDA!!!!!!!!!!!"
    }
}

var option = {
    priority:"high",
    timeToLive:60*60*24
}

admin.messaging().sendToDevice(registrationToken,payload,option).then((response)=>{
    console.log(response)
}).catch((error)=>{
    console.error({erro:error})
})


