//パスワード変更画面

function validateSignupForm() {
    let password = document.getElementById("password").value;
    let new_pass = document.getElementById("new_pass").value;

    if (password == new_pass) {
        alert("パスワードが同じです");
        return false;
    } else if (new_pass.length > 30) {
        alert("パスワードの長さを30文字以内にしてください");
        return false;
    } else if (!new_pass.match(/^[A-Za-z0-9]*$/)) {
        alert("半角英数字で入力してください");
        return false;
    }
}
