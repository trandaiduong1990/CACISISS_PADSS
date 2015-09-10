// Add the selected items from the source to destination list
function addSrcToDestList1(destList,srcList) {
  // destList = window.document.forms[0].selectprod;
  // srcList = window.document.forms[0].availprod; 
   var len = destList.length;
   for(var i = 0; i < srcList.length; i++) {
   if ((srcList.options[i] != null) && (srcList.options[i].selected)) {
       //Check if this value already exist in the destList or not
       //if not then add it otherwise do not add it.
       var found = false;
   for(var count = 0; count < len; count++) {
      if (destList.options[count] != null) {
        if (srcList.options[i].text == destList.options[count].text) {
           found = true;
           break;
        }
      } 
   } 
   if (found != true) {
       destList.options[len] = new Option(srcList.options[i].text, srcList.options[i].value, true, true);   
       len++;
   }
  }
 }
}

// Deletes from the destination list.
function deleteFromDestList1(destList) {
//   var destList  = window.document.forms[0].selectprod;
   var len = destList.options.length;
   for(var i = (len-1); i >= 0; i--) {
      if ((destList.options[i] != null) && (destList.options[i].selected == true)) {
        destList.options[i] = null;
      }
   }
}

function addTextToDestList1(destList,srcList) {
   var found = false;
   var len = destList.length;
   for(var count = 0; count < len; count++) {
      if (destList.options[count] != null) {
        if (srcList.value == destList.options[count].text) {
           found = true;
           break;
        }
      } 
   } 
   if (found != true) {
       destList.options[len] = new Option(srcList.value, srcList.value, true, true);   
       len++;
   }
}

// Add the selected items from the source to destination list
function addSrcToDestList2(destList,srcList,srcList_v) {
   var found = false;
   var len = destList.length;
   for(var count = 0; count < len; count++) {
      if (destList.options[count] != null) {
        if (srcList.value == destList.options[count].text) {
           found = true;
           break;
        }
      } 
   } 
   if (found != true) {
       destList.options[len] = new Option(srcList.value, srcList_v.value, true, true);   
       len++;
   }
}

function moveText(txtbox,tbox){
   var found = false;
   var len = tbox.length;
   for(var count = 0; count < len; count++) {
      if (tbox.options[count] != null) {
        if (txtbox.value == tbox.options[count].text) {
           found = true;
           break;
        }
      } 
   } 
   if (found != true) {
       tbox.options[len] = new Option(txtbox.value, txtbox.value, true, false);   
       len++;
       txtbox.value = "";
   }
}


//moves the values from one list box to other
function move(fbox,tbox) {
   if (fbox.selectedIndex == -1)
      return

   // temporary arrays to copy and re-paint the fbox
   var tmptxtArr = new Array();
   var tmpvalArr = new Array();

   // counter to track the number of non-null elements in fbox
   var j=0;


   // temporary option object to move the selected item
   var tmpOpt;

   // Loop through the fbox to identify the selected items
   // add them to tbox and reset their values and text to null

   for(var i=0; i<fbox.options.length; i++) {

      // test if the row is selected
      if(fbox.options[i].selected && fbox.options[i].value != '') {

         // test if the fbox is currency box to avoid the removal of base currency
           // create a temporary option object to move the selected item
            tmpOpt = new Option();
            tmpOpt.value = fbox.options[i].value;
            tmpOpt.text = fbox.options[i].text;

            // add the new option to tbox
            tbox.options[tbox.options.length] = tmpOpt;

            // set the selected flag to false
            // to prevent IE throwing up DR. WATSON error
            // while it tries to paint a selected item which is
            // not present in the list
            fbox.options[i].selected = false;


      } else {

         // copy properites from fbox
         tmptxtArr[j] = fbox.options[i].text;
         tmpvalArr[j] = fbox.options[i].value;

         // increment  counter for temporary array
         j++;

      }

   }

   // reset fbox length to temporary array length
   fbox.options.length = tmpvalArr.length;

   // copy the temporary array to the fbox
   for(var i=0; i<tmpvalArr.length; i++){
      fbox.options[i].value = tmpvalArr[i];
      fbox.options[i].text  = tmptxtArr[i];
   }

   return;

}


// End -->
//-->