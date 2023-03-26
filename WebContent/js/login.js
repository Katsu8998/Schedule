//ログイン画面

function funcConfirm() {
    const text = document.getElementById("text").value;

    if (text == "") {
        alert("ID欄が空欄です");
        return false;
    }

    const password = document.getElementById("password").value;

    if (password == "") {
        alert("パスワード欄が空欄です");
        return false;
    }
}

