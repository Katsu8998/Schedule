//スケジュール入力画面
function inputFunction() {

    let detail = document.getElementById("detail").value;
    let title = document.getElementById("title").value;

    if (title.length > 20) {
        alert("件名の文字数を20文字以内にしてください");
        return false;
    } else if (detail.length > 30) {
        alert("メモの文字数を30文字以内にしてください");
        return false;
    }
}

