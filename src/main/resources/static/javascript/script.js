console.log("this is script file");

const togglesidebar = () => {
 if($(".sidebar").is(":visible")){
  $(".sidebar").css("display","none");
  $(".content").css("margin-left","0%");

 }else{
    $(".sidebar").css("display","block");
    $(".content").css("margin-left","20%");

 }
};

//For Payment 

//request for creating order

const paymentStart=()=>{
 console.log("payment starting...");
  let amount = $("#pay_field").val();
  console.log(amount);

  if(amount=="" || amount==null){
     alert("amount is required !!!");
     return;
  }

//code.....

  //Requesting to server byusing Ajax-jquery
  // to create order
  
  $.ajax(
     {
         url: '/user/create_order',
         data:JSON.stringify({amount:amount,info:'order_request'}),   
         contentType:'application/json',
         type:'POST',
         datatype:'json',
         success:function(response){
            //invoked when success
            console.log(response);
            
             if(response.status == "created"){
                //open payment form
              let options={
                 key:"rzp_test_uUcNFVxM3dHSQp",
                 amount:response.amount,
                 currency:"INR",
                 name:"E-Waste MANAGEMENT",
                 description:"Payment of Product",
                 image:"https://5.imimg.com/data5/VX/WX/MY-1100475/e-waste-management-1000x1000.jpg",
                 order_id: response.id,
                  
                 handler:function(response){
                    console.log(response.razorpay_payment_id);
                    console.log(response.razorpay_order_id);
                    console.log(response.razorpay_signature);
                    console.log("payment sucessfull!!!");
                    alert("Congrats !! payment successfull !!");
                 },
                 prefill: {
                  "name": "",
                  "email": "",
                  "contact": "",
               },
              notes: {
               "address": "E-waste Management Office",
              },
              theme: {
               "color": "#3399cc",
           },
         
         };
              //opening razorpay form
             let rzp = new Razorpay(options);
 
            
             rzp.on('payment.failed', function (response){
               console.log(response.error.code);
               console.log(response.error.description);
               console.log(response.error.source);
               console.log(response.error.step);
               console.log(response.error.reason);
               console.log(response.error.metadata.order_id);
               console.log(response.error.metadata.payment_id);

               alert("OOPS...payment failed!!!")
       });
          
                      rzp.open();

        
         }
         },
         error:function(error){
            //invoked when error
            console.log(error)
            alert("something went wrong!!!")
         },
     
     
     });
   
    };
