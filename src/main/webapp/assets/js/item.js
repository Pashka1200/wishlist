/**
 * Created by admin on 31.07.2015.
 */
jQuery(function ($) {
    'use strict';

    $(document).ready(function() {
        var url = window.location.href;
        var pos = url.substring(url.lastIndexOf('/') + 1);
        var item_id = pos.substring(4);

        $.getJSON('/index/item' + item_id + '/getItem', {}, function (json) {

                var list = '<ul>';
                    list += '<li>';
                    list += '<div>';
                    list += '<span id="item_name/'+json.item.id +'" class="fn_text" >' + json.item.title + '</span>';
                    list += '<br>';
                    list += '<span id="item_name/'+json.item.id +'" class="fn_text" >' + json.item.url + '</span>';
                    list += '<br>';
                    list += '<span id="item_name/'+json.item.id +'" class="fn_text" >' + json.item.description + '</span>';
                    list += '<br>';
                    list += '</div>';
                    list +=  '<hr >';
                    list += '</li>';
                list += '</ul>';

                $('.item').append($(list));

        });
    });

    $('body').on('click', "#bought", function () {

        var url = window.location.href;
        var pos = url.substring(url.lastIndexOf('/') + 1);
        var item_id = pos.substring(4);
        var postData = {item_id: item_id,buy_status:1};
        console.log(postData);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/index/reserve/isBought",
            dataType:'json',
            data: JSON.stringify(postData),

            success:  function (json) {
                console.log(json);
                $("#bought").css("display","none");
            },

            error: function () {
                console.log('error');
            }

        });
    });

});