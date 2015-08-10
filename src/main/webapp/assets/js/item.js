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
               console.log(json);
               $('#item_name').text(json.item.title);
               $('.main').css("width","80%");
//               $('.item_picture').attr("src",json.item.picture);
               $('.item_picture').attr("src","/assets/images/rectangle19@3x.png");
               $('.item_description_text').text(json.item.description);
               $('.item_url_text').text(json.item.url);
               $('.container').remove();
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
                //$("#bought").css("display","none");
            },

            error: function () {
                console.log('error');
            }

        });
    });

});