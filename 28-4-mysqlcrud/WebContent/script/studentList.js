/*2018. 7. 3(화) 28기 이원상*/
window.addEventListener("load", function(){
	var selectForm = document.getElementById("selectForm");
	var pagePerRowButton = document.getElementById("pagePerRowButton");
	pagePerRowButton.addEventListener("click", function(){
		console.log("pagePerRowButton 태그 클릭 이벤트");
		document.getElementById("selectForm").submit();
	});
});