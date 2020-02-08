
function comment2Target(id, content, type){
    if(!content){
        alert("回复内容不能为空")
        return
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId" : id,
            "content" : content,
            "type" : type
        }),
        success: function (res) {
            console.log(res.code);
            if(res.code == 200){
                window.location.reload();
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

/**
 * 将回复内容以post的请求发送到后台
 * */
function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    comment2Target(questionId, commentContent, 1);
}

function comment(e){
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2Target(commentId, content, 2);
}

/**
* 展开二级评论
* */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);;

    //标记评论展开状态
    var collapse = e.getAttribute("data-collapse");

    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        var subCommentContainer = $("#comment-" + id);

        if(subCommentContainer.children().length != 1){
            //展开二级评论
            comments.addClass("in");
            e.setAttribute("data-collapse", "collapse");
            e.classList.add("active");
        }else{
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>",{
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object user-avatar",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>",{
                        "class": "media-body user-name"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "meun"
                    })).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    }));

                    var mediaElement = $("<div/>",{
                        "class": "media"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                })
            })

            //展开二级评论
            comments.addClass("in");
            e.setAttribute("data-collapse", "collapse");
            e.classList.add("active");
        }
    }
}

/**
 *控制显示选择标签
 **/

function showSelectTag() {
    $("#select-tag").show();
}

/**
 *选择标签
 **/
function selectTag(e){
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();

    if(previous.indexOf(value) == -1){
        if(previous){
            $("#tag").val(previous + "," + value);
        }else{
            $("#tag").val(value);
        }
    }
}