<!doctype html>
<html lang="en" style="height: 100%;width: 100%;">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>vbao</title>
</head>
<body style="height: 100%;width: 100%;margin: 0 auto;">
<div style="height: 100%;width: 100%;background: gainsboro;overflow: hidden;display: flex;justify-content: center;flex-direction: column;align-items: center;">
    <p>
        <label>
            <span>卡号</span>
            <input name="accountNumber" type="text" id="accountNumber" placeholder="请输入账号" style="height: 30px;">
        </label>
    </p>
    <p>
        <label>
            <span>密码</span>
            <input type="password" name="payPassword" id="payPassword" placeholder="请输入密码" style="height: 30px;">
        </label>
    </p>
    <p>
        <label>
            <span>金额</span>
            <input type="text" name="payAmount" id="payAmount" placeholder="请输入金额" style="height: 30px;">
        </label>
    </p>
    <button style="height: 40px;width: 120px;background: dodgerblue;color: white;" onclick="check()">提交</button>
    <button  style="height: 40px;width: 120px;background: dodgerblue;color: white;"> <a href="https://auth.alipay.com/login/index.htm">alipay</a></button>
</div>
<script>
    const postUrl = 'https://paytest.chinavbao.cn/alipay/test'
    let submitData = {
        accountNumber: '',
        payPassword: '',
        payAmount: 0
    }
    let flag = true
    function getValue (id) {
        let value = document.getElementById(id).value.trim()
        return value
    }


    function check () {
        flag = true
        for (let item in submitData) {
            let checkValue = getValue(item)
            submitData[item] = ''
            if (checkValue === '') {
                alert(item + "不能为空！")
                flag = false
                break;
            }
            else {
                submitData[item] = checkValue
            }
        }
        submitData.payAmount = parseInt(submitData.payAmount)
        console.log(submitData)
        if (flag){
            submit()
        }
        else {
            alert("交易失败！")
        }
    }
    function submit () {
        var ajax = new XMLHttpRequest();
        ajax.open('post', postUrl);
        ajax.setRequestHeader("Content-type", "application/json;charset=utf8")
        ajax.send(JSON.stringify(submitData));
        ajax.onreadystatechange = function () {
            if (ajax.readyState==4 &&ajax.status==200) {
                console.log(ajax.responseText)
                alert('交易成功！')
            }
        }
    }
</script>
</body>
</html>