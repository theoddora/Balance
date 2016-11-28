/**
 * Created by pgenev on 28/11/2016.
 */
$(document).ready(function(){

//let's create arrays
    var vegetables = [
        {display: "Cucumber", value: "Cucumber" },
        {display: "Onion", value: "Onion" },
        {display: "Carrot", value: "Carrot" }];


    var fruits = [
        {display: "Banana", value: "Banana" },
        {display: "Apple", value: "Apple" },
        {display: "Strawberry", value: "Strawberry" }];




//If parent option is changed
    $("#parent_selection").change(function() {
        var parent = $(this).val(); //get option value from parent

        switch(parent){ //using switch compare selected option and populate child
            case 'vegetables':
                list(vegetables);
                break;
            case 'fruits':
                list(fruits);
                break;

            default: //default child option is blank
                $("#child_selection").html('');
                break;
        }
    });

//function to populate child select box
    function list(array_list)
    {
        $("#child_selection").html(""); //reset child options
        $(array_list).each(function (i) { //populate child options
            $("#child_selection").append("<option value="+array_list[i].value+">"+array_list[i].display+"</option>");


        });
    }

});