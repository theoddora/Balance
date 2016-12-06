//$("input:radio").on("click", function() {
//    $("input:text").attr("disabled", true);
//
//    $(this).next("input").attr("disabled", false)
//});
//
//function handleSelect() {
//    if (this.value == '01') {
//        document.getElementById('select02').disabled = true;
//    } else {
//        document.getElementById('select02').disabled = false;
//    }
//}

/*function displayProducts(){
 var product = document.getElementById("products").value;

 if(product === 'vegetable') {
 document.getElementById("fruitsSelect").style.display = "block";
 document.getElementById("vegetablesSelect").style.display = "none";
 }else if(product === 'fruit'){
 document.getElementById("fruitsSelect").style.display = "none";
 document.getElementById("vegetablesSelect").style.display = "block";
 }
 }*/

document.getElementById("products").addEventListener('change', function () {
    var product = this.value;

    if (product == "vegetable") {
        document.getElementById("fruitsSelect").style.display = "none";
        document.getElementById("vegetablesSelect").style.display = "inline-block";
    }
    else if (product == "fruit") {
        document.getElementById("fruitsSelect").style.display = "inline-block";
        document.getElementById("vegetablesSelect").style.display = "none";
    }
});

document.getElementById("forKilo").addEventListener('change', function () {

    document.getElementById("amountPiece").style.display = "none";

    document.getElementById("amountKilo").style.display = "inline-block";
    document.getElementById("price").placeholder = "Price for kilo";
    document.getElementById("price").style.display = "inline-block";


});

document.getElementById("forPiece").addEventListener('change', function () {


    document.getElementById("amountKilo").style.display = "none";

    document.getElementById("amountPiece").style.display = "inline-block";
    document.getElementById("price").placeholder = "Price for piece";
    document.getElementById("price").style.display = "inline-block";
    //$('input[name="price"]').attr('placeholder', 'Price for piece');

    //$('#price').attr('placeholder','Some New Text');

});

$(document).ready(function () {
    $('input[name="isForKilo"]').attr('checked', false);
});

function reload() {
    window.location.reload(true);
}

function validateAddProducts() {
    var productType = document.forms["productForm"]["productType"].value;
    var productName = document.forms["productForm"]["productName"].value;

    var amountKilo = document.forms["productForm"]["amountKilo"].value;
    var amountPiece = document.forms["productForm"]["amountPiece"].value;

    var price = document.forms["productForm"]["price"].value;

    if ((productType == "") || (productName == "") || (amountKilo == "") || (amountPiece == "") || (price == "")) {
        alert("All fields must be filled out !");
        return false;
    }
}

