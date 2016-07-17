/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var answers;


function antworten(){
    var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
    };
    
   var pollid = getUrlParameter('idpoll');
    $.ajax({
		type : "GET",
		url : "http://localhost:8080/rest/getById/" + pollid,
		data : null,
		dataType : "json",
		success : function(data) {
			 
                         for(var i = 0; i < data.answer.length;i++){
     var string = data.answer[i];
     $("#antworte").append('<input type="radio" name="antwort" value='+string+'> '+string);
 }
				}
});


}

$(function(){
    antworten();
});

function antwortenA(){
     var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
    };
    
   var pollid = getUrlParameter('idpoll');
    var rates = $('input[name="antwort"]:checked').val();
    console.log(rates);
    $.ajax({
		type : "PUT",
		url : "http://localhost:8080/rest/addAnswer/" + pollid +"/" + rates,
		data : null,
		dataType : "json"
            });
            
            var route = './result.html?idpoll=' + pollid;
            location.href = route;
            }