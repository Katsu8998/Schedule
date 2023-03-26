//検索画面
function searchFunction() {
    let search = document.getElementById("search").ariaValueMax;

    if (search > 10000) {
        alert("5桁以内のIDを入力してください");
    }

    if (search == "") {
        alert("ID欄が空欄です");
        return false;
    }
}