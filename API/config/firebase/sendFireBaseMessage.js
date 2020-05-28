
var admin = require("firebase-admin");

var serviceAccount = require("../firebase/pushnotification.json");


admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://pushnotification-228b3.firebaseio.com"
});

var payload = {
    data: {
        MyKey1: "EU CONSEGUI !!!!!!!!!!!"
    }
}

var option = {
    priority: "high",
    timeToLive: 60 * 60 * 24
}

module.exports = {
    async sendMessage(title, message, arrayAdmins) {
        payload = {
            data: {
                title,
                message: message,
                id: "1"
            }
        }
        arrayAdmins.map(async (user) => {
            var response = await admin.messaging().sendToDevice(user.device, payload, option)
            console.log(user.device)
        })

        //console.log(response)
        //return response

    }
}

/*admin.messaging().sendToDevice(registrationToken,payload,option).then((response)=>{
    console.log(response)
}).catch((error)=>{
    console.error({erro:error})
})*/


