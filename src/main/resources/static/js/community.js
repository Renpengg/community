function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId" : questionId,
            "content" : commentContent,
            "type" : 1
        }),
        success: function (res) {
            console.log(res.code);
            if(res.code == 200){
                $("#comment_section").hide();
            }else{
                console.log(res.code);
                if(res.code == 2003){
                    var isAccepted = confirm(res.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=809cd85a2ae8203b70b7&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                }else{
                    alert(res.message);
                }
            }
        },
        dataType: "json"
    });
}