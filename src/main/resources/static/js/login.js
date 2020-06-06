function checkConfirmPassword(){
    var password = $("#registry_password").val() ;
    var confirmPassword = $("#registry_confirmPassword").val() ;

    if (password === confirmPassword){
        $("#btnSubmitRegistry").prop('disabled', false);

    }
    else{
        alert("password khong trung khop");
    }
}

