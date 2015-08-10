/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

//      *******MAIN FUNCTIONS*******

    $.fn.friendlist = function(paramId) {
        var list = '<ul class="friend_list">';
        for (var i = 0; i < paramId.data.length; i++) {
                    list += '<li class="friend_li">';
                    list += '<div class="friend">';
                    list += '<div class="img_container">';
                    list += '<img src="/assets/images/rectangle19@3x.png" class="friend_picture" >';
                    list += '</div>';
                    list += '<div class="text_block">';
                    list += '<a href="/index/item' + paramId.data[i].id + '" >'; //style="float:left,width:70%"
                    list += '<span id="item_name/' + paramId.data[i].id + '" class="friend_name" >' + paramId.data[i].name + '</span>';
                    list += '</a>';
                    list += '</div>';
                    list += '</div>';
                    list += '<div class="center_line"></div>';
                    list += '</li>';
        //            console.log(paramId.data[i].id);
        //            console.log(paramId.data[i].name);
       }
        list += '</ul>';
        $('.friend_list').empty().append($(list));

    }
});