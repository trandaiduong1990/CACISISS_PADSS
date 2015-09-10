
function load() {
	
	 YAHOO.namespace("example.container");
	 
	 DisableEnableLinks(true);
    
	// Initialize the temporary Panel to display while waiting for external content to load 
	YAHOO.example.container.wait =  
	        new YAHOO.widget.Panel("wait",   
	            { width:"270px",  
	              fixedcenter:true,  
	              close:false,  
	              draggable:false,  
	              zindex:4, 
	              modal:true, 
	              visible:false 
	            }  
	        );

	YAHOO.example.container.wait.setHeader("Please wait untill Import finish..."); 
	YAHOO.example.container.wait.setBody('<img src="images/loading.gif" />'); 
	YAHOO.example.container.wait.render(document.body);

	YAHOO.example.container.wait.show(); 
	
	}


function DisableEnableLinks(trueOrFalse){

	// disable or enable leftFrame
	var myIFrame2 = window.parent.leftFrame;
	objLinksFromFrames2 = myIFrame2.document.links;
	linkDE(objLinksFromFrames2, trueOrFalse);
	
	// disable or enable topFrame
	var myIFrame3 = window.top.topFrame;
	objLinksFromFrames3 = myIFrame3.document.links;
	linkDE(objLinksFromFrames3, trueOrFalse);
	  
}


function linkDE(objLinks, xHow){
	
	for(i=0;i<objLinks.length;i++){
	    objLinks[i].disabled = xHow;
	    //link with onclick
	    if(objLinks[i].onclick && xHow){  
	        objLinks[i].onclick = new Function("return false;" + objLinks[i].onclick.toString().getFuncBody());
	    }
	    //link without onclick
	    else if(xHow){  
	      objLinks[i].onclick = function(){return false;}
	    }
	    //remove return false with link without onclick
	    else if(!xHow && objLinks[i].onclick.toString().indexOf("function(){return false;}") != -1){            
	      objLinks[i].onclick = null;
	    }
	    //remove return false link with onclick
	    else if(!xHow && objLinks[i].onclick.toString().indexOf("return false;") != -1){  
	    	objLinks[i].onclick = null;
	      //strClick = objLinks[i].onclick.toString().getFuncBody().replace("return false;","");
	      //objLinks[i].onclick = new Function(strClick);
	    }
	  }
}

String.prototype.getFuncBody = function(){ 
	  var str=this.toString(); 
	  str=str.replace(/[^{]+{/,"");
	  str=str.substring(0,str.length-1);   
	  str = str.replace(/\n/gi,"");
	  if(!str.match(/\(.*\)/gi))str += ")";
	  return str; 
	};