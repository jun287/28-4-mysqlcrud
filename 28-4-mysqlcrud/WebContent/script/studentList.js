/*2018. 7. 3(ȭ) 28�� �̿��� studentList.js*/
window.addEventListener("load", function(){
	var selectForm = document.getElementById("selectForm");
	var pagePerRowButton = document.getElementById("pagePerRowButton");
	var old = document.getElementById("old");
	var young = document.getElementById("young");
	pagePerRowButton.addEventListener("click", function(){
		console.log("pagePerRowButton �±� Ŭ�� �̺�Ʈ");
		document.getElementById("selectForm").submit();
	});
	old.addEventListener("click", function(){
		document.getElementById("oldAgeForm").submit();
	});
	young.addEventListener("click", function(){
		document.getElementById("youngAgeForm").submit();
	});
});