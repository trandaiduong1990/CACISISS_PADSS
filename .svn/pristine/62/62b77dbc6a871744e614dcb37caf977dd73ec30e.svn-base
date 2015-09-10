function timeDiff(fromDate, toDate) {

	var bufferMonth = document.frmMain.reportTimeInterval.value;
	alert(bufferMonth);

	var fd = new Date(fromDate.split('/')[2], fromDate.split('/')[1] - 1, fromDate.split('/')[0]);
	var td = new Date(toDate.split('/')[2], toDate.split('/')[1] - 1, toDate.split('/')[0]);

	var fdMonth = parseInt(fd.getMonth()) + parseInt(bufferMonth);
	var nd = new Date(fd.setMonth(fdMonth));

	if (td.getTime() > nd.getTime()) {
		alert('Please Select NOT more than ' + bufferMonth + ' Months');
		return false;
	} else {
		return true;
	}

}