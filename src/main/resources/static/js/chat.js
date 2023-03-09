$(function () {
    let auth = false;
    function hideChat(boolean) {
        if (boolean) {
            $('.u-group').css("visibility", "hidden")
            $('.u-form').css("visibility", "hidden")
            $('.registration-form').css("visibility", "visible")
            auth = false;
        }
        if (!boolean) {
            $('.u-group').css("visibility", "visible")
            $('.u-form').css("visibility", "visible")
            $('.registration-form').css("visibility", "hidden")
            $('.registration-form').css("height","1px")
            auth=true
        }

    }


    $(function () {
        $.get("/init", {}, function (response) {

            if (response) {
                hideChat(false)

            }
            if (!response) {
                hideChat(true)
            }
        })
    })

    function updateUserList(){
        if (auth) {
            let userList = document.getElementById("userList");

            //userList.innerHTML=""
            $.get("/updateUserList", {}, function (response) {
                $('.u-container-layout-2').text(response.forEach(name => {
                     if (userList.getElementsByClassName(name).length<=0) {
                        let block = document.createElement('div');
                        block.className = name;
                        block.textContent = name;
                        userList.append(block);
                    }
                }))
            })

        }
    }

    function updateMessageList(){
        if (auth) {
            let userList = document.getElementById("chat");

            //userList.innerHTML=""
            $.get("/updateMessageList", {}, function (response) {
                $('.u-container-layout-1').text(response.forEach(name => {
                    if (userList.getElementsByClassName(name).length<=0) {
                        let block = document.createElement('div');
                        block.className = name;
                        block.textContent = name;
                        userList.append(block);
                    }
                }))
            })

        }
    }


    $(function (){
        setInterval( updateMessageList,1000);
        setInterval(updateUserList,5000)
    })
    $('.reg-button').on('click', function () {
        let nameF = $('.nameF').val()
        let passwordF = $('.passwordF').val()
        $.post("/reg", {name: nameF, pass: passwordF}, function (response) {
            if (response) {
                hideChat(false)
            }
        })

    })
    $('.log-button').on('click', function () {
        let nameF = $('.nameF').val()
        let passwordF = $('.passwordF').val()
        $.get("/login", {name: nameF, pass: passwordF}, function (response) {
            if (response) {
                hideChat(false)
            } else alert("Login ili parol ne werni")
        })

    })

    $('.buttonSend').on('click', function () {

        let text = $('.u-input-rectangle').val()
        $('.u-input-rectangle').val("")
        $.post("/send", {textMessage: text.replace(/[^a-zA-ZР-пр-џ0-9\s]/gi,'')}, function (response) {
        })
        updateMessageList()
        setTimeout(() => {
            $(".u-border-3")[1].scrollTo(0,10000)
        }, 500);

    })


})