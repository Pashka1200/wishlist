/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

//      *******MAIN FUNCTIONS*******
    $( document ).ready(function() {

            var fb_id = $.cookie("fb_id");
            var page  = 0;
            var status = 0;
            var postData = {fb_id:fb_id,page:page,buy_status:status};
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/index/items/getToBuyItems",
                dataType:'json',
                data: JSON.stringify(postData),

                success:  function (json) {
                    var list = '<ul class="item_list">';
                    if (json.items.length > 0 ) {
                        for (var i = 0; i < json.items.length; i++) {
                            list += '<li class="item_li">';
                            list += '<div class="item">';
                            list += '<div class="img_container">';
                            list += '<img src="/assets/images/rectangle19@3x.png" class="item_picture" >';
                            list += '</div>';
                            list += '<div class="text_block">';
                            list += '<a href="/index/item' + json.items[i].id + '" >'; //style="float:left,width:70%"
                            list += '<span id="item_name/' + json.items[i].id + '" class="item_title" >' + json.items[i].title + '</span>';
                            list += '</a>';
                            list += '<span id="item_name/' + json.items[i].id + '" class="item_description" >' + json.items[i].description + '</span>';
                            list += '</div>';
                            list += '</div>';
                            list += '<div class="center_line"></div>';
                            list += '</li>';
                        }
                    }
                    list += '</ul>';
                    $('.item_list_tobuy').empty().append($(list));
                    $('.container').remove();

                },

                error: function () {
                    console.log('error');
                }

            });
    });



});