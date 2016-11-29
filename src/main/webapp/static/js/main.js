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

document.getElementById("products").addEventListener('change', function() {
    var product = this.value;

    if (product == "vegetable") {
        document.getElementById("fruitsSelect").style.display = "none";
        document.getElementById("vegetablesSelect").style.display = "inline-block";
    }
    else if(product =="fruit"){
        document.getElementById("fruitsSelect").style.display = "inline-block";
        document.getElementById("vegetablesSelect").style.display = "none";
    }
});

