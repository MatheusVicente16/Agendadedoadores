/**
 *  Realiza a confirmação para que seja realizado a exclusão de um contato no DB
 */
 
 function confirmar (idcon){
	let resposta = confirm("Confirma a exclusão deste contato ?")
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon
	}
	
}