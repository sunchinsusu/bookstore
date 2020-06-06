
window.onload = function(){
    if (document.getElementById('paypal').checked === true) {
        document.getElementById("btnBooking").setAttribute("formaction","/booking-paypal");
    }
    if (document.getElementById('cash').checked === true) {
        document.getElementById("btnBooking").setAttribute("formaction","/booking-cash");
    }
}

function changeMethod() {
    if (document.getElementById('paypal').checked === true) {
        document.getElementById("btnBooking").setAttribute("formaction","/booking-paypal");
    }
    if (document.getElementById('cash').checked === true) {
        document.getElementById("btnBooking").setAttribute("formaction","/booking-cash");
    }
}

function cancelOrder(i) {
    bootbox.confirm({
        title: "Cancel Order?",
        message: "Do you want to cancel this booking? That will adversely affect your reputation",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancel'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirm'
            }
        },
        callback: function (result) {
            if(result==true){
                var link = "/oder/cancel/"+i;
                location.href = link;
            }
        }
    });
}