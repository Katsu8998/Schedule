function registerFunction() {
    let name = document.getElementById("name").value;
    let id = document.getElementById("id").value;
    let pass = document.getElementById("password").value;

    if (!name.match(/^[A-Za-z0-9]*$/)) {
        alert("名前に半角英数字で入力してください");
        return false;
    }

    if (!id.match(/^[A-Za-z0-9]*$/)) {
        alert("IDに半角英数字で入力してください");
        return false;
    }

    if (name > 20) {
        alert("名前に20文字以内で入力してください");
        return false;
    }

    if (id > 10000) {
        alert("IDに5桁のIDを入力してください");
        return false;
    }

    if (pass.length > 30) {
        alert("パスワードに30文字以内で入力してください");
        return false;
    }

}