/*2018. 7. 3(ȭ) 28�� �̿���*/
window.addEventListener("load", function(){
	var selectForm = document.getElementById("selectForm");
	var pagePerRowButton = document.getElementById("pagePerRowButton");
	pagePerRowButton.addEventListener("click", function(){
		console.log("pagePerRowButton �±� Ŭ�� �̺�Ʈ");
		document.getElementById("selectForm").submit();
	});
});