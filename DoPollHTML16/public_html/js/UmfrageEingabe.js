/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function plus(){
        $("#answerList").append('<li><input/></li>');
    }
function minus(){
        $('#answerList li:last').remove();
    }
    
function umfrage() {
	jurl = "http://localhost:8080/rest/create";
        umfrage = '{"thema" :"';
        umfrage += $('#thema').val();
        umfrage += '","question" : "';
        umfrage += $("#frage").val();
        umfrage += '","answer"  : ["';
        $("#answerList > li").each(function (){
            umfrage += $(this).children("input").val();
            umfrage += '",';
            
        });
        umfrage = umfrage.substring(0, umfrage.length-1);
        umfrage += '],';
        umfrage += '"endsAt":"';
        umfrage += new Date().getTime();
        umfrage += '"}';
	$.ajax({
		type : "POST",
		url : jurl,
                data: umfrage,
                contentType : "application/json",

		success : function(data){console.log("success");
                $("#anzeigeistraus").html(data.id)},
		error : function(jqXHR,textStatus,  errorThrown ){console.log("error");
        console.log(textStatus);
        console.log(errorThrown);}
	});
}

