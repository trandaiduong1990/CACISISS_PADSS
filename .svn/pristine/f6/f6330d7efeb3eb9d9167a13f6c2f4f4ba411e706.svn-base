
function switchMenu(obj) {
	pageLoad()
	var el = document.getElementById(obj);
	if ( el.style.display != "none" ) {
		el.style.display = 'none';
	}
	else {
		el.style.display = '';
	}
}

function $() {

	var elements = new Array();
	for (var i = 0; i < arguments.length; i++) {
		var element = arguments[i];
	   if (typeof element == 'string')
			element = document.getElementById(element);
			
		if (arguments.length == 1)
			return element;
		elements.push(element);
	}
	return elements;
}

function collapseAll(objs) {
	var i;
	for (i=0;i<objs.length;i++ ) {
		objs[i].style.display = 'none';
	}
}
function pageLoad() {
	collapseAll($('myvar1','myvar2','myvar3'));
}

