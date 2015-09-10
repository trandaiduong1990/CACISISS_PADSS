function submitForm(frmName) {
	frmName.submit();
}

function searchlist(){
	
	if(isFilledAtleastOneTextBox()){
		document.forms[0].submit();
	}else{
		alert('Please give atleast ONE search criteria.');
	}
}


function isFilledAtleastOneTextBox(){
	
	var boxes = document.getElementsByTagName("INPUT");
	
	var isFilledAtleastOneFiled = false;

	for(var i = 0; i < boxes.length; i++){
		var input = boxes[i];
		if(input.type != 'text'){ 
			continue;
		}else{
			if(input.value != ''){
				isFilledAtleastOneFiled = true;
			}
		}
	}

	return isFilledAtleastOneFiled;
}