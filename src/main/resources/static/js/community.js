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
            if(res.code = 200){
                $("#comment_section").hide();
            }else{
                alert(res.message);
            }
        },
        dataType: "json"
    });
    console.log(questionId);
    console.log(commentContent);
}