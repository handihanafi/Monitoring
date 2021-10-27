const firebaseConfig = {

    apiKey: "AIzaSyCUytxpTlXRyJDEDXq72zTL0jRh8C1ZPEs",

    authDomain: "fir-webapp-351b7.firebaseapp.com",

    projectId: "fir-webapp-351b7",

    storageBucket: "fir-webapp-351b7.appspot.com",

    messagingSenderId: "1040064132406",

    appId: "1:1040064132406:web:281212b49c6235dfe42f97",

    measurementId: "G-L96ESSLP6K"

  };


  // Initialize Firebase

  firebase.initializeApp(firebaseConfig);

  firebase.auth.Auth.Persistence.LOCAL;

  // $("#submit").click(function(){
  //   var email = $("#email").val();
  //   var password = $("#password").val();

  //   if (email != && password != ""){
  //     var result = firebase.auth().signInWithEmailAndPassword(email, password);

  //     result.catch(function(error){
  //       var errorCode = error.code;
  //       var errorMessage = error.message;

  //       console.log(errorCode);
  //       console.log(errorMessage);

  //       window.alert("Message: " + errorMessage);
  //     });
  //   }else{
  //     window.alert("Form is incomplite. Please fill out all fields.");
  //   }

  // });
