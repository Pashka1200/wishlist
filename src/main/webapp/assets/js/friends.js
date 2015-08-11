/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

//      *******MAIN FUNCTIONS*******

    $.fn.friendlist = function(paramId) {

        var list = '<ul class="friend_list">';
        for (var i = 0; i < paramId.data.length; i++) {

                    FB.api(
                        "/"+ paramId.data[i].id +"/picture?width=250&height=250",
                        function (response) {
                            if (response && !response.error) {
                                console.log(response);
                                localStorage.setItem("avatar" , response.data.url);
                            }
                        }
                    );
                    list += '<li class="friend_li">';
                    list += '<div class="friend">';
                    list += '<div class="img_container">';
                    list += '<img src="https://graph.facebook.com/'+ paramId.data[i].id+'/picture?width=250&height=250" id="'+ paramId.data[i].id +'" class="friend_picture" >';
                    list += '</div>';
                    list += '<div class="text_block">';
                    list += '<a href="/index/item' + paramId.data[i].id + '" >'; //style="float:left,width:70%"
                    list += '<span id="item_name/' + paramId.data[i].id + '" class="friend_name" >' + paramId.data[i].name + '</span>';
                    list += '</a>';
                    list += '</div>';
                    list += '</div>';
                    list += '<div class="center_line"></div>';
                    list += '</li>';

            setTimeout(
                localStorage.removeItem("avatar"),200);
            }
        list += '</ul>';
        $('.friend_list').empty().append($(list));

    }
});