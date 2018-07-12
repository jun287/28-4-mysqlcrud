/*2018. 7. 3(화) 28기 이원상 studentList.js*/
window.addEventListener("load", function(){
	var selectForm = document.getElementById("selectForm");
	var pagePerRowButton = document.getElementById("pagePerRowButton");
	var old = document.getElementById("old");
	var young = document.getElementById("young");
	pagePerRowButton.addEventListener("click", function(){
		console.log("pagePerRowButton 태그 클릭 이벤트");
		document.getElementById("selectForm").submit();
	});
	old.addEventListener("click", function(){
		document.getElementById("oldAgeForm").submit();
	});
	young.addEventListener("click", function(){
		document.getElementById("youngAgeForm").submit();
	});
});