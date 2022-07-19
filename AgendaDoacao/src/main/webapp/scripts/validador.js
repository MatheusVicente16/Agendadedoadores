/**
 * Validação do meu formulário
 */

function validar() {
	/**alert('teste para ver se esta sendo linkado')  */

	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	let tipo = frmContato.tipo.value
	if (nome === "") {
		alert('Por gentileza preencha o campo Nome')
		frmContato.nome.focus() /*Posicionando cursor neste ponto*/
		return false
	} else if (fone === "") {
		alert('Por gentileza preencha o campo Fone')
		frmContato.fone.focus() /*Posicionando cursor neste ponto*/
		return false
	} else if (tipo === "") {
		alert('Por gentileza preencha o campo Tipo Sanguineo')
		frmContato.tipo.focus() /*Posicionando cursor neste ponto*/
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}