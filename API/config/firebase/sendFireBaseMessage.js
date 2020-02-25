
var admin = require("firebase-admin");

var serviceAccount = require("../firebase/pushnotification.json");

var registrationToken = "csIbPDmL1I4:APA91bHoarDLdUbTDjt5DzROzQZ_mIXK_Vb1ZBnhdZp1t8ygiCcHhXF2ZnfInWzG0fGpfykIrym4xzpvEBO4N5UClFOJ9Wh1rHS6MkC2AE0x3LXvEl-VS9p1gBtyKbB6CayqAVBvcsv2"

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

module.exports ={
    async sendMessage(title, message, arrayAdmins){
        payload = {
            data:{
                title,
                text:message,
                id:"1"
            }
        }
arrayAdmins.map(async (user)=>{
    var response = await admin.messaging().sendToDevice(user.device,payload,option)
    console.log(response)
})
         
            console.log(response)
            return  response
        
    }
}

/*admin.messaging().sendToDevice(registrationToken,payload,option).then((response)=>{
    console.log(response)
}).catch((error)=>{
    console.error({erro:error})
})*/


