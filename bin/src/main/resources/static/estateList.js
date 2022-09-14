let bottone = document.getElementById('attivaFiltri');

bottone.addEventListener('click', function(e) {
	e.preventDefault();
	
	
	if(document.getElementById(`filtriAvanzati`).style.display == `none`) {
		document.getElementById(`filtriAvanzati`).style.display = `block`;
	}
	else {
		document.getElementById(`filtriAvanzati`).style.display = `none`;
	
		
	}
	
});
   